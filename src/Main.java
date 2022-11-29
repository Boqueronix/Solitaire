public class Main {
    public static Card[][] ordered = new Card[4][13];
    public static Card[] deck = new Card[52];
    public static void main(String[] args) {
        //System.out.println("IK how to github");
        Board.init();
        for (int i = 0; i < 52; i++) {
            ordered[i / 13][i % 13] = new Card(i / 13, (i % 13) + 1);
        }
        for (int i = 0; i < 52; i++) {
            System.out.println(ordered[i / 13][i % 13]);
        }

    }
}