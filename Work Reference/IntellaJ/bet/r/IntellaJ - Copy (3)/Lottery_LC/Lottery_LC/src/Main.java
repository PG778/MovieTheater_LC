import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //creates scanner
        Scanner input = new Scanner(System.in);
        // creates object
        Lottery lot = new Lottery();
        //sets the lottery array to random numbers
        lot.setLotteryNumbers();
/* Test to see the lotttery randomnumbers for testng
        for(int i = 0; i <4; i++){
            System.out.println(lot.getLotteryNumbers()[i]);
        }

 */



        // asks user to input 4 numbers
        System.out.println("Enter 4 numbers for your lottery ticket: ");
        // initializes the user array
        int[] lotteryTicket = new int[4];
        // loops through until the user has enetered 4 numbers t add to the array
        for(int i = 0; i < 4; i++){
            lotteryTicket[i]=input.nextInt();

        }
        /* prints the inut of the user for testing
        for(int i = 0; i < 4;i++){
            System.out.println(lotteryTicket[i]);
        }

         */


        // The following is just pringting the results of the lottery.
        int winnings = lot.checkNums(lotteryTicket);
        System.out.println("The Lottery Numbers: " + Arrays.toString(lot.getLotteryNumbers()));
        System.out.println("Your Chosen Numbers: " + Arrays.toString(lotteryTicket));
        System.out.println("Your winnings are $" + winnings);



    }
}