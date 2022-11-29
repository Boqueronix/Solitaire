import java.awt.*;

public class Board {
    public static void init(){
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledSquare(0.5,0.5,0.5);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setPenRadius(0.002 * 2);
        StdDraw.rectangle(0.93,0.8875,0.05, 0.07);
        StdDraw.rectangle(0.81,0.8875,0.05, 0.07);
        StdDraw.rectangle(0.69,0.8875,0.05, 0.07);
        StdDraw.rectangle(0.57,0.8875,0.05, 0.07);
        StdDraw.rectangle(0.45,0.8875,0.05, 0.07);
        StdDraw.rectangle(0.33,0.8875,0.05, 0.07);
        StdDraw.rectangle(0.21,0.8875,0.05, 0.07);
    }
}
