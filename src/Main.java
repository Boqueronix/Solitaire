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
        init();
        System.out.println(deck.length);
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
    public static Card[] removeFirstX(Card[] tbs, int n){
        Card[] tbr = new Card[tbs.length - n];
        for (int i = 0; i < tbs.length - n; i++) {
            tbr[i] = tbs[i + n];
        }
        return tbr;
    }
    public static void moveTo(Card car, Frame fra){
        Card[] tbr = new Card[]{};
        try {
            tbr = new Card[fra.contents.length + 1];
            System.out.println("" + fra);
            for (int i = 0; i < fra.contents.length; i++) {
                tbr[i] = fra.contents[i];
            }
            System.out.println("test");
            tbr[fra.contents.length] = car;
            System.out.println("Finished try");
        } catch (Exception e) {
            if (fra.contents.length == 0) {
                System.out.println("Starting " + fra);
                tbr = new Card[]{car};
            }
        }
        fra.contents = tbr;
        for (Card c: fra.contents) {
            System.out.println(c);
            System.out.println(c.revealed);
        }
        car.coords = fra.coords;
        car.draw();
    }
    public static void init(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i + 1; j++) {
                moveTo(deck[j], Board.columns[i]);
                //System.out.println("moved " + deck[j] + " to column " + Board.columns[i]);
                deck[i].revealed = true;
                deck[j].draw();
            }
            deck = removeFirstX(deck,i + 1);
        }
    }
}