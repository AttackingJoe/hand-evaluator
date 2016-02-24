import java.util.Comparator;

public class Card {

    private char rank;
    private char suit;
    private int value; // overall integer representation of a Card, a higher value doesn't always mean better Card

    private final int NUM_RANKS = 13;
    private CardRank ranks[] = CardRank.values();
    private CardSuit suits[] = CardSuit.values();


    private int iRank; // integer representation of the Card's rank
    private int iSuit; // integer representation of the Card's suit

    /**
     * Constructor for the Card object that takes two char parameters for its rank and suit
     * @param rank is the char that will be the rank of the card [AaKkQqJjTt2-9]
     * @param suit is the char that will be the suit of the card [CcDdHhSs]
     */
    public Card(char rank, char suit) {
        this.rank = rank;
        this.suit = suit;
        value = toValue(rank, suit);
        iRank = calcRank(value);
        iSuit = calcSuit(value);
    }

    /**
     * Constructor for the Card object that takes an integer value and derives its rank and suit
     * @param value is the integer value of the card to derive [0, 51]
     */
    public Card(int value) {
        this.value = value;
        iRank = calcRank(this.value);
        iSuit = calcSuit(this.value);
        rank = getRankFromValue(this.value);
        suit = getSuitFromValue(this.value);
    }

    /**
     * Calculates the integer value of the card in its rank, suit, and overall value.
     *
     * Rank - integer rankings start at 0 going from deuce to ace
     * Suit - integer values for suit start at 0 going from Clubs, Diamonds, Hearts, Spades
     *
     * Note - a higher value doesn't necessarily mean a better card.
     * @param rank the Card's rank as a char to convert into its given integer value
     * @param suit the Card's suit as a char to convert into its given integer value
     * @return the integer representation of that Card
     */
    private int toValue(char rank, char suit) {
        CardRank r = CardRank.BAD_CARD;
        switch (rank) {
            case '2': r = CardRank.TWO; break;
            case '3': r = CardRank.THREE; break;
            case '4': r = CardRank.FOUR; break;
            case '5': r = CardRank.FIVE; break;
            case '6': r = CardRank.SIX; break;
            case '7': r = CardRank.SEVEN; break;
            case '8': r = CardRank.EIGHT; break;
            case '9': r = CardRank.NINE; break;
            case 'T': r = CardRank.TEN; break;
            case 'J': r = CardRank.JACK; break;
            case 'Q': r = CardRank.QUEEN; break;
            case 'K': r = CardRank.KING; break;
            case 'A': r = CardRank.ACE; break;
            case 't': r = CardRank.TEN; break;
            case 'j': r = CardRank.JACK; break;
            case 'q': r = CardRank.QUEEN; break;
            case 'k': r = CardRank.KING; break;
            case 'a': r = CardRank.ACE; break;
        }
        CardSuit s = CardSuit.BAD_SUIT;
        switch (suit) {
            case 'h': s = CardSuit.HEARTS; break;
            case 'd': s = CardSuit.DIAMONDS; break;
            case 's': s = CardSuit.SPADES; break;
            case 'c': s = CardSuit.CLUBS; break;
            case 'H': s = CardSuit.HEARTS; break;
            case 'D': s = CardSuit.DIAMONDS; break;
            case 'S': s = CardSuit.SPADES; break;
            case 'C': s = CardSuit.CLUBS; break;
        }
        if (s.getValue() != -1 && r.getValue() != -1)
            return calcValue(r.getValue(),s.getValue());
        else return CardRank.BAD_CARD.getValue();
    }

    /**
     * Takes a given integer [0, 51] and determines what its playing card rank value is in char form.
     * @param value is the integer representation that that Card's overall value
     * @return a char representing the Card's rank
     */
    private char getRankFromValue(int value) {
        int integerRank = calcRank(value);

        CardRank r = ranks[integerRank];
        switch (r) {
            case TWO:
                return '2';
            case THREE:
                return '3';
            case FOUR:
                return '4';
            case FIVE:
                return '5';
            case SIX:
                return '6';
            case SEVEN:
                return '7';
            case EIGHT:
                return '8';
            case NINE:
                return '9';
            case TEN:
                return 'T';
            case JACK:
                return 'J';
            case QUEEN:
                return 'Q';
            case KING:
                return 'K';
            case ACE:
                return 'A';
            default:
                return 'b';
        }
    }

    /**
     * Takes a given integer value [0, 51] and determines what its playing card suit value is in char form.
     * @param value is the integer representation that that Card's overall value
     * @return a char representing the Card's suit
     */
    private char getSuitFromValue(int value) {
        int integerSuit = calcSuit(value);

        CardSuit s = suits[integerSuit];
        switch (s) {
            case CLUBS:
                return 'c';
            case DIAMONDS:
                return 'd';
            case HEARTS:
                return 'h';
            case SPADES:
                return 's';
            default:
                return 'x';
        }
    }

    /**
     * Calculates the Card's overall integer value
     * @param rank is the Card's integer rank value
     * @param suit is the Card's integer suit value
     * @return an integer that represents the overall value of the Card
     */
    private int calcValue(int rank, int suit) {
        return(suit*NUM_RANKS) + rank;
    }

    /**
     * Calculates the Card's integer rank from its overall value
     * @param value is the integer representation of the Card's overall value
     * @return an integer that represents the Card's rank value
     */
    private int calcRank(int value) {
        return value % NUM_RANKS;
    }

    /**
     * Calculates the Card's integer suit from its overall value
     * @param value is the integer representation of the Card's overall value
     * @return an integer that represents the Card's suit value
     */
    private int calcSuit(int value) {
        return value / NUM_RANKS;
    }

    /**
     * Gets the integer representation of the Card's rank value
     * @return an integer that represents the Card's rank
     */
    public int getIRank() {
        return iRank;
    }

    /**
     * Gets the integer representation of the Card's suit value
     * @return an integer that represents the Card's suit
     */
    public int getISuit() {
        return iSuit;
    }

    public String toString() {
        return "" + rank + suit + " ";
    }

    static class CardComparator implements Comparator<Card> {
        /**
         * Compares two Cards and determines which is the higher ranked Card
         * @param c1 is the first Card to compare
         * @param c2 is the second Card to compare
         * @return an integer that represents whether the first Card is greater than, less than, or equal to the second
         */
        @Override
        public int compare(Card c1, Card c2) {
            return Integer.compare(c1.getIRank(), c2.getIRank());
        }
    }

}
