
public class Hand {

    private String cards;
    private long value;

    public Hand(String cards) {
        this.cards  = cards;
        value = calcValue(this.cards);
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public boolean isProperFormat(String cards) {

        return false;
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
                return -1; // something wen wrong.
        }
    }

    private long calcValue(String cards) {
        long result = 1;
        for(int i =0; i < 5; ++i) {
            result *= getCardValue(cards.substring(i * 2, (i*2)+1));
        }

        return result;
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < cards.length() - 1; ++i) {
            if(i % 2 == 1)
                result += " ";
            else
                result += cards.substring(i, i+2);
        }
        result += " --- With a value of " + value;
        return result;
    }

}
