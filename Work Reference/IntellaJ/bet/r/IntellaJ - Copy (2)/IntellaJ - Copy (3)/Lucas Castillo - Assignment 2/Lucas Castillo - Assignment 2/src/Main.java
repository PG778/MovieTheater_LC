import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;




/*
Name: Lucas Castillo
Assignment: Magic Word
Description: Reads names from a file and prints them alphabetically to the console. Also allows users to add names to the file.
*/



public class Main {

    public static void main(String[] args) throws IOException {




        String words[] = new String[0];

        Scanner scanner = new Scanner(new File("word.txt"));
        ArrayList<String> name = new ArrayList<>();

        // Populates names array
        while (scanner.hasNext())
        {
            String bidder = scanner.nextLine();
            words = bidder.split(":");
            name.add(words[0]);
        }
        Random rand = new Random();
        int nameIndex = rand.nextInt(name.size());





        // Gets the whole line from a file, then sets another variable to the password/element after the delimeter
        String Pass = Files.readAllLines(Paths.get("word.txt")).get(nameIndex);
        String password = Pass.split(":")[1];

        Scanner scan = new Scanner(System.in);
        //Loops until the password enters is correct and matchs the required password.
            while(true){
                System.out.println("Enter the password for " + name.get(nameIndex));
                String inp =  scan.nextLine();

                if(password.equals(inp)){
                    break;
                }
            }
        System.out.println( "Congrats Bozo");










    }



    }
