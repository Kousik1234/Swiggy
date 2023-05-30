package UnoGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private String playerName;
    private List<Card> hand;

    public Player(String playerName) {
        this.playerName = playerName;
        hand = new ArrayList<>();
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void receiveInitialCards(List<Card> initialCards) {
        hand.addAll(initialCards);
    }

    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        hand.add(card);
        System.out.println("Player " + playerName + " drew a card: " + card);
    }

    public abstract Card chooseCardToPlay(Card topCard);

    public abstract void setColor(Color color);

    public abstract Color chooseColor();

    public List<Card> getValidCards(Card topCard) {
        List<Card> validCards = new ArrayList<>();
        for (Card card : hand) {
            if (card.isValidPlay(topCard)) {
                validCards.add(card);
            }
        }
        return validCards;
    }

    public boolean hasValidCards(Game game) {
        Card topCard = game.getTopDiscardedCard();
        for (Card card : hand) {
            if (card.isValidPlay(topCard)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidPlay(Card card, Game game) {
        Card topCard = game.getTopDiscardedCard();
        return card.isValidPlay(topCard);
    }

    public void playCard(Card card, Game game) {
        hand.remove(card);
        game.addDiscardedCard(card);
        System.out.println("Player " + playerName + " played a card: " + card);
        card.performAction(game, this);
    }

    public boolean hasEmptyHand() {
        return hand.isEmpty();
    }
}
