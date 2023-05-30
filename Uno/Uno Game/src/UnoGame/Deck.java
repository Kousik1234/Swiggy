package UnoGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        for (Color color : Color.values()) {
            for (Value value : Value.values()) {
                if (value == Value.ZERO || value.ordinal() <= Value.NINE.ordinal()) {
                    cards.add(new Card(color, value));
                    if (value != Value.ZERO) {
                        cards.add(new Card(color, value));
                    }
                } else if (value.isSpecialActionCard()) {
                    cards.add(new Card(color, value));
                    cards.add(new Card(color, value));
                } else if (value.isWildCard()) {
                    cards.add(new Card(null, value));
                    cards.add(new Card(null, value));
                    cards.add(new Card(null, value));
                    cards.add(new Card(null, value));
                }
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
           // replenishDeck();
        }
        return cards.remove(cards.size() - 1);
    }

    private void replenishDeck(Game game) {
        List<Card> discardedCards = game.getDiscardedCards();
        cards.addAll(discardedCards);
        discardedCards.clear();
        shuffle();
    }

}
