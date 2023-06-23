package PCB_CONTROLLER_OOP;

import java.util.*;

public class Main_Controller extends File_utils{
    public static int time = 0;
    public static int processNumber = 1000;
    public static String active_process = null;
    public static boolean finished_all_process = true;
    
    private int seconds_to_display;
    private int time_slice = 5;
    private Vector<String> waiting_que = new Vector<String>();
    private Vector<String> ready_que = new Vector<String>();
    private Vector<String> screen_que = new Vector<String>();
    private Vector<String> disk_que = new Vector<String>();
    private Vector<String> ethernet_que = new Vector<String>();
    private Vector<PCB> PCBS = new Vector<PCB>();
    
    private int temp_run_app = 0;
    private String temp_waiting_name ="";
    private String temp_waiting_case = "";
    private int temp_waiting_time = -1;
    private boolean temp2 = false;
    private boolean temp = true;

    private Map<String,Integer> temp_show_names_and_command_count = new HashMap<String,Integer>();
    

    private void PCB_Creat(int creat_time,String Procces_name)
    {
        PCB pcb = new PCB(Procces_name, processNumber, null, 1, 0, creat_time);
        PCBS.add(pcb);
        processNumber++;
    }

    private void PCB_creat_and_add_read_que()
    {
        while(this.run_app_time.size() > temp_run_app && this.run_app_time.get(temp_run_app) == time)
        {
            PCB_Creat(time, this.run_app_name.get(temp_run_app));
            ready_que.add(this.run_app_name.get(temp_run_app));
            if(temp_run_app == 0)
            {
                PCBS.get(0).setCpuUsage(PCBS.get(0).getCpuUsage() -1);
                PCBS.get(0).setProgramCounter(PCBS.get(0).getProgramCounter() -1);
            }
            temp_run_app++;
        }
    }

    private int process_index(String name)
    {
        for(int i = 0; i < PCBS.size(); i++)
        {
            if(PCBS.get(i).getProcessName().equals(name))
            {
                return i;
            }
        }
        return -1;
    }

    private void PCBS_count_and_cpu_update(int index)
    {
        if(time_slice != 0)
        {

            PCBS.get(index).setCpuUsage(PCBS.get(index).getCpuUsage() + 1);
            PCBS.get(index).setProgramCounter(PCBS.get(index).getProgramCounter() + 1);
        }
    }
    
    private void bug_fix()
    {
        if(waiting_que.size() > 0)
        {    
            if(temp_waiting_time +1 == time)
            {
                if(temp_waiting_case.equals("ekran"))
                {
                    screen_que.add(temp_waiting_name);
                }
                else if(temp_waiting_case.equals("disk"))
                {
                    disk_que.add(temp_waiting_name);
                }
                else if(temp_waiting_case.equals("ethernet"))
                {
                    ethernet_que.add(temp_waiting_name);
                }
                int index = process_index(active_process);
                PCBS_count_and_cpu_update(index);
                active_process = null;
                temp_waiting_case = "";
                temp_waiting_name = "";
                temp_waiting_time = -1;
            }
        }
        
    }
    private void read_que_go_run()
    {
        if(ready_que.size() > 0 && active_process == null)
        {
            active_process = ready_que.get(0);
            ready_que.remove(0);
            time_slice = 5;
        }
    }
    private void read_que_go_run2()
    {
        if(ready_que.size() > 0 && active_process == null)
        {
            active_process = ready_que.get(0);
            ready_que.remove(0);
            time_slice = 5;
            int index = process_index(active_process);
            if( temp_show_names_and_command_count.get(active_process) != null && temp_show_names_and_command_count.get(active_process) == PCBS.get(index).getProgramCounter() )
            {
                PCBS_count_and_cpu_update(index);
            }
        }
    }

    private void wait_and_other_que_remove(int index)
    {
        if(index != -1 && PCBS.get(index).waiting)
        {
            for(int i = 0; i < PCBS.size(); i++)
            {
                if(PCBS.get(i).waiting)
                {
                    if(time - PCBS.get(i).waiting_time == 4)
                    {
                        PCBS.get(i).waiting = false;
                        ready_que.add(PCBS.get(i).getProcessName());
                        waiting_que.remove(PCBS.get(i).getProcessName());
                        
                        if(screen_que.indexOf(PCBS.get(i).getProcessName()) != -1)
                        {
                            screen_que.remove(PCBS.get(i).getProcessName());
                        }
                        else if(disk_que.indexOf(PCBS.get(i).getProcessName()) != -1)
                        {
                            disk_que.remove(PCBS.get(i).getProcessName());
                        }
                        else if(ethernet_que.indexOf(PCBS.get(i).getProcessName()) != -1)
                        {
                            ethernet_que.remove(PCBS.get(i).getProcessName());
                        }
                    }
                }
            }
            index = process_index(active_process);
        }
    }

    private void finish_time_slice_go_ready(int index,boolean temp2)
    {
        if(time_slice == 0 && ready_que.size() > 0)
        {
            ready_que.add(active_process);
            if(temp2)
                ready_que.remove(active_process);
            active_process = ready_que.get(0);
            ready_que.remove(0);
            time_slice = 5;
            temp2 = false;
        }
        else if(time_slice == 0 && ready_que.size() == 0)
        {
            time_slice = 5;
        }
    }

    private void low_exception()
    {
        if(ready_que.size() > 0)
        {
            active_process = ready_que.get(0);
            ready_que.remove(0);
            time_slice = 5;
            PCBS_count_and_cpu_update(process_index(active_process));
        }
    }

    private int utils_app_I_O_case_time(int x,int y)
    {
        return(this.app_I_O_case_time.get(x).indexOf(PCBS.get(y).getProgramCounter()));
    }
    
    private String utils_app_I_O_case_name(int x,int y)
    {
        return(this.app_I_O_case.get(x).get(y));
    }

    private void I_O_event_wait_accepted()
    {
        int index = process_index(active_process);
        if(index != -1)
        {
            int find_I_O = this.app_name.indexOf(active_process) ;
            if(this.app_I_O_case_time.get(find_I_O) != null && utils_app_I_O_case_time(find_I_O, index)  != -1)
            {                             
                String I_O_type = utils_app_I_O_case_name(find_I_O,utils_app_I_O_case_time(find_I_O, index));
                if(I_O_type.equals("ekran"))
                {
                    waiting_que.add(active_process);
                }
                else if(I_O_type.equals("disk"))
                {
                    waiting_que.add(active_process);
                }
                else if(I_O_type.equals("ethernet"))
                {
                    waiting_que.add(active_process);
                }

                temp_waiting_case = I_O_type;
                temp_waiting_name = active_process;
                temp_waiting_time = time;
                PCBS.get(index).waiting_time = time;
                PCBS.get(index).waiting = true;
                time_slice = 1;
                temp2 = true;
            }
        }
    }

    private void I_O_event_wait_completion()
    {
        for(int i = 0; i < PCBS.size(); i++)
        {
            if(PCBS.get(i).waiting)
            {
                if(time - PCBS.get(i).waiting_time == 4)
                {
                    PCBS.get(i).waiting = false;
                    ready_que.add(PCBS.get(i).getProcessName());
                    waiting_que.remove(PCBS.get(i).getProcessName());

                    if(screen_que.indexOf(PCBS.get(i).getProcessName()) != -1)
                    {
                        screen_que.remove(PCBS.get(i).getProcessName());
                    }
                    else if(disk_que.indexOf(PCBS.get(i).getProcessName()) != -1)
                    {
                        disk_que.remove(PCBS.get(i).getProcessName());
                    }
                    else if(ethernet_que.indexOf(PCBS.get(i).getProcessName()) != -1)
                    {
                        ethernet_que.remove(PCBS.get(i).getProcessName());
                    }
                    if(active_process == null && ready_que.size() == 1 )
                    {
                        read_que_go_run();
                        int index = process_index(active_process);
                        PCBS_count_and_cpu_update(index);
                    }
                }
            }
        }
    }

    private void PCBS_terminated()
    {
        int index = process_index(active_process);
        if(index != -1)
        {
            if(PCBS.get(index).getProgramCounter() -1 == app_command_count.get(app_name.indexOf(active_process) ))
            {
                waiting_que.remove(PCBS.get(index).getProcessName());
                if(screen_que.indexOf(PCBS.get(index).getProcessName()) != -1)
                {
                    screen_que.remove(PCBS.get(index).getProcessName());
                }
                else if(disk_que.indexOf(PCBS.get(index).getProcessName()) != -1)
                {
                    disk_que.remove(PCBS.get(index).getProcessName());
                }
                else if(ethernet_que.indexOf(PCBS.get(index).getProcessName()) != -1)
                {
                    ethernet_que.remove(PCBS.get(index).getProcessName());
                }
                PCBS.remove(index);

                if(ready_que.size() == 0)
                {
                    time_slice = 0;
                }
                if(PCBS.size() == 0)
                {
                    temp = false;
                }
            }
        }
    }

    private void detailed_show(int detailed_int)
    {
        if(detailed_int == 1)
        System.out.print("active_process: " + active_process);
        
        if(active_process != null)
        {
            int index = process_index(active_process);
            if(index == -1)
                active_process = null;

            if(detailed_int == 1)
            {
                if(index == -1)
                    System.out.format("%25s", "procces ended");
                else
                    System.out.format("%25s %25s", "procces counter: " + PCBS.get(index).getProgramCounter(),PCBS.get(index).getCpuUsage());
            }

            if(index != -1)
                temp_show_names_and_command_count.put(active_process,PCBS.get(index).getProgramCounter());
        }
        if(detailed_int == 1)
        {
            System.out.format("%15s %40s", "time:" + time , "ready_que:" + ready_que );
            System.out.format( "%25s %40s %40s ","screen_que:" + screen_que , "disk_que:" + disk_que , "ethernet_que: " + ethernet_que);
            System.out.println("");
        }
    }

    private void show_final_settings()
    {
        for(int i = 0; i < PCBS.size(); i++)
        {
            if(temp_show_names_and_command_count.get(PCBS.get(i).getProcessName()) != null)
            {
                PCBS.get(i).setProgramCounter(temp_show_names_and_command_count.get(PCBS.get(i).getProcessName()) + 1);
                PCBS.get(i).setCpuUsage(temp_show_names_and_command_count.get(PCBS.get(i).getProcessName()) );
                if(active_process == PCBS.get(i).getProcessName())
                    PCBS.get(i).setProcessState("running");
                else
                    PCBS.get(i).setProcessState("waiting");    
            }
            PCBS.get(i).set_all_time(seconds_to_display - PCBS.get(i).getCreationTime() );
        }
    }
    
    private void show_techer()
    {
        System.out.println("CPU'da çalışan procces: "+ active_process);
        System.out.print("Ready que:");
        for(int i = 0; i < ready_que.size();i++)
            System.out.print(" "+ ready_que.get(i));

        System.out.println();
        System.out.print("Screen que:");
        for(int i = 0; i < screen_que.size();i++)
            System.out.print(" "+ screen_que.get(i));
        
        System.out.println();
        System.out.print("Disk que:");
        for(int i = 0; i < disk_que.size();i++)
            System.out.print(" "+ disk_que.get(i));

        System.out.println();
        System.out.print("Ethernet que:");
        for(int i = 0; i < ethernet_que.size();i++)
            System.out.print(" "+ ethernet_que.get(i));

        System.out.println();
        System.out.print("PCB'si bulunan proccesler:");
        
        for(int i = 0; i < PCBS.size(); i++)
        {
            System.out.print(" " + PCBS.get(i).getProcessName());
        }
        System.out.println();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println(seconds_to_display +". saniyedeki PCB’sini görüntülemek istediğiniz proses ismini giriniz:");
        String name_to_display_prcces = scanner.nextLine();
        scanner.close();
        display(seconds_to_display,name_to_display_prcces) ;
    }

    private void running_case(int index)
    {
        if(active_process != null)
        {
            if(index != -1)
            {
                finish_time_slice_go_ready(index,temp2);
                PCBS_count_and_cpu_update(index);
            }
            else if(index == -1 && active_process != null)
            {
                low_exception();
            }
        }
    }

    private void run_all(int detailed_int)
    {
        while(seconds_to_display > time && temp)
        {
            PCB_creat_and_add_read_que();
            read_que_go_run();
            bug_fix();
            if(active_process != null)
            {
                int index = process_index(active_process);
                wait_and_other_que_remove(index);
                running_case(index);
            }
            read_que_go_run2();
            I_O_event_wait_accepted();
            I_O_event_wait_completion();
            PCBS_terminated();
            detailed_show(detailed_int);
            time_slice--;
            time++;
        }
        show_final_settings();
        show_techer();
    }

    public Main_Controller(int ..._seconds_to_display)
    {
        int arg1 = 0;
        this.seconds_to_display = _seconds_to_display[0];
        if(_seconds_to_display.length > 1)
            arg1 = _seconds_to_display[1];        
        run_all(arg1);
    }

    private void display(int time, String name_to_display_procces)
    {
        System.out.println("Time: " + time + " " + "saniyede gösterilecek procces" + " " + name_to_display_procces);
        for(int i = 0; i < PCBS.size(); i++)
        {
            if(PCBS.get(i).getProcessName().equals(name_to_display_procces))
            {
                PCBS.get(i).showPCB();
            }
        }
    }
}
