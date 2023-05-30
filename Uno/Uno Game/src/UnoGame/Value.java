package UnoGame;

public enum Value {

    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR;

    public boolean isSpecialActionCard() {
        return this == SKIP || this == REVERSE || this == DRAW_TWO;
    }

    public boolean isWildCard() {
        return this == WILD || this == WILD_DRAW_FOUR;
    }

}
