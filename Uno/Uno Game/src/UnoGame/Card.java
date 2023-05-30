package UnoGame;

public class Card {

    private Color color;
    private Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public boolean isSpecialActionCard() {
        return value.isSpecialActionCard();
    }

    public boolean isWildCard() {
        return value.isWildCard();
    }

    public void performAction(Game game, Player player) {
        switch (value) {
            case SKIP:
                game.skipNextPlayer();
                break;
            case REVERSE:
                game.reverseDirection();
                break;
            case DRAW_TWO:
                game.drawCards(2);
                game.skipNextPlayer();
                break;
            case WILD_DRAW_FOUR:
                game.chooseNextColor();
                game.drawCards(4);
                game.skipNextPlayer();
                break;
        }
    }

    public boolean isValidPlay(Card topCard) {
        if (color == topCard.getColor()) {
            // Card colors match, so it's a valid play
            return true;
        }
        if (value == topCard.getValue()) {
            // Card values match, so it's a valid play
            return true;
        }
        if (color == Color.WILD) {
            // Wild card can be played on any card
            return true;
        }
        return false;
    }


}
