import java.io.FileNotFoundException;

/**
 * The Game class represents the Battleship game.
 * It manages the game boards for the player and computer, and handles moves and game status.
 */
public class Game {

    /** The computer's game board. */
    private ComputerBoard computer;
    
    /** The player's game board. */
    private UserBoard player;

    /**
     * Constructs a Game object by initializing the player and computer boards.
     *
     * @throws FileNotFoundException If the file containing ship placements is not found.
     */
    public Game() throws FileNotFoundException {
        computer = new ComputerBoard("compfleet.txt");
        player = new UserBoard("userFleet.txt");
    }

    /**
     * Generates a computer move and returns the result.
     *
     * @return The result of the computer move.
     */
    public String[] makeComputerMove() {
        return player.makeComputerMove();
    }

    /**
     * Makes a player move and returns the result.
     *
     * @param move The move made by the player.
     * @return The result of the player move.
     */
    public String makePlayerMove(String move) {
        return computer.makePlayerMove(new Move(move));
    }

    /**
     * Checks if the computer is defeated.
     *
     * @return True if the computer is defeated, false otherwise.
     */
    public boolean computerDefeated() {
        return computer.getFleet().gameOver();
    }

    /**
     * Checks if the player is defeated.
     *
     * @return True if the player is defeated, false otherwise.
     */
    public boolean userDefeated() {
        return player.getFleet().gameOver();
    }

    /**
     * Generates a string representation of the game boards.
     *
     * @return The string representation of the game boards.
     */
    @Override
    public String toString() {
        return "Player board\n" + player.toString() + "\nComputer board\n" + computer.toString();
    }
}