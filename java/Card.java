import java.util.Comparator;

public class Card {

    private char rank;
    private char suit;
    private int value;

    public final static int CLUBS = 0;
    public final static int DIAMONDS = 1;
    public final static int HEARTS = 2;
    public final static int SPADES = 3;

    public final static int BAD_CARD = -1;
    public final static int TWO = 0;
    public final static int THREE = 1;
    public final static int FOUR = 2;
    public final static int FIVE = 3;
    public final static int SIX = 4;
    public final static int SEVEN = 5;
    public final static int EIGHT = 6;
    public final static int NINE = 7;
    public final static int TEN = 8;
    public final static int JACK = 9;
    public final static int QUEEN = 10;
    public final static int KING = 11;
    public final static int ACE = 12;

    public final static int NUM_SUITS = 4;
    public final static int NUM_RANKS = 13;
    public final static int NUM_CARDS = 52;

    private int iRank;
    private int iSuit;

    public Card(char rank, char suit) {
        this.rank = rank;
        this.suit = suit;
        value = toValue(rank, suit);
        iRank = calcRank(value);
        iSuit = calcSuit(value);
    }

    public Card(int value) {
        this.value = value;
        iRank = calcRank(this.value);
        iSuit = calcSuit(this.value);
        rank = getRankFromValue(this.value);
        suit = getSuitFromValue(this.value);
    }

    private int toValue(char rank, char suit) {
        int r = -1;
        switch (rank) {
            case '2': r = TWO; break;
            case '3': r = THREE; break;
            case '4': r = FOUR; break;
            case '5': r = FIVE; break;
            case '6': r = SIX; break;
            case '7': r = SEVEN; break;
            case '8': r = EIGHT; break;
            case '9': r = NINE; break;
            case 'T': r = TEN; break;
            case 'J': r = JACK; break;
            case 'Q': r = QUEEN; break;
            case 'K': r = KING; break;
            case 'A': r = ACE; break;
            case 't': r = TEN; break;
            case 'j': r = JACK; break;
            case 'q': r = QUEEN; break;
            case 'k': r = KING; break;
            case 'a': r = ACE; break;
        }
        int s = -1;
        switch (suit) {
            case 'h': s = HEARTS; break;
            case 'd': s = DIAMONDS; break;
            case 's': s = SPADES; break;
            case 'c': s = CLUBS; break;
            case 'H': s = HEARTS; break;
            case 'D': s = DIAMONDS; break;
            case 'S': s = SPADES; break;
            case 'C': s = CLUBS; break;
        }
        if (s != -1 && r != -1)
            return calcIndex(r,s);
        else return BAD_CARD;
    }

    private char getRankFromValue(int value) {
        int r = calcRank(value);
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

    private char getSuitFromValue(int value) {
        int s = calcSuit(value);
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

    private int calcIndex(int rank, int suit) {
        return(suit*NUM_RANKS) + rank;
    }

    public int getValue() {
        return value;
    }

    public char getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }

    public int calcRank(int value) {
        return value % NUM_RANKS;
    }

    public int calcSuit(int value) {
        return value / NUM_RANKS;
    }

    public int getIRank() {
        return iRank;
    }

    public int getISuit() {
        return iSuit;
    }

    public String toString() {
        return "" + rank + suit + " ";
    }

    static class CardComparator implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            return Integer.compare(c1.getIRank(), c2.getIRank());
        }
    }

}
