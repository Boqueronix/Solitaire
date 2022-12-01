import org.jetbrains.annotations.NotNull;
import java.util.*;

public class Main {
    public static Card[][] ordered = new Card[4][13];
    public static Card[] allCards = new Card[52];
    public static Object selectedObj;
    public static boolean mousePressed = false;
    public static String backUrl = "/svg_playing_cards/backs/back" + ((int) (Math.random() * 12)) + ".png";
    public static void main(String[] args) {
        Board.init();

        for (int i = 0; i < 52; i++) {
            ordered[i / 13][i % 13] = new Card(i / 13, (i % 13) + 1);
            allCards[i] = ordered[i / 13][i % 13];
        }
        //allCards = shuffle(ordered);
        Board.deckFrame.contents = allCards;
        for (Card c: Board.deckFrame.contents) { c.revealed = true;}
        init();
        while (true){
            if (StdDraw.isMousePressed() && !mousePressed){
                mousePressed = true;
                if (selectedObj != null) {
                    try {
                        moveAttempt(StdDraw.mouseX, StdDraw.mouseY);
                    } catch (Exception e){
                        System.out.println("You clicked on nothing");
                        selectedObj = null;
                    }
                } else {
                    try {
                        selectedObj = select(StdDraw.mouseX, StdDraw.mouseY);
                    } catch (Exception e){
                        System.out.println("You clicked on nothing");
                        selectedObj = null;
                    }
                }
            } else if (!StdDraw.isMousePressed() && mousePressed) {
                mousePressed = false;
            }
        }
    }

    private static Object select(double x, double y) {
        Frame tbrF = findFrame(x, y);
        Object tbr = null;
        /* Also try (tbrF.contents[tbrF.contents.length - 1].revealed) to Include all open piles*/
        if (tbrF.getClass().toString().equals("class Column") && tbrF.contents[tbrF.contents.length - 1].revealed){
            for (int i = tbrF.contents.length - 1; i >= 0; i--) {
                if ((x > tbrF.contents[i].hitbox()[0] && x < tbrF.contents[i].hitbox()[2]) && (y > tbrF.contents[i].hitbox()[1] && y < tbrF.contents[i].hitbox()[3])){
                    System.out.println("You clicked on the " + tbrF.contents[i]);
                    tbr = tbrF.contents[i];
                    break;
                }
            }
        } else if (tbrF.getClass().toString().equals("class Frame")) {
            tbr = tbrF;
            System.out.println("You clicked on the deck");
        }
        return tbr;
    }

    private static void moveAttempt(double x, double y) {
        System.out.println("MA run");
        if (selectedObj.getClass().toString().equals("class Card")){
            Card temp = (Card) selectedObj;
            Frame src = cardToFrame(temp);
            int index = -1;
            for (int i = 0; i < src.contents.length; i++){
                if (src.contents[i].equals(temp)){
                    index = i;
                    break;
                }
            }
            int l = src.contents.length;
            for (int i = index; i < l; i++) {
                System.out.println(src.contents.length);
                moveTo(src.contents[i], findFrame(x, y));
                removeFrom(src.contents[i], src);
            }
        } else {
            // Flip a card from the deck or refill deck
        }
        selectedObj = null;
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
    //Uses allCards the cards in the ordered deck to make a new randomly assigned deck (1D Parameter)
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
    public static Card[] removeLastX(Card[] tbs, int n){
        Card[] tbr = new Card[tbs.length - n];
        if (tbs.length - n >= 0) { System.arraycopy(tbs, 0, tbr, 0, tbs.length - n); }
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
            Board.deckFrame.contents[i].revealed = true;
            for (int j = 0; j < i + 1; j++) {
                moveTo(Board.deckFrame.contents[j], Board.columns[i]);
            }
            Board.deckFrame.contents = removeFirstX(Board.deckFrame.contents,i + 1);
            System.out.println(Board.deckFrame.contents.length);
        }
        for (Card c: Board.deckFrame.contents) {c.coords = Board.deckFrame.coords;}
        StdDraw.picture(Board.deckFrame.coords[0], Board.deckFrame.coords[1], Main.backUrl, 0.1, 0.14);
    }
    public static Frame cardToFrame(Card car){
        for (Frame fra: Frame.all){
            for (Card c: fra.contents) {
                if (c.equals(car)){
                    return fra;
                }
            }
        }
        return null;
    }
    public static Frame findFrame(double x, double y){
        Frame tbr = null;
        for (Card car: allCards) {
            if ((x > car.hitbox()[0] && x < car.hitbox()[2]) && (y > car.hitbox()[1] && y < car.hitbox()[3])){
                tbr = cardToFrame(car);
                break;
            }
        }
        return tbr;
    }
    public static void removeFrom(Card car, Frame fra){
        int index = -1;
        for (int i = 0; i < fra.contents.length; i++) {
            if (fra.contents[i].equals(car)){
                index = i;
                break;
            }
        }
        Card[] tbr = new Card[fra.contents.length -1];
        for (int i = 0; i < fra.contents.length; i++) {
            if (i < index){
                tbr[i] = fra.contents[i];
            } else if (i > index) {
                tbr[i - 1] = fra.contents[i];
            }

        }
        fra.contents = tbr;
        fra.draw();
        fra.drawContents();
    }
}