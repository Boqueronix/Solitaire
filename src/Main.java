public class Main {
    public static Card[][] ordered = new Card[4][13];
    public static Card[] deck = new Card[52];
    public static void main(String[] args) {
        //System.out.println("IK how to github");
        Board.init();
        for (int i = 0; i < 52; i++) {
            ordered[i / 13][i % 13] = new Card(i / 13, (i % 13) + 1);
        }
//        for (int i = 0; i < 52; i++) {
//            System.out.println(ordered[i / 13][i % 13]);
//        }
        shuffle();
        for (int i = 0; i < 52; i++) {
            System.out.println(deck[i]);
        }
    }
    public static void shuffle(){
        int[] used = new int[52];
        int index = -1;
        for (int i = 0; i < 52; i++) {
            used[i] = -1;
        }
        for (int i = 0; i < 52; i++) {
            boolean found = false;
            while (!found){
                index = (int) (Math.random() * 52);
                if (used[index] == -1) {
                    used[index] = index;
                    found = true;
                }
            }
            deck[index] = ordered[i / 13][i % 13];
//            System.out.println("At index: " + index + " is the " + deck[index]);
        }
    }
}