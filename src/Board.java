import java.awt.*;

public class Board {
    public static void init(){
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledSquare(0.5,0.5,0.5);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setPenRadius(0.004);
        //Starting columns (Margins of 0.001 between card and border, 0.02 from card and edge)
        StdDraw.rectangle(0.93,0.91,0.055, 0.075);
        StdDraw.rectangle(0.81,0.91,0.055, 0.075);
        StdDraw.rectangle(0.69,0.91,0.055, 0.075);
        StdDraw.rectangle(0.57,0.91,0.055, 0.075);
        StdDraw.rectangle(0.45,0.91,0.055, 0.075);
        StdDraw.rectangle(0.33,0.91,0.055, 0.075);
        StdDraw.rectangle(0.21,0.91,0.055, 0.075);
        //Starting deck and pile
        StdDraw.rectangle(0.07,0.40,0.055, 0.075);
        StdDraw.rectangle(0.07,0.56,0.055, 0.075);
        //Finishing suit piles
        StdDraw.rectangle(0.07,0.09,0.055, 0.075);
        StdDraw.rectangle(0.19,0.09,0.055, 0.075);
        StdDraw.rectangle(0.31,0.09,0.055, 0.075);
        StdDraw.rectangle(0.43,0.09,0.055, 0.075);
    }
}
