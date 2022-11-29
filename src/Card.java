public class Card {
    //Suit of the card: 0 = Spades, 1 = Diamonds, 2 = Clubs, 3 = Hearts
    public int suit;
    //Number of the card: 1 = Ace, 2-10 = 2-10, 11 = Jack, 12 = Queen, 13 = King
    public int value;
    public Card(int sui, int val) {
        suit = sui;
        value = val;
    }
    public String toString(){
        String suitStr;
        String valueStr;
        if (suit == 0){
            suitStr = "Spades";
        } else if (suit == 1){
            suitStr = "Diamonds";
        } else if (suit == 2){
            suitStr = "Clubs";
        } else{
            suitStr = "Hearts";
        }
        if (value == 1){
            valueStr = "Ace";
        } else if (value == 11){
            valueStr = "Jack";
        } else if (value == 12){
            valueStr = "Queen";
        } else if (value == 13){
            valueStr = "King";
        } else {
            valueStr = "" + value;
        }
        return valueStr + " of " + suitStr + ".";
    }
}
