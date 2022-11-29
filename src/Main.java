public class Main {
    public static Card[][] ordered = new Card[4][13];
    public static Card[] deck = new Card[52];
    public static void main(String[] args) {
        Board.init();
        for (int i = 0; i < 52; i++) {
            ordered[i / 13][i % 13] = new Card(i / 13, (i % 13) + 1);
        }
        shuffle(ordered);
    }
    //Uses all the cards in the ordered deck to make a new randomly assigned deck (2D Parameter)
    public static Card[] shuffle(Card[][] tbs){
        int[] used = new int[tbs.length * tbs[0].length];
        int index = -1;
        Card[] tbr = new Card[used.length];
        for (int i = 0; i < used.length; i++) {
            used[i] = -1;
        }
        for (int i = 0; i < used.length; i++) {
            boolean found = false;
            while (!found){
                index = (int) (Math.random() * used.length);
                if (used[index] == -1) {
                    used[index] = index;
                    found = true;
                }
            }
            tbr[index] = tbs[i / tbs[0].length][i % tbs[0].length];
        }
        return tbr;
    }
    //Uses all the cards in the ordered deck to make a new randomly assigned deck (1D Parameter)
    public static Card[] shuffle(Card[] tbs){
        int[] used = new int[tbs.length];
        int index = -1;
        Card[] tbr = new Card[used.length];
        for (int i = 0; i < used.length; i++) {
            used[i] = -1;
        }
        for (int i = 0; i < used.length; i++) {
            boolean found = false;
            while (!found){
                index = (int) (Math.random() * used.length);
                if (used[index] == -1) {
                    used[index] = index;
                    found = true;
                }
            }
            tbr[index] = tbs[i];
        }
        return tbr;
    }
}