public class Card {
    int[] deck = new int[52];
    String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"
    };

    String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
    };

    int round = 0;

    String pCard = "";

     public Card(int _round){
         round = _round;

    }

    public void setDeck(){
        // initialize deck
        int n = SUITS.length * RANKS.length;
        String[] deck = new String[n];
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                deck[SUITS.length*i + j] = RANKS[i] + " of " + SUITS[j];
            }
        }
        // Shuffles Deck
        for (int i = 0; i < n; i++) {
            int r = i + (int) (Math.random() * (n-i));
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }

    }


    public void setRound(){
         round += round +1;
    }

    public int getRound(){
         return round;
    }


}
