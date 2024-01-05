import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // create a list of all 52 playing cards
        int cards = 52;
        CardTwo[] deck = new CardTwo[cards];
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};

        int count = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < deck.length / 4; i++) {
                deck[count] = new CardTwo(suits[j], i + 1);
                count++;

            }
        }
        int round = 0;
        int tie = 0;
        int P1Win =0;
        int P2Win =0;

        while (round < 26) {



            Random random = new Random();
            int rand = random.nextInt(0,cards +1);

            CardTwo Player1 = deck[rand];
            for (int i = rand; i < deck.length; i++) {
                if (i < 51) {
                    deck[i] = deck[i + 1];
                }
                deck[i] = null;

            }
            cards--;






            rand = random.nextInt(0,cards +1);
            CardTwo Computercard = deck[rand];
            for (int i = rand; i < deck.length; i++) {
                if (i < 51) {
                    deck[i] = deck[i + 1];

                }
                deck[i] = null;

            }
            cards--;






            //System.out.println(Computercard + " is playr two card");


            System.out.println("Player ones card is: " + Player1 + "\n Player Two Card is:  " + Computercard);

            if (Player1.getValue() ==Computercard.getValue()) {
                System.out.println("Its a tie");
                tie++;
            }
            if (Player1.getValue() > Computercard.getValue()) {
                System.out.println("Player 1 Wins this round");
                P1Win++;

            } else if(Computercard.getValue() > Player1.getValue()){
                System.out.println("Player 2 wins this round");
                P2Win++;
            }

            round++;
            System.out.println(cards);

        }
        System.out.println(" Player One Win Count: " + P1Win + "\n Player Two Win Count: " + P2Win +"\n Tie Count: " + tie);

        if(P1Win > P2Win && P1Win >tie){
            System.out.println("The winner of this match is Player One");
        } else if (P2Win > P1Win && P2Win > tie) {
            System.out.println("The winner of this match is Player Two");

        } else if (tie>P1Win && tie >P2Win) {
            System.out.println("The match is a tie");
        }
    }


}