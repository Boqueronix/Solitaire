public class Column extends Frame{
    public Column(double x, double y){
        super(x,y);
    }
    public void drawContents(){
        contents[contents.length - 1].revealed = true;
        for (Card c: contents) {
            c.draw();
        }
    }
}
