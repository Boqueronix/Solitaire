import java.awt.*;

public class Frame {
    public double[] coords = new double[2];
    public Card[] contents = new Card[0];
    public Frame(double x, double y){
        coords[0] = x;
        coords[1] = y;
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setPenRadius(0.004);
        StdDraw.rectangle(coords[0], coords[1],0.055, 0.075);
    }
    public void draw(){
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setPenRadius(0.004);
        StdDraw.rectangle(coords[0], coords[1],0.055, 0.075);
    }
    public String toString(){
        return "Frame at " + coords[0] + ", " + coords[1];
    }
}
