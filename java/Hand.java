import java.util.Arrays;

public class Hand {

    private String input;
    private long value;
    private Card cards[];

    private final int HIGHCARD = 1;
    private final int ONEPAIR = 2;
    private final int TWOPAIR = 3;
    private final int TRIPS = 4;
    private final int STRAIGHT = 5;
    private final int FLUSH = 6;
    private final int BOAT = 7;
    private final int QUADS = 8;
    private int numCards = 5;



    private boolean isHighCard, isOnePair, isTwoPair, isTrips, isStraight, isFlush, isBoat, isQuads;
    private long primes[];
    private String ranksAndSuits[];

    public Hand(String input) {
        this.input  = input;
        createHand();
        primes = new long[5];
        ranksAndSuits = new String[5];
        value = calcValue(this.input);
        setPrimes();
        sortCards();

    }

    private void createHand() {
        cards = new Card[5];
        int index = 0;
        for(int i = 0; i < input.length() - 1; i += 2) {
            char rank = input.charAt(i);
            char suit = input.charAt(i+1);
            cards[index] = new Card(rank, suit);
            ++index;
        }
    }

    public String getCards() {
        return input;
    }

    public void setCards(String input) {
        this.input = input;
    }

    public boolean isProperFormat(String cards) {

        return false;
    }

    private void sortCards() {
        setStringArray();
        Arrays.sort(primes);
        Arrays.sort(ranksAndSuits);

        for(int i = numCards -1; i > 0; --i) {
            String s = ranksAndSuits[i].substring(0,1);
            long value = getCardValue(s);

            for(int j = 0; j < numCards; ++j) {

            }

        }
    }

    private long getCardValue(String card) {
        String c = card.toLowerCase();

        switch (c) {
            case "2" :
                return 2;
            case "3" :
                return 3;
            case "4" :
                return 5;
            case "5" :
                return 7;
            case "6" :
                return 11;
            case "7" :
                return 13;
            case "8" :
                return 17;
            case "9" :
                return 19;
            case "t" :
                return 23;
            case "j" :
                return 29;
            case "q" :
                return 31;
            case "k" :
                return 37;
            case "a" :
                return 41;
            default:
                return -1; // something went wrong.
        }
    }

    private long calcValue(String cards) {
        long result = 1;
        for(int i =0; i < numCards; ++i) {
            result *= getCardValue(cards.substring(i * 2, (i*2)+1));
        }

        return result;
    }

    private void setStringArray() {
        int index = 0;
        for(int i = 0; i < input.length() - 1; ++i) {
            ranksAndSuits[index] = input.substring(i, i+2);
            index++;
            ++i;
        }
    }

    private void setPrimes() {
        int index = 0;
        for(int i = 0; i < (numCards *2) - 1; ++i) {
            String s = input.substring(i, i + 1);
            primes[index] = getCardValue(s);
            ++index;
            ++i;
        }
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < input.length() - 1; ++i) {
            if(i % 2 == 1)
                result += " ";
            else
                result += input.substring(i, i+2);
        }
        result += " --- With a value of " + value;

        result += "\n";
        for(int i = 0; i < numCards; ++i) {
            result += ranksAndSuits[i] + " ";

        }
        result += "\n";
        for(int i = 0; i < numCards; ++i) {
            result += "" + primes[i] + " ";
        }
        return result;
    }



}
