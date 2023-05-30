package UnoGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create players
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer("Player 1"));
        players.add(new AIPlayer("Player 2"));

        // Create game
        Game game = new Game(players);

        // Start the game
        game.startGame();

        // Play the game
        game.playGame();

        // Close the scanner
        scanner.close();
    }

}
