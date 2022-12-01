import java.awt.*;

public class Column extends Frame{
    public Column(double x, double y){
        super(x,y);
    }
    public void draw() {
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(coords[0], coords[1], 0.05, 0.6);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setPenRadius(0.004);
        StdDraw.rectangle(coords[0], coords[1], 0.055, 0.075);
    }
    public void drawContents(){
        contents[contents.length - 1].revealed = true;
        for (Card c: contents) {
            c.draw();
        }
    }
}
