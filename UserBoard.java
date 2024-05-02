import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The UserBoard class represents the user's game board.
 * It extends the Board class and implements methods specific to the user's moves.
 */
public class UserBoard extends Board {

    /** The list of available moves. */
    private ArrayList<Move> moves;

    /** Random number generator for selecting moves. */
    private Random random;

    /**
     * Constructs a UserBoard object by reading ship placements from a file.
     *
     * @param file The name of the file containing ship placements.
     * @throws FileNotFoundException If the file is not found.
     */
    public UserBoard(String file) throws FileNotFoundException {
        super(file);
        moves = new ArrayList<>();
        random = new Random();

        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                moves.add(new Move(i, j));
            }
        }
    }

    /**
     * Generates a computer move and returns the result.
     *
     * @return The result of the computer move.
     */
    public String[] makeComputerMove() {
        Move move = moves.remove(pickRandomMove());
        CellStatus status = applyMoveToLayout(move);
        String[] result = new String[2];
        result[0] = move.toString();
        if (status != CellStatus.NOTHING && status != CellStatus.NOTHING_HIT) {
            if (status.toString().contains("SUNK")) {
                result[1] = String.format("You sank my %s!", status.toString().substring(0, status.toString().indexOf("_")));
            }
        }
        return result;
    }

    /**
     * Picks a random move from the available moves.
     *
     * @return The index of the selected move.
     */
    public int pickRandomMove() {
        return random.nextInt(moves.size());
    }

    /**
     * Generates a string representation of the user's board.
     *
     * @return The string representation of the user's board.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<CellStatus> row : getLayout()) {
            for (CellStatus status : row) {
                sb.append(status.toString().charAt(1)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}