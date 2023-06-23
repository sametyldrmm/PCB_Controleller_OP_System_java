package PCB_CONTROLLER_OOP;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.*;


public class File_utils {
    private String fileName = "PCB_CONTROLLER_OOP/girdi.txt";
    
    protected int app_countt;
    protected Vector<String> file_content = new Vector<String>();
    protected Vector<String> app_name = new Vector<String>();
    protected Vector<Integer> app_command_count = new Vector<Integer>();
    protected Map<Integer, Vector<String>> app_I_O_case = new HashMap<Integer, Vector<String>>();
    protected Map<Integer, Vector<Integer>> app_I_O_case_time = new HashMap<Integer, Vector<Integer>>();
    
    
    protected Vector<String> run_app_name = new Vector<String>();
    protected Vector<Integer> run_app_time = new Vector<Integer>();

    public int get_app_countt()
    {
        return app_countt;
    }
    public Vector<String> get_app_name()
    {
        return app_name;
    }
    public Vector<Integer> get_app_command_count()
    {
        return app_command_count;
    }
    public Map<Integer, Vector<String>> get_app_I_O_case()
    {
        return app_I_O_case;
    }
    public Map<Integer, Vector<Integer>> get_app_I_O_case_time()
    {
        return app_I_O_case_time;
    }
    public Vector<String> get_run_app_name()
    {
        return run_app_name;
    }
    public Vector<Integer> get_run_app_time()
    {
        return run_app_time;
    }

    private void read()
    {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                file_content.add(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    private void find_app_count()
    {
        for (int i = 0; i < file_content.size(); i++) {
            if (file_content.get(i).equals("olaylar:")) {
                app_countt = i;
                break;
            }
        }
    }

    private void find_app_names_command_count()
    {
        for (int i = 1; i < file_content.size(); i++) {
            if (file_content.get(i).equals("olaylar:")) {
                break;
            }
            else
            {
                String[] temp = file_content.get(i).split(" ");
                app_name.add(temp[0]);
                app_command_count.add(Integer.parseInt(temp[1]));
            
            }
        }
    }

    private void find_app_I_O_case_and_time()
    {
        for (int i = 1; i < file_content.size(); i++) {
            if (file_content.get(i).equals("olaylar:")) {
                break;
            }
            else
            {
                String[] temp = file_content.get(i).split(" ");
                Vector<String> temp_I_O = new Vector<String>();
                Vector<Integer> temp_I_O_time = new Vector<Integer>();
                for (int j = 2; j < temp.length; j += 2) {
                    temp_I_O.add(temp[j]);
                    temp_I_O_time.add(Integer.parseInt(temp[j+1]));
                }
                if(temp.length == 2)
                {
                    temp_I_O = null;
                    temp_I_O_time = null;
                }
                app_I_O_case.put(i -1, temp_I_O);
                app_I_O_case_time.put(i -1, temp_I_O_time);
            }
        }
    }
    
    private void run_info()
    {
        for (int i = app_countt + 1; i < file_content.size() ; i++) {
            String[] temp = file_content.get(i).split(" ");
            run_app_name.add(temp[1]);
            run_app_time.add(Integer.parseInt(temp[2]));
        }

        for(int i = 0; i < run_app_name.size(); i++)
        {
            for(int j = 0; j < run_app_name.size(); j++)
            {
                if(run_app_time.get(i) < run_app_time.get(j))
                {
                    int temp = run_app_time.get(i);
                    run_app_time.set(i, run_app_time.get(j));
                    run_app_time.set(j, temp);

                    String temp2 = run_app_name.get(i);
                    run_app_name.set(i, run_app_name.get(j));
                    run_app_name.set(j, temp2);
                }
            }
        }
    }

    public File_utils()
    {
        read();
        find_app_count();
        find_app_names_command_count();
        find_app_I_O_case_and_time();
        run_info();
    }
}
