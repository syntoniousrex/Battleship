import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The ComputerBoard class represents the computer's game board.
 * It extends the Board class and implements methods specific to the computer's moves.
 */
public class ComputerBoard extends Board {

    /**
     * Constructs a ComputerBoard object by reading ship placements from a file.
     *
     * @param file The name of the file containing ship placements.
     * @throws FileNotFoundException If the file is not found.
     */
    public ComputerBoard(String file) throws FileNotFoundException {
        super(file);
    }

    /**
     * Applies a move made by the player to the computer's board layout.
     *
     * @param move The move made by the player.
     * @return A string indicating the result of the move.
     */
    public String makePlayerMove(Move move) {
        CellStatus status = applyMoveToLayout(move);
        System.out.println(status.name());

        if (status.name().contains("SUNK")) {
            return String.format("You sank my %s!", status.toString().substring(0, status.toString().indexOf("_")));
        }
        return null;
    }

    /**
     * Generates a string representation of the computer's board.
     *
     * @return The string representation of the computer's board.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<CellStatus> row : getLayout()) {
            for (CellStatus status : row) {
                sb.append(status.toString().charAt(0)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}