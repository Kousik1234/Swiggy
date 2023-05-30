package UnoGame;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner scanner;

    public HumanPlayer(String playerName) {
        super(playerName);
        scanner = new Scanner(System.in);
    }

    @Override
    public Card chooseCardToPlay(Card topCard) {
        List<Card> validCards = getValidCards(topCard);
        System.out.println("Player " + getPlayerName() + ", it's your turn.");
        System.out.println("Your hand: " + getHand());

        if (validCards.isEmpty()) {
            System.out.println("You don't have any valid cards to play. Drawing a card...");
            return null;
        }

        System.out.println("Top card: " + topCard);
        System.out.println("Valid cards: " + validCards);

        while (true) {
            System.out.print("Choose a card to play (enter the index): ");
            int index = scanner.nextInt();

            if (index >= 0 && index < validCards.size()) {
                return validCards.get(index);
            } else {
                System.out.println("Invalid index. Try again.");
            }
        }
    }

    @Override
    public void setColor(Color color) {
        return;
    }

    @Override
    public Color chooseColor() {
        System.out.println("Choose the next color:");
        System.out.println("1. Red");
        System.out.println("2. Blue");
        System.out.println("3. Green");
        System.out.println("4. Yellow");

        while (true) {
            System.out.print("Enter the corresponding number: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return Color.RED;
                case 2:
                    return Color.BLUE;
                case 3:
                    return Color.GREEN;
                case 4:
                    return Color.YELLOW;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

