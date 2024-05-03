/**
 * The Move class represents a move in the Battleship game.
 * It stores the row and column of the move.
 */
public class Move {

    /** The alphabet used for column indexing. */
    private final String alphabet = "ABCDEFGHIJ";

    /** The row of the move. */
    private int row;

    /** The column of the move. */
    private int col;

    /**
     * Constructs a Move object with the given row and column.
     *
     * @param row The row of the move.
     * @param col The column of the move.
     */
    public Move(int row, int col) {
        this.row = row - 1;
        this.col = col - 1;
    }

    /**
     * Constructs a Move object with the given move string.
     *
     * @param move The move string (e.g., "A1").
     */
    public Move(String move) {
        row = alphabet.indexOf(move.toUpperCase().charAt(0));
        col = Integer.parseInt(move.substring(1)) - 1;
    }

    /**
     * Returns the row of the move.
     *
     * @return The row of the move.
     */
    public int row() {
        return row;
    }

    /**
     * Returns the column of the move.
     *
     * @return The column of the move.
     */
    public int col() {
        return col;
    }

    /**
     * Generates a string representation of the move.
     *
     * @return The string representation of the move.
     */
    @Override
    public String toString() {
        return String.format("%c%d", alphabet.charAt(row), col);
    }
}