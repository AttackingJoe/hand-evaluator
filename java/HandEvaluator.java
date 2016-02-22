
public class HandEvaluator {

    private String cards;
    private Hand hand1, hand2;



    public static void main(String args[]) {
//        String cards = "As2s3s4s5s7h3h8sAc9d";
//        String cards = "AsAcKcKsKdKh3h4h5h9h";
//
//        Hand hand1 = new Hand(cards.substring(0, 10));
//        Hand hand2 = new Hand(cards.substring(10));
//        System.out.println(hand1.toString());
//        System.out.println(hand2.toString());
//        System.out.println(hand1.printCards());
//        System.out.println(hand2.printCards());
//
//
//        System.out.println("Check for flushes");
//        System.out.println(hand1.isFlush() + "---" + hand1.printCards());
//        System.out.println(hand2.isFlush() + "---" + hand2.printCards());
//
//        System.out.println("Check for straights");
//        System.out.println(hand1.isStraight() + "---" + hand1.printCards());
//        System.out.println(hand2.isStraight() + "---" + hand2.printCards());
//
//        System.out.println("Check for two pair");
//        System.out.println(hand1.isTwoPair() + "---" + hand1.printCards());
//
//        System.out.println("Check for full house");
//        System.out.println(hand1.isBoat() + "---" + hand1.printCards());
//
//        System.out.println("Check for three of a kind");
//        System.out.println(hand1.isTrips() + "---" + hand1.printCards());

//        System.out.println("Check for one pair");

        System.out.println("MOMENT OF TRUTH");

        HandEvaluator eval = new HandEvaluator("9h9c9s3d3cAsAcKcKsKd");
        int winner = eval.compareHands();

        if(winner == 1)
            System.out.println("OKAY IT SEEMS TO BE WORKING");
        else if(winner == 2)
            System.out.println("Crap something went wrong");
        else
            System.out.println("Wow I REALLY messed up");




    }

    public HandEvaluator(String cards) {
        this.cards = cards;
        hand1 = new Hand(cards.substring(0, 10));
        hand2 = new Hand(cards.substring(10));
    }

    public int compareHands() {
        if(hand1.getRank().compareTo(hand2.getRank()) > 0)
            return 1; // hand1 wins
        if(hand1.getRank().compareTo(hand2.getRank()) < 0)
            return 2; // hand2 wins
        else { // Both have the same rank need to find the better hand when both are the same
            return getWinner(hand1, hand2);
        }
    }

    private int getWinner(Hand hand1, Hand hand2) {
        // We know that both ranks are equivalent
        switch(hand1.getRank()) {
            case HIGHCARD:
                return findHighestKicker(hand1, hand2);
            case ONEPAIR:
                return findBetterPair(hand1, hand2);
            case TWOPAIR:
                return findBetterTwoPair(hand1, hand2);
            case TRIPS:
                return findBetterTrips(hand1, hand2);
            case STRAIGHT:
                return findHighestKicker(hand1, hand2);
            case FLUSH:
                return findHighestKicker(hand1, hand2);
            case BOAT:
                return findBetterTrips(hand1, hand2);
            case STRFLUSH:
                return findHighestKicker(hand1, hand2);
            default:
                return 0; // it was a tie
        }

//        return 0;
    }

    // From here on all methods assume that ranks for the hands are equal

    // Used for HIGHCARD, STRAIGHT, FLUSH, STRFLUSH
    private int findHighestKicker(Hand hand1, Hand hand2) {
        // The last card should be the highest, so go from there
        for(int i = 4; i > -1; --i) {
            if(hand1.getCardAt(i).getIRank() > hand2.getCardAt(i).getIRank())
                return 1; // hand 1 is better than hand 2
            else if(hand1.getCardAt(i).getIRank() < hand2.getCardAt(i).getIRank())
                return 2; // hand 2 is better than hand 1
        }
        return 0; // tie
    }

    private int findHighestKickerIgnoreMult(Hand hand1, Hand hand2) {
        int hand1Unique[] = hand1.findUnique();
        int hand2Unique[] = hand2.findUnique();

        // arrays are sorted from low to high
        for(int i = 4; i > -1; --i) {
            if(hand1Unique[i] > hand2Unique[i])
                return 1; // hand 1 is better than hand 2
            if(hand1Unique[i] < hand2Unique[i])
                return 2; // hand 2 is better than hand 1
        }
        return 0; // tie
    }

    private int findBetterPair(Hand hand1, Hand hand2) {
        int hand1Pair = -1, hand2Pair = -1;
        for(int i = 0; i < 5 - 1; ++i) {
            if(hand1.getCardAt(i).getIRank() == hand1.getCardAt(i+1).getIRank()) {
                hand1Pair = hand1.getCardAt(i).getIRank();
                break;
            }
        }
        for(int i = 0; i < 5 - 1; ++i) {
            if(hand2.getCardAt(i).getIRank() == hand2.getCardAt(i+1).getIRank()) {
                hand2Pair = hand2.getCardAt(i).getIRank();
                break;
            }
        }
        if(hand1Pair > hand2Pair)
            return 1; // hand 1 is better than hand 2
        else if(hand1Pair < hand2Pair)
            return 2; // hand 2 is better than hand 1
        return findHighestKickerIgnoreMult(hand1, hand2);
    }

    private int findBetterTwoPair(Hand hand1, Hand hand2) {
        int hand1PairOne = -1, hand1PairTwo = -1, hand2PairOne = -1, hand2PairTwo = -1;
        for(int i = 0; i < 4; ++i) {
            if(hand1.getCardAt(i).getIRank() == hand1.getCardAt(i+1).getIRank()) {
                if(hand1PairOne == -1) {
                    hand1PairOne = hand1.getCardAt(i).getIRank();
                } else {
                    hand1PairTwo = hand1.getCardAt(i).getIRank();
                }
            }
            if(hand2.getCardAt(i).getIRank() == hand2.getCardAt(i+1).getIRank()) {
                if(hand2PairOne == -1) {
                    hand2PairOne = hand2.getCardAt(i).getIRank();
                } else {
                    hand2PairTwo = hand2.getCardAt(i).getIRank();
                }
            }
        }

        // Pair two should be the higher pair (sorted from low to high)
        if(hand1PairTwo > hand2PairTwo)
            return 1; // hand 1 is better than hand 2
        if(hand1PairTwo < hand2PairTwo)
            return 2; // hand 2 is better than hand 1
        if(hand1PairOne > hand2PairOne)
            return 1; // hand 1 is better than hand 2
        if(hand1PairOne < hand2PairOne)
            return 2; // hand 2 is better than hand 1

        return findHighestKickerIgnoreMult(hand1, hand2);
    }

    // Also for Quads
    private int findBetterTrips(Hand hand1, Hand hand2) {
        int hand1Trips = -1, hand2Trips = -1;
        for(int i = 0; i < 5 - 2; ++i) {
            if(hand1.getCardAt(i).getIRank() == hand1.getCardAt(i+1).getIRank() && hand1.getCardAt(i).getIRank() == hand1.getCardAt(i+2).getIRank()) {
                hand1Trips = hand1.getCardAt(i).getIRank();
                break;
            }
        }
        for(int i = 0; i < 5 - 2; ++i) {
            if(hand2.getCardAt(i).getIRank() == hand2.getCardAt(i+1).getIRank() && hand2.getCardAt(i).getIRank() == hand2.getCardAt(i+2).getIRank()) {
                hand2Trips = hand2.getCardAt(i).getIRank();
                break;
            }
        }

        if(hand1Trips > hand2Trips)
            return 1; //hand 1 is better than hand 2
        if(hand1Trips < hand2Trips)
            return 2; // hand 2 is better than hand 1
        return findHighestKickerIgnoreMult(hand1, hand2);
    }

    private int findBetterQuads(Hand hand1, Hand hand2) {
        int hand1Quad = -1, hand2Quad = -1;
        int hand1Unique[] = hand1.findUnique();
        int hand2Unique[] = hand2.findUnique();

        if(hand1.getCardAt(0).getIRank() == hand1Unique[4])
            hand1Quad = hand1.getCardAt(1).getIRank();
        else
            hand1Quad = hand1.getCardAt(0).getIRank();

        if(hand2.getCardAt(0).getIRank() == hand2Unique[4])
            hand2Quad = hand2.getCardAt(1).getIRank();
        else
            hand2Quad = hand2.getCardAt(0).getIRank();

        if(hand1Quad > hand2Quad)
            return 1; // hand 1 is better than hand 2
        if(hand1Quad < hand2Quad)
            return 2; // hand 2 is better than hand 1

        return findHighestKickerIgnoreMult(hand1, hand2);
    }



}
