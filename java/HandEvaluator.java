
public class HandEvaluator {



    public static void main(String args[]) {
        String cards = "AcAsKdKcTh9h7d6s3d5c";

        Hand hand1 = new Hand(cards.substring(0, 10));
        Hand hand2 = new Hand(cards.substring(10));
        System.out.println(hand1.toString());
        System.out.println(hand2.toString());
    }



}
