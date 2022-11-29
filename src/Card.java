public class Card {
    //Suit of the card: 0 = Spades, 1 = Diamonds, 2 = Clubs, 3 = Hearts
    public int suit;
    //Number of the card: 1 = Ace, 2-10 = 2-10, 11 = Jack, 12 = Queen, 13 = King
    public int value;
    public boolean black;
    public String url;
    public String suitStr;
    public String valueStr;
    public Card(int sui, int val) {
        suit = sui;
        value = val;
        black = suit % 2 == 0;
        if (suit == 0){
            suitStr = "spades";
        } else if (suit == 1){
            suitStr = "diamonds";
        } else if (suit == 2){
            suitStr = "clubs";
        } else{
            suitStr = "hearts";
        }
        if (value == 1){
            valueStr = "ace";
        } else if (value == 11){
            valueStr = "jack";
        } else if (value == 12){
            valueStr = "queen";
        } else if (value == 13){
            valueStr = "king";
        } else {
            valueStr = "" + value;
        }
        url = "/svg_playing_cards/fronts/" + suitStr + "_" + valueStr + ".png";
    }
    public String toString(){
        return valueStr + " of " + suitStr + ".";
    }
}
