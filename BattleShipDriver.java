import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The BattleShipDriver class is responsible for running the Battleship game.
 */
public class BattleShipDriver {

    /** The Game instance. */
    private static Game game;

    /** Random number generator for determining who goes first. */
    private static Random random = new Random();

    /** Indicates whether it's the player's turn. */
    private static Boolean playerTurn;

    /** Scanner object for taking user input. */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The main method, responsible for running the Battleship game.
     *
     * @param args Command line arguments (not used).
     * @throws FileNotFoundException If the file containing ship placements is not
     *                               found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        game = new Game();

        // Determine who goes first
        if (random.nextInt(2) == 1) {
            playerTurn = true;
            System.out.println("Player goes first\n");
        } else {
            playerTurn = false;
            System.out.println("Computer goes first\n");
        }

        // Main game loop
        while (!game.computerDefeated() && !game.userDefeated()) {
            if (playerTurn) {
                System.out.println("Enter player move (A1: rows, columns): ");
                try {
                    System.out.println(game.makePlayerMove(scanner.nextLine()));
                } catch (Exception e) {
                    System.out.println("Invalid move entry, forfeiting turn");
                    throw e;
                } finally {
                    playerTurn = !playerTurn;
                }
            } else {
                System.out.println("Computer's move: " + Arrays.toString(game.makeComputerMove()));
                playerTurn = !playerTurn;
            }
            System.out.println(game.toString());
        }

        // Display the game result
        if (game.computerDefeated()) {
            System.out.println(game.computerDefeated());
        } else {
            System.out.println(game.userDefeated());
        }
    }
}