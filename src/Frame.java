import java.awt.*;

public class Frame {
    public double[] coords = new double[2];
    public Card[] contents = new Card[0];
    public static Frame[] all = new Frame[0];
    public Frame(double x, double y){
        coords[0] = x;
        coords[1] = y;
        Frame[] tbr;
        try {
            tbr = new Frame[all.length + 1];
            System.arraycopy(all, 0, tbr, 0, all.length);
            tbr[all.length] = this;
            all = tbr;
        } catch (Exception e) {
            if (all.length == 0) {
                all = new Frame[]{this};
            }
        }
    }
    public void draw(){
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(coords[0],coords[1],0.05,0.07);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setPenRadius(0.004);
        StdDraw.rectangle(coords[0], coords[1],0.055, 0.075);
    }
    public void drawContents(){
        for (Card c: contents) {
            c.revealed = false;
            c.draw();
        }
    }
    public double[] hitbox() {
        return new double[] {coords[0] - 0.055, coords[1] - 0.075, coords[0] + 0.055, coords[1] + 0.075};
    }
    public String toString(){
        return "Frame at " + coords[0] + ", " + coords[1];
    }
}
