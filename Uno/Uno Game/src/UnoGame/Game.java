package UnoGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {
    private List<Player> players;
    private Deck deck;
    private List<Card> discardedCards;
    private int currentPlayerIndex;
    private boolean directionClockwise;
    private Color currentColor;

    public Game(List<Player> players) {
        this.players = players;
        deck = new Deck();
        discardedCards = new ArrayList<>();
        currentPlayerIndex = 0;
        directionClockwise = true;
        currentColor = null;
    }

    public void startGame() {
        // Distribute initial cards to players
        for (Player player : players) {
            List<Card> initialCards = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                initialCards.add(deck.drawCard());
            }
            player.receiveInitialCards(initialCards);
        }

        // Determine the first player
        currentPlayerIndex = new Random().nextInt(players.size());
    }

    public void playGame() {
        boolean gameEnded = false;
        while (!gameEnded) {
            Player currentPlayer = players.get(currentPlayerIndex);

            // Check if the current player can play a card
            if (currentPlayer.hasValidCards(this)) {
                Card topCard = getTopDiscardedCard();
                boolean cardPlayed = false;

                while (!cardPlayed) {
                    Card card = currentPlayer.chooseCardToPlay(topCard);

                    if (card.isWildCard()) {
                        currentColor = currentPlayer.chooseColor();
                    }

                    if (currentPlayer.isValidPlay(card, this)) {
                        currentPlayer.playCard(card, this);
                        cardPlayed = true;

                        if (currentPlayer.hasEmptyHand()) {
                            gameEnded = true;
                            declareWinner(currentPlayer);
                        }
                    } else {
                        currentPlayer.drawCard(deck);
                    }
                }
            } else {
                currentPlayer.drawCard(deck);
            }

            // Proceed to the next player
            currentPlayerIndex = getNextPlayerIndex();
        }
    }

    private int getNextPlayerIndex() {
        int direction = directionClockwise ? 1 : -1;
        int nextPlayerIndex = currentPlayerIndex + direction;

        if (nextPlayerIndex < 0) {
            nextPlayerIndex = players.size() - 1;
        } else if (nextPlayerIndex >= players.size()) {
            nextPlayerIndex = 0;
        }

        return nextPlayerIndex;
    }

    public void addDiscardedCard(Card card) {
        discardedCards.add(card);
    }

    public Card getTopDiscardedCard() {
        if (discardedCards.isEmpty()) {
            return null;
        }
        return discardedCards.get(discardedCards.size() - 1);
    }

    public List<Card> getDiscardedCards() {
        return discardedCards;
    }

    public void clearDiscardedCards() {
        discardedCards.clear();
    }

    public void skipNextPlayer() {
        currentPlayerIndex = getNextPlayerIndex();
    }

    public void reverseDirection() {
        directionClockwise = !directionClockwise;
    }

    public void drawCards(int numCards) {
        Player nextPlayer = players.get(getNextPlayerIndex());
        for (int i = 0; i < numCards; i++) {
            nextPlayer.drawCard(deck);
        }
    }

    public void chooseNextColor() {
        Card topCard = getTopDiscardedCard();
        if (topCard.isWildCard()) {
            if (players.get(currentPlayerIndex) instanceof HumanPlayer) {
                currentColor = ((HumanPlayer) players.get(currentPlayerIndex)).chooseColor();
            } else {
                // Logic to determine the next color (can be implemented based on game rules)
                // For now, let's randomly choose a color
                currentColor = Color.values()[new Random().nextInt(Color.values().length)];
            }
        } else {
            currentColor = topCard.getColor();
        }
    }

    public void declareWinner(Player player) {
        System.out.println("Player " + player.getPlayerName() + " wins!");
    }
}
