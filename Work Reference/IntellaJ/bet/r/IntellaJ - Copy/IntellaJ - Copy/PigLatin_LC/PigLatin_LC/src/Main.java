import java.util.*;
/*
    Lucas Castillo
    Pig Latin Project
    



 */
public class Main {
    public static void main(String[] args) {
        // Scanner initialize
        Scanner input = new Scanner(System.in);

        System.out.println("Enteer a word or setence(s)");
        String word = input.nextLine();
        String [] array = word.split(" ");



        String PigLatin = "";

        for (String words : array) {
            String pigLatinWord = word.substring(1) + word.charAt(0) + "ay";
            PigLatin += pigLatinWord + " ";
        }

        System.out.println(PigLatin);






    }
}