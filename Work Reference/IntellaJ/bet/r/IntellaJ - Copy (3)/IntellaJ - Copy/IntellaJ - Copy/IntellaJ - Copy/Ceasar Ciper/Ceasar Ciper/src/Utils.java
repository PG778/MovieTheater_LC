public class Utils {

    // variables to be used
    private String encodedMessage, plainText;

    // constructor
    public Utils (){
        encodedMessage = this.encodedMessage;
        plainText = this.plainText;
    }

    // this is the decrypt method, this method ttakes the input of the encrypted text, (The String vriable in main) and will go through a loop
    // 13 times to meet the assignment requirements until it has gone thouh eac letter in the prompt
    // producing the correct output, setting that to a variable in main and printing it or the user to see
    public String decrypt(String encrypted) {
        StringBuilder decrypted = new StringBuilder();
        // loops through the loop 13 times
        for (int i = 0; i < encrypted.length(); i++) {
            char letter = encrypted.charAt(i);
            // compares the letters to see if its in the bounds od he alphabet
            if (letter >= 'a' && letter <= 'z') {
                letter = (char) ('a' + (letter - 'a' + 13) % 26);
            } else if (letter >= 'A' && letter <= 'Z') {
                letter = (char) ('A' + (letter - 'A' + 13) % 26);
            }
            decrypted.append(letter);
        }
        return decrypted.toString();
    }

    // does the same as the other metod, iterally just in reverse.
    public String encrypt(String plaintext) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char letter = plaintext.charAt(i);
            if (letter >= 'a' && letter <= 'z') {
                letter = (char) ('a' + (letter - 'a' + 13) % 26);
            } else if (letter >= 'A' && letter <= 'Z') {
                letter = (char) ('A' + (letter - 'A' + 13) % 26);
            }
            encrypted.append(letter);
        }
        return encrypted.toString();
    }

}

