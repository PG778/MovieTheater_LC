import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/*
Lucas Castillo

 */

public class Main {
    public static void main(String[] args) throws IOException {
        Utils obj = new Utils();
       // Read the encrypted message from the file
        File inputFile = new File("src/Original.txt");
        Scanner scanner = new Scanner(inputFile);
        String encryptedMessage = scanner.nextLine();
        scanner.close();

        // Decrypt the message using the Caesar cipher
        String decryptedMessage = obj.decrypt(encryptedMessage);
        System.out.println( decryptedMessage);

        // Encrypt the message again and write it to a new file
        String encryptedAgain = obj.encrypt(decryptedMessage);
        File outputFile = new File("src/ReEncrypted.txt");
        FileWriter writer = new FileWriter(outputFile);
        writer.write(encryptedAgain);
        writer.close();


    }


    }
