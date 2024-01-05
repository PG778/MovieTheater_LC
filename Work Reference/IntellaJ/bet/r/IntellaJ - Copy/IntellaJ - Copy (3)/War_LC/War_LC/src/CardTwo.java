public class CardTwo {

        private String suit;
        private int value;

        public CardTwo(String suit, int value) {
            this.suit = suit;
            this.value = value;
        }

        public String getSuit() {
            return suit;
        }

        public int getValue() {
            return value;
        }

        public String toString() {
            return value + " of the suiit of " + suit;
        }




}
