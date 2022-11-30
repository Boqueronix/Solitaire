import java.awt.*;

public class Board {
    //Starting columns (Margins of 0.001 between card and border, 0.02 from card and edge)
    public static Column col7 = new Column(0.93,0.91);
    public static Column col6 = new Column(0.81,0.91);
    public static Column col5 = new Column(0.69,0.91);
    public static Column col4 = new Column(0.57,0.91);
    public static Column col3 = new Column(0.45,0.91);
    public static Column col2 = new Column(0.33,0.91);
    public static Column col1 = new Column(0.21,0.91);
    public static Column[] columns = {col1, col2, col3, col4, col5, col6, col7};;
    //Starting deck and pile
    public static Frame deckFrame = new Frame(0.07,0.56);
    public static OpenFrame pileFrame = new OpenFrame(0.07,0.40);
    //Finishing suit piles
    public static Suiter spadeFrame = new Suiter(0.07,0.09);
    public static Suiter diamondFrame = new Suiter(0.19,0.09);
    public static Suiter clubFrame = new Suiter(0.31,0.09);
    public static Suiter heartFrame = new Suiter(0.43,0.09);
    public static Suiter[] openPiles = {spadeFrame,diamondFrame,clubFrame,heartFrame};
    public static void init(){
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledSquare(0.5,0.5,0.5);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setPenRadius(0.004);
        for (Frame col:columns) {
            col.draw();
        }
        deckFrame.draw();
        pileFrame.draw();
        for (Frame pile:openPiles) {
            pile.draw();
        }
    }
}
