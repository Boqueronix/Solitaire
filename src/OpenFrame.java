public class OpenFrame extends Frame{
    public OpenFrame(double x, double y) {
        super(x, y);
    }
    public void drawContents(){
        for (Card c: contents) {
            c.revealed = true;
            c.draw();
        }
    }
}
