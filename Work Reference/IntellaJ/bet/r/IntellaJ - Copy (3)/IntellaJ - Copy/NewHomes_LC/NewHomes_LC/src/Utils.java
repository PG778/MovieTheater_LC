import java.io.*;
import java.util.Scanner;

public class Utils {

    public static final String INFO = "src\\lotr.txt";
    public static final String DELIMITER = ":";









    public static void updateRecord(String name,String loc) throws IOException {
        // created two file objects, tempp is for the temp.txt and original is tte lotr.txt
        String tempFile = "src\\temp.txt";
        File originalFile = new File("src\\lotr.txt");
        File newFile = new File(tempFile);

        try {
            FileWriter fileWriter = new FileWriter(tempFile, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Scanner inputFile = new Scanner(originalFile);

            while (inputFile.hasNextLine()) {
                String[] split = inputFile.nextLine().split(DELIMITER);
                Info _info = new Info(split[0],split[1],loc,split[3]);
                String currentName = split[0];

                if(currentName.equals(name)){
                    //Print edited line to file
                    System.out.println(_info.createOutputString());
                    printWriter.println(_info.createOutputString());


                }
                else{
                    // Print original line to file
                    StringBuilder newString = new StringBuilder();
                    for(String s : split){
                        newString.append(s).append(DELIMITER);
                    }
                    printWriter.println(newString);
                }
            }

            inputFile.close();
            printWriter.flush();
            printWriter.close();
            originalFile.delete();
            File dump = new File(INFO);
            newFile.renameTo(dump);





        } catch (Exception e){
        System.out.println("Error " + e.fillInStackTrace());
    }

    }
}
