import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

public class Main {
    public static Card[][] ordered = new Card[4][13];
    public static Card[] all;
    public static Card[] deck;
    public static Object selectedObj;
    public static boolean mousePressed = false;
    public static boolean selected = false;
    public static String backUrl = "/svg_playing_cards/backs/back" + ((int) (Math.random() * 12)) + ".png";
    public static void main(String[] args) {
        Board.init();
        for (int i = 0; i < 52; i++) {
            ordered[i / 13][i % 13] = new Card(i / 13, (i % 13) + 1);
        }
        all = shuffle(ordered);
        deck = all;
//        for (Card c: deck) { c.revealed = true;}
        init();
        while (true){
            if (StdDraw.isMousePressed() && !mousePressed){
                mousePressed = true;
                if (selected) {
                    moveAttempt(StdDraw.mouseX, StdDraw.mouseY);
                } else {
                    selectedObj = select(StdDraw.mouseX, StdDraw.mouseY);
                }
            } else if (!StdDraw.isMousePressed() && mousePressed) {
                mousePressed = false;
            }
        }
    }

    private static Object select(double x, double y) {
        for (Card car: all) {
            if ((x > car.hitbox()[0] && x < car.hitbox()[2]) && (y > car.hitbox()[1] && y < car.hitbox()[3])){
                System.out.println("You clicked on the" + car);

            }
        }
        return null;
    }

    private static void moveAttempt(double x, double y) {
//        find target frame
//        selectedObj.move(target frame)
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
        if (tbs.length - n >= 0) { System.arraycopy(tbs, n, tbr, 0, tbs.length - n); }
        return tbr;
    }
    public static void moveTo(Card car, Frame fra){
        Card[] tbr = new Card[]{};
        try {
            tbr = new Card[fra.contents.length + 1];
            System.arraycopy(fra.contents, 0, tbr, 0, fra.contents.length);
            tbr[fra.contents.length] = car;
        } catch (Exception e) {
            if (fra.contents.length == 0) {
                tbr = new Card[]{car};
            }
        }
        fra.contents = tbr;
        boolean col = false;
        for (int i = 0; i < 7; i++) {
            if (fra.equals(Board.columns[i])){
                for (int j = 0; j < Board.columns[i].contents.length; j++) {
                    Board.columns[i].contents[j].coords = new double[] {fra.coords[0], fra.coords[1] - j * 0.03};
                    Board.columns[i].contents[j].draw();
                }
                col = true;
                break;
            }
        }
        if (!col) {
            car.draw();
        }
    }
    public static void init(){
        for (int i = 0; i < 7; i++) {
            deck[i].revealed = true;
            for (int j = 0; j < i + 1; j++) {
                moveTo(deck[j], Board.columns[i]);
            }
            deck = removeFirstX(deck,i + 1);
            System.out.println(deck.length);
        }
        Board.deckFrame.contents = new Card[deck.length];
        System.arraycopy(deck,0,Board.deckFrame.contents,0,deck.length);
        StdDraw.picture(Board.deckFrame.coords[0], Board.deckFrame.coords[1], Main.backUrl, 0.1, 0.14);
    }
//    public method
}