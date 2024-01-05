import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/SalesData.txt");
        Scanner inputFile = new Scanner(file);
        double totalSalesWeek = 0;
        int weekCount = 0;
        double totalWeeklySale = 0;
        double maxWeek = 0;
        double minWeek = 0;
        while(inputFile.hasNextLine()){
            weekCount++;
            String weekly = inputFile.nextLine();
            String[] sales = weekly.split(",");

            for (String sale : sales) {
                totalSalesWeek += Double.parseDouble(sale);
            }
            double avgDailySalesWeek = totalSalesWeek / 7;

            System.out.printf("Week %d: Total sales = %.2f, Average daily sales = %.2f \n", weekCount, totalSalesWeek, avgDailySalesWeek);

            totalWeeklySale += totalSalesWeek;

            double avgWeeklySales = totalWeeklySale / weekCount;
            System.out.printf("The Sale Average Total For All The Weeks Is %.2f \n",avgWeeklySales);


            if(totalSalesWeek > totalSalesWeek++){
                System.out.println("Week 1 had higher sales");
            }
            if(totalSalesWeek++ > totalSalesWeek+2){
                System.out.println("Week 2 had higher sales");
            }
            if(totalSalesWeek < totalSalesWeek++){
                System.out.println("Week 3 had higher sales");
            }



        }


    }


}