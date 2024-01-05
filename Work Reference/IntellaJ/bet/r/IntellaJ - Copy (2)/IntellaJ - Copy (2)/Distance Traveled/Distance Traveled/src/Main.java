import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int spd, time;
        boolean isRight;

        System.out.print("Enter the speed of the vehicle (in mph as a posotive number): ");
        spd = input.nextInt();

        System.out.print("Enter the time the vehicle has traveled (in hours greater than 0):");
        time = input.nextInt();

        if(spd >= 0 && time >=1){
            isRight = true;
            System.out.println("\nHours\tDistance Traveled");
        }else{
            isRight = false;
            System.out.println("If You are seeing this Re Run the program, you have entered invalid data. ");
        }



        if (isRight = true){
            for (int i = 1; i <= time; i++) {
                int distance = spd * i;
                System.out.println(i + "\t" + distance);
            }

            try {
                FileWriter fileWriter = new FileWriter("src/DistanceTraveled.txt");
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println("Hours\tDistance Traveled");
                for (int i = 1; i <= time; i++) {
                    int distance = spd * i;
                    printWriter.println(i + "\t" + distance);
                }
                printWriter.close();

            } catch (IOException e) {
                System.out.println(" ");
            }

        }



    }
}