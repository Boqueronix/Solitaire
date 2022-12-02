import org.jetbrains.annotations.NotNull;

import java.awt.*;
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
        allCards = shuffle(ordered);
        Board.deckFrame.contents = allCards;
        for (Card c: Board.deckFrame.contents) { c.revealed = false;}
        init();
        while (true){
            if (StdDraw.isMousePressed() && !mousePressed){
                mousePressed = true;
                if (selectedObj != null) {
                    try {
                        moveAttempt(StdDraw.mouseX, StdDraw.mouseY);
                    } catch (Exception e){
//                      System.out.println("You clicked on nothing");
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
                if ((x > tbrF.contents[i].hitbox()[0] && x < tbrF.contents[i].hitbox()[2]) && (y > tbrF.contents[i].hitbox()[1] && y < tbrF.contents[i].hitbox()[3]) && tbrF.contents[i].revealed){
                    System.out.println("You clicked on the " + tbrF.contents[i]);
                    tbr = tbrF.contents[i];
                    break;
                }
            }
        } else if (tbrF.getClass().toString().equals("class Frame")) {
            System.out.println("You clicked on the deck");
            if (Board.deckFrame.contents.length > 0) {
                moveTo(Board.deckFrame.contents[Board.deckFrame.contents.length - 1], Board.pileFrame);
                Board.pileFrame.drawContents();
                removeFrom(Board.deckFrame.contents[Board.deckFrame.contents.length - 1], Board.deckFrame);
                System.out.println(Board.pileFrame.contents.length);
            } else {
                for (int i = Board.pileFrame.contents.length - 1; i > - 1 ; i--) {
                    moveTo(Board.pileFrame.contents[i], Board.deckFrame);
                    removeFrom(Board.pileFrame.contents[Board.pileFrame.contents.length - 1], Board.pileFrame);
                }
                Board.deckFrame.drawContents();
            }
        } else if (tbrF.getClass().toString().equals("class OpenFrame")) {
            if (tbrF.contents.length != 0) {
                tbr = tbrF.contents[tbrF.contents.length - 1];
            }
        }
        return tbr;
    }

    private static void moveAttempt(double x, double y) {
        System.out.println("MA run");
        Card temp = (Card) selectedObj;
        Frame src = cardToFrame(temp);
        int l = src.contents.length;
        Card[] contentCopy = src.contents;
        if (selectedObj.getClass().toString().equals("class Card")){
            if (findFrame(x, y).getClass().toString().equals("class Column")) {
                if (canMove((Card) selectedObj, findFrame(x, y))) {
                    int index = -1;
                    for (int i = 0; i < src.contents.length; i++) {
                        if (src.contents[i].equals(temp)) {
                            index = i;
                            break;
                        }
                    }
                    for (int i = index; i < l; i++) {
                        System.out.println("Trying to move " + contentCopy[i]);
                        moveTo(contentCopy[i], findFrame(x, y));
                        removeFrom(contentCopy[i], src);
                    }
                } else {
                    System.out.println("Move illegal");
                }
            } else if (findFrame(x, y).getClass().toString().equals("class Suiter")){
                if (findFrame(x, y).contents.length == 0 && temp.value == 1){
                    System.out.println("Trying to move " + temp);
                    moveTo(temp, findFrame(x, y));
                    removeFrom(temp, src);
                } else if (findFrame(x, y).contents[findFrame(x, y).contents.length - 1].value + 1 == temp.value && findFrame(x, y).contents[findFrame(x, y).contents.length - 1].suit == temp.suit) {
                    System.out.println("Trying to move " + temp);
                    moveTo(temp, findFrame(x, y));
                    removeFrom(temp, src);
                }
                if (Board.heartFrame.contents.length + Board.diamondFrame.contents.length + Board.clubFrame.contents.length + Board.spadeFrame.contents.length == 52){
                    System.out.println("You win bitch");
                    winGame();
                }

            }
        } else {
            // NO
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
        if (fra.getClass().toString().equals("class OpenFrame")) {System.out.println("Flip " + car);}
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
        car.coords = fra.coords;
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
        for (Frame fra: Frame.all) {
            if ((x > fra.hitbox()[0] && x < fra.hitbox()[2]) && (y > fra.hitbox()[1] && y < fra.hitbox()[3])){
                return fra;
            }
        }
        for (Card car: allCards) {
            if ((x > car.hitbox()[0] && x < car.hitbox()[2]) && (y > car.hitbox()[1] && y < car.hitbox()[3])){
                return cardToFrame(car);
            }
        }
        return null;
    }
    public static void removeFrom(Card car, Frame fra){
        int index = fra.contents.length -1;
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
    public static boolean canMove(Card car, Frame fra){
        if (fra.contents.length == 0 && car.valueStr.equals("king")){
            return true;
        } else if (fra.contents.length != 0) {
            if (fra.contents[fra.contents.length -1].value - 1 == car.value && fra.contents[fra.contents.length -1].black != car.black){
               return true;
            }
        }
        return false;
    }
    public static void winGame(){
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.filledSquare(0.5,0.5,0.5);
        StdDraw.text(0.5, 0.5, "MF won!!!!");
    }
}