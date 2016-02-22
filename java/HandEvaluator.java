
public class HandEvaluator {



    public static void main(String args[]) {
        String cards = "As2s3s4s5s7h3h8sAc9d";

        Hand hand1 = new Hand(cards.substring(0, 10));
        Hand hand2 = new Hand(cards.substring(10));
//        System.out.println(hand1.toString());
//        System.out.println(hand2.toString());
        System.out.println(hand1.printCards());
        System.out.println(hand2.printCards());


        System.out.println("Check for flushes");
        System.out.println(hand1.isFlush() + "---" + hand1.printCards());
        System.out.println(hand2.isFlush() + "---" + hand2.printCards());

        System.out.println("Check for straights");
        System.out.println(hand1.isStraight() + "---" + hand1.printCards());
        System.out.println(hand2.isStraight() + "---" + hand2.printCards());

        Card c = new Card('Q', 'c');
        System.out.println(c.getValue());
        Card c2 = new Card('K', 'd');
        System.out.println(c2.getValue());
        Card c3 = new Card('9', 'h');
        System.out.println(c3.getValue());

    }

    public int compareHands(Hand hand1, Hand hand2) {

        return 0;
    }



}
