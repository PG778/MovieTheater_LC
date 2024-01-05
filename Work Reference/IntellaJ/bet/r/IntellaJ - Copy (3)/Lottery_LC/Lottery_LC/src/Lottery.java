import java.util.Arrays;
import java.util.Random;

public class Lottery {

    private int[] lotteryNumbers;
    private Random random;

    public Lottery() {
        random = new Random();
        lotteryNumbers = new int[4];
    }

    public void setLotteryNumbers() {
        for (int i = 0; i < 4; i++) {
            lotteryNumbers[i] = random.nextInt(10);
        }
    }
    public int[] getLotteryNumbers(){
        return lotteryNumbers;
    }
    private boolean hasPairInArray(int[] array) {
        int[] tempArray = Arrays.copyOf(array, 4);
        Arrays.sort(tempArray);
        for (int i = 0; i < 3; i++) {
            if (tempArray[i] == tempArray[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public int checkNums(int[] array) {
        int count = 0;
        int[] testLotteryNumbers = Arrays.copyOf(lotteryNumbers, 4);

        for (int i = 0; i < 4; i++) {
            if (array[i] == testLotteryNumbers[i]) {
                count++;
            }
        }

        if (count == 4) {
            return 10000;
        } else if (count == 3) {
            if (hasPairInArray(array)) {
                return 2500;
            } else {
                return 417;
            }
        } else if (count == 2) {
            if (hasPairInArray(array)) {
                return 833;
            } else {
                return 100;
            }
        } else if (count == 1) {
            return 10;
        } else {
            return 0;
        }
    }

}
