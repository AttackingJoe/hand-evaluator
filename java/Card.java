
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

    public Card(char rank, char suit) {
        this.rank = rank;
        this.suit = suit;
        value = toValue(rank, suit);
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
            return determineCard(r,s);
        else return BAD_CARD;
    }

    private int determineCard(int rank, int suit) {
        return(suit*NUM_RANKS) + rank;
    }

    public int getValue() {
        return value;
    }

}
