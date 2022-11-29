import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Main {
    public static Card[][] ordered = new Card[4][13];
    public static Card[] deck = new Card[52];
    public static String backUrl = "/svg_playing_cards/backs/back" + ((int) (Math.random() * 12)) + ".png";
    public static void main(String[] args) {
        Board.init();
        for (int i = 0; i < 52; i++) {
            ordered[i / 13][i % 13] = new Card(i / 13, (i % 13) + 1);
        }
        deck = shuffle(ordered);
    }
    public static Card @NotNull [] shuffle(Card[] @NotNull [] tbs){
        int[] used = new int[tbs.length * tbs[0].length];
        int index = -1;
        Card[] tbr = new Card[used.length];
        Arrays.fill(used, -1);
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
    public static Card @NotNull [] shuffle(Card @NotNull [] tbs){
        int[] used = new int[tbs.length];
        int index = -1;
        Card[] tbr = new Card[used.length];
        Arrays.fill(used, -1);
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