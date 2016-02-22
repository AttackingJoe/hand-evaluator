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
        this.input = input;
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
        for (int i = 0; i < input.length() - 1; i += 2) {
            char rank = input.charAt(i);
            char suit = input.charAt(i + 1);
            cards[index] = new Card(rank, suit);
            ++index;
        }
    }

    public Card[] getCards() {
        return cards;
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

        // terrible sorting method --- temporary

        int cardArray[] = new int[numCards];

        for (int i = 0; i < numCards; ++i) {
            cardArray[i] = cards[i].getValue();
        }

        Arrays.sort(cards, new Card.CardComparator());

//        Arrays.sort(cardArray);

//        for (int i = 0; i < numCards; ++i) {
//            cards[i] = new Card(cardArray[i]);
//        }
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
                isFirst = false;
            } else {
                if (difference != 1) {
                    return false;
                }
            }


        }
        return true;
    }

    public boolean isQuads() {
        int count = 0;
        int max = -1;
        for(int i = 0; i < numCards - 1; ++i) {
            if(cards[i].getIRank() == cards[i + 1].getIRank()) {
                ++count;
                if(count >= max)
                    max = count;
            }
            else
                count = 0;
        }
        return max == 3;
    }

    public boolean isTrips() {
        int count = 0;
        int max = -1;
        for(int i = 0; i < numCards - 1; ++i) {
            if(cards[i].getIRank() == cards[i + 1].getIRank()) {
                ++count;
                if(count > max)
                    max = count;
            }
            else
                count = 0;
        }
        return max == 2;
    }

    public boolean isOnePair() {
        int count = 0;
        int max = -1;
        int numPairs = 0;
        for(int i = 0; i < numCards - 1; ++i) {
            if(cards[i].getIRank() == cards[i + 1].getIRank()) {
                ++count;
                ++numPairs;
                if(count > max)
                    max = count;
            }
            else
                count = 0;
        }
        return (max == 1 && numPairs ==1);
    }

    public boolean isTwoPair() {
        int count = 0;
        int max = -1;
        int numPairs = 0;
        for(int i = 0; i < numCards - 1; ++i) {
            if(cards[i].getIRank() == cards[i + 1].getIRank()) {
                ++count;
                ++numPairs;
                if(count > max)
                    max = count;
            }
            else
                count = 0;
        }
        return (max == 1 && numPairs ==2);
    }

    public boolean isBoat() {
        int numPairs = 0;
        int numTrips = 0;
        int count = 0;
        int max = -1;
        // check for one pair only
        for(int i = 0; i < numCards - 1; ++i) {
            if(cards[i].getIRank() == cards[i + 1].getIRank()) {
                // need to check to make sure the next card is not the same rank
                if(i != numCards - 2 && cards[i+1].getIRank() != cards[i+2].getIRank()) {
                    ++count;
                    if(count == 1)
                        ++numPairs;
                }

            } else
                count = 0;
        }
        count = 0;
        for(int i = 0; i < numCards - 1; ++i) {
            if(cards[i].getIRank() == cards[i + 1].getIRank()) {
                ++count;
                if(count == 2)
                    ++numTrips;
            } else {
                count = 0;
            }
        }
        return (numPairs == 1 && numTrips == 1);
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

    public String printCards() {
        String result = "";
        for (int i = 0; i < numCards; ++i) {
            result += cards[i].toString() + " ";
        }
        return result;
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
