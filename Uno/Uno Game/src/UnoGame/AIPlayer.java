package UnoGame;

import java.util.Random;


import java.util.Random;

import java.util.Random;

public class AIPlayer extends Player {

    private Color chosenColor;

    public AIPlayer(String playerName) {
        super(playerName);
        chosenColor = null;
    }

    @Override
    public Card chooseCardToPlay(Card topCard) {
        for (Card card : getHand()) {
            if (card.getColor() == topCard.getColor() || card.getValue().equals(topCard.getValue())) {
                return card;
            }
        }
        return null; // No valid cards to play
    }

    @Override
    public void setColor(Color color) {
        chosenColor = color;
    }

    @Override
    public Color chooseColor() {
        Color[] colors = Color.values();
        Random random = new Random();
        int index = random.nextInt(colors.length);
        setColor(colors[index]);
        return chosenColor;
    }
}
