import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class DrugTesting {
    public static void main(String[] args){
        int[] employees = new int[30];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = i + 1; // initialize the employee numbers
        }

        int[][] choice = new int[52][4];
        int[] counts = new int[30];

        Random rand = new Random();


        try {
            PrintWriter writer = new PrintWriter(new FileWriter("src/DrugTesting.txt"));

            for (int week = 1; week <= 52; week++) {
                writer.println("Week " + week);

                for (int i = 0; i < 4; i++) {
                    int employee;
                    do {
                        employee = employees[rand.nextInt(employees.length)];


                    } while (counts[employee - 1] > 0 && week - choice[week-1][i] <= 4);
                    choice[week - 1][i] = week;
                    counts[employee - 1]++;
                    writer.print("#" + employee + "\t");

                    if (choice[week-1][i] <= i) {
                        writer.print("- - - -\t");



                    } else {
                        writer.print("Wk " + (week - choice[week -1][i]) + "\t");

                    }

                    writer.println(counts[employee - 1] + " times");
                }
                writer.println();


            }
            writer.close();

            } catch (IOException ex) {
            throw new RuntimeException(ex);
        }




    }
}
