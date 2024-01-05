import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // create scanner object
        Scanner inp = new Scanner(System.in);

        //Input for name
        System.out.println("Enter a name: ");

        String name = inp.nextLine();
        //input for location
        System.out.println("Enter where they now live: ");

        String loc = inp.nextLine();

        //calling the update 
        Utils.updateRecord(name,loc);



    }
}