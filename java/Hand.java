import java.util.Arrays;

public class Hand {

    private String input;
    private long value;
    private Card cards[];
    private final int NUM_RANKS = 13;
    private int freq[] = new int[NUM_RANKS];
    private int freqNoSort[] = new int[NUM_RANKS];
    private int numCards = 5;
    private HandRank rank;
    private boolean isWheel;


//    private boolean isHighCard, isOnePair, isTwoPair, isTrips, isStraight, isFlush, isBoat, isQuads, isStraightFlush;
    private long primes[];
    private String ranksAndSuits[];

    public Hand(String input) {
        this.input = input;
        createHand();
        primes = new long[5];
        ranksAndSuits = new String[5];
        value = calcValue(this.input);
        setPrimes();
        sortCards();
        isWheel = false;
        rank = calculateRank();
    }

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

        Arrays.sort(unique);
        return unique;
    }

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

    public HandRank getRank() {
        return rank;
    }

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

    private void sortCards() {
        setStringArray();
        Arrays.sort(primes);
        Arrays.sort(ranksAndSuits);

        for (int i = 0; i < numCards; ++i) {
            freq[cards[i].getIRank()]++;
            freqNoSort[cards[i].getIRank()]++;
        }

        Arrays.sort(cards, new Card.CardComparator());

        Arrays.sort(freq);


    }

    private long getCardValue(String card) {
        String c = card.toLowerCase();

        switch (c) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 5;
            case "5":
                return 7;
            case "6":
                return 11;
            case "7":
                return 13;
            case "8":
                return 17;
            case "9":
                return 19;
            case "t":
                return 23;
            case "j":
                return 29;
            case "q":
                return 31;
            case "k":
                return 37;
            case "a":
                return 41;
            default:
                return -1; // something went wrong.
        }
    }

    private long calcValue(String cards) {
        long result = 1;
        for (int i = 0; i < numCards; ++i) {
            result *= getCardValue(cards.substring(i * 2, (i * 2) + 1));
        }

        return result;
    }

    private void setStringArray() {
        int index = 0;
        for (int i = 0; i < input.length() - 1; ++i) {
            ranksAndSuits[index] = input.substring(i, i + 2);
            index++;
            ++i;
        }
    }

    private void setPrimes() {
        int index = 0;
        for (int i = 0; i < (numCards * 2) - 1; ++i) {
            String s = input.substring(i, i + 1);
            primes[index] = getCardValue(s);
            ++index;
            ++i;
        }
    }

    public boolean isFlush() {
        for (int i = 0; i < numCards - 1; ++i) {
            if (cards[i].getISuit() != cards[i + 1].getISuit()) {
                return false;
            }
        }
        return true;
    }

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
        for (int i = 0; i < input.length() - 1; ++i) {
            if (i % 2 == 1)
                result += " ";
            else
                result += input.substring(i, i + 2);
        }
        result += " --- With a value of " + value;

        result += "\n";
        for (int i = 0; i < numCards; ++i) {
            result += ranksAndSuits[i] + " ";

        }
        result += "\n";
        for (int i = 0; i < numCards; ++i) {
            result += "" + primes[i] + " ";
        }
        return result;
    }


}
