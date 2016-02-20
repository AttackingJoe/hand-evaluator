
public class Hand {

    private String cards;

    public Hand(String cards) {
        this.cards  = cards;
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

    public String toString() {
        String result = "";
        for(int i = 0; i < cards.length() - 1; ++i) {
            if(i % 2 == 1)
                result += " ";
            result += cards.substring(i, i+2);
        }
        return result;
    }

}
