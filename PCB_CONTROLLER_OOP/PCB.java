package PCB_CONTROLLER_OOP;
import java.util.*;

public class PCB  {
    
    private String processName;
    private int processNumber; 
    private String processState;
    private int programCounter; 
    private int cpuUsage; 
    private int creationTime; 
    protected int all_time = 0; 
    
    protected int waiting_time = 0;
    protected boolean waiting = false; 

    HashMap<String, ArrayList<Integer>> I_O_count = new HashMap<String, ArrayList<Integer>>();

    public PCB(String proccesName,int processNumber, String processState, int programCounter, int cpuUsage, int creationTime) {
        this.processName = proccesName;
        this.processNumber = processNumber;
        this.processState = processState;
        this.programCounter = programCounter;
        this.cpuUsage = cpuUsage;
        this.creationTime = creationTime;
    }


    public void set_waiting(boolean waiting){
        this.waiting = waiting;
    }
    
    public boolean get_waiting(){
        return this.waiting;
    }

    protected void set_waiting_time(int time){
        this.waiting_time = time;
    }
    protected void set_all_time(int time){
        this.all_time = time;
    }
    
    protected int get_waiting_time(){
        return this.waiting_time;
    }

    public String getProcessName() {
        return processName;
    }
    
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(int processNumber) {
        this.processNumber = processNumber;
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    protected int getCpuUsage() {
        return cpuUsage;
    }

    protected void setCpuUsage(int cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

    protected void showPCB(){
        System.out.println("Process Name: " + this.processName);
        System.out.println("Process Number: " + this.processNumber);
        System.out.println("Process State: " + this.processState);
        System.out.println("Program Counter: " + this.programCounter);
        System.out.println("CPU Usage: " + this.cpuUsage);
        System.out.println("Creation Time: " + this.creationTime);
        System.out.println("All Time: " + this.all_time);
    }
    protected void update_creation_time(int time){
        this.all_time = time - this.creationTime;
    }
}
