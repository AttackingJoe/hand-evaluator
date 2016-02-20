
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

    private long calcValue(String cards) {


        return 0;
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < cards.length() - 1; ++i) {
            if(i % 2 == 1)
                result += " ";
            else
                result += cards.substring(i, i+2);

        }
        return result;
    }

}
