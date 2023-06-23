package PCB_CONTROLLER_OOP;
import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Main_Controller controller ;
        System.out.println("Enter the number of seconds to display: ");
        Scanner scanner = new Scanner(System.in);
        int seconds_to_display = scanner.nextInt();
    
        if(args.length == 1)
            controller = new Main_Controller(seconds_to_display, args[0].equals("1") ? 1 : 0);
        else
            controller = new Main_Controller(seconds_to_display);
        scanner.close();
    }
}
