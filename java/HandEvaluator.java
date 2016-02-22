
public class HandEvaluator {



    public static void main(String args[]) {
//        String cards = "As2s3s4s5s7h3h8sAc9d";
        String cards = "AsAcKcKsKd2h3h4h5h6h";

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

        System.out.println("Check for two pair");
        System.out.println(hand1.isTwoPair() + "---" + hand1.printCards());

        System.out.println("Check for full house");
        System.out.println(hand1.isBoat() + "---" + hand1.printCards());

        System.out.println("Check for three of a kind");
        System.out.println(hand1.isTrips() + "---" + hand1.printCards());

        System.out.println("Check for one pair");



    }

    public int compareHands(Hand hand1, Hand hand2) {

        return 0;
    }



}
