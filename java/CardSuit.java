
public enum CardSuit {
    CLUBS(0), DIAMONDS(1), HEARTS(2), SPADES(3), BAD_SUIT(-1);

    private int value;

    CardSuit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
