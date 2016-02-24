import java.util.Arrays;

public class Hand {

    private String input;
    private Card cards[];
    private final int NUM_RANKS = 13;
    private int freq[] = new int[NUM_RANKS]; // frequency of the ranks of a Hand that will be sorted
    private int freqNoSort[] = new int[NUM_RANKS]; // frequency of the ranks of a Hand that will NOT be sorted
    private int numCards = 5;
    private HandRank rank;
    private boolean isWheel;


    /**
     * Constructor for the Hand object, contains an input String of the Cards, and an array of Cards
     * @param input is a String that represent different playing cards. ex) "ThJhQhKhAh"
     */
    public Hand(String input) {
        this.input = input;
        createHand();
        sortCards();
        isWheel = false;
        rank = calculateRank();
    }

    /**
     * Records the frequency of each rank of Card in a given Hand
     * @return a sorted integer array containing the frequency of the given ranks in the Hand
     */
    public int[] findUnique() {
        int[] unique = new int[numCards];
        for(int i = 0; i < numCards; ++i) {
            unique[i] = -1;
        }

        int index = 0;
        for(int i = NUM_RANKS - 1; i> -1; --i ) {
            if(freqNoSort[i] == 1) {
                unique[index] = i;
                ++index;
            }
        }
        // We sort unique for easier look up when we want to use it
        Arrays.sort(unique);
        return unique;
    }

    /**
     * Gives the Hand a certain ranking of how good it is
     * @return an enum of how good the Hand is
     */
    private HandRank calculateRank() {
        if(isStraightFlush())
            return HandRank.STRFLUSH;
        if(isQuads())
            return HandRank.QUADS;
        if(isBoat())
            return HandRank.BOAT;
        if(isFlush())
            return HandRank.FLUSH;
        if(isStraight())
            return HandRank.STRAIGHT;
        if(isTrips())
            return HandRank.TRIPS;
        if(isTwoPair())
            return HandRank.TWOPAIR;
        if(isOnePair())
            return HandRank.ONEPAIR;
        return HandRank.HIGHCARD;
    }

    /**
     * @return an enum of the Hand's ranking
     */
    public HandRank getRank() {
        return rank;
    }

    /**
     * Sets up the Card array for the Hand by reading the input String
     */
    private void createHand() {
        cards = new Card[5];
        int index = 0;
        for (int i = 0; i < input.length() - 1; i += 2) {
            char rank = input.charAt(i);
            char suit = input.charAt(i + 1);
            cards[index] = new Card(rank, suit);
            ++index;
        }
    }

    /**
     * Sorts the cards from low to high and creates the frequency arrays while it does that
     */
    private void sortCards() {
        for (int i = 0; i < numCards; ++i) {
            freq[cards[i].getIRank()]++;
            freqNoSort[cards[i].getIRank()]++;
        }
        Arrays.sort(cards, new Card.CardComparator());
        Arrays.sort(freq);
    }

    /**
     * Checks to see if the Hand is a flush - All five Cards have the same ISuit value
     * @return a boolean whether the Hand is a flush
     */
    public boolean isFlush() {
        for (int i = 0; i < numCards - 1; ++i) {
            if (cards[i].getISuit() != cards[i + 1].getISuit()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if the Hand is a straight - All five cards are in sequential order.
     * Unique case of the Wheel, which is A2345, but will be represented as 2345A due to sorting, so it will mark the Hand's isWheel to be true if this is the case
     * @return a boolean whether the Hand is a straight
     */
    public boolean isStraight() {
        boolean isFirst = true;
        for (int i = numCards - 1; i > 0; --i) {
            // have one special case of 2345A
            int difference;
            int rankOne = cards[i].getIRank();
            int rankTwo = cards[i - 1].getIRank();
            difference = rankOne - rankTwo;
            if (isFirst) {
                if (difference != 9 && difference != 1)
                    return false;
                if (difference == 9) {
                    isWheel = true;
                }
                isFirst = false;
            } else {
                if (difference != 1) {
                    return false;
                }
            }


        }
        return true;
    }

    public boolean isWheel() {
        return isWheel;
    }

    public boolean isQuads() {
        return freq[12] == 4 && freq[11] == 1;
    }

    public boolean isTrips() {
        return freq[12] == 3 && freq[11] == 1 && freq[10] == 1;
    }

    public boolean isOnePair() {
        return freq[12] == 2 && freq[11] == 1 && freq[10] == 1 && freq[9] == 1;
    }

    public boolean isTwoPair() {
        return freq[12] == 2 && freq[11] == 2 && freq[10] == 1;
    }

    public boolean isBoat() {
        return freq[12] == 3 && freq[11] == 2;
    }

    public boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    public Card getCardAt(int index) {
        if (index >= 0 && index < numCards) {
            return cards[index];
        }
        return null;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < numCards; ++i) {
            result += cards[i].toString();
        }
        return result;
    }


}
