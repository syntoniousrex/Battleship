import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The abstract class Board represents the game board.
 * It contains the layout of the board, the fleet of ships,
 * and methods to apply moves and check the game's status.
 */
public abstract class Board {
    /** The layout of the board represented as a 2D ArrayList. */
    private ArrayList<ArrayList<CellStatus>> layout;

    /** The fleet of ships on the board. */
    private Fleet fleet;

    /** The size of the board. */
    public static final int SIZE = 10;

    /** Conversion string for translating column indices. */
    private final String moveConversion = "ABCDEFGHIJ";

    /**
     * Constructs a Board object by reading ship placements from a file.
     *
     * @param fileName The name of the file containing ship placements.
     * @throws FileNotFoundException If the file is not found.
     */
    public Board(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        layout = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            layout.add(new ArrayList<>(SIZE));
            for (int j = 0; j < SIZE; j++) {
                layout.get(i).add(j, CellStatus.NOTHING);
            }
            System.out.println("Should be nothing" + layout.get(i));
        }

        fleet = new Fleet();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            char shipType = parts[0].charAt(0);
            int startRow = moveConversion.indexOf(parts[1].charAt(0));
            int startColumn = Integer.parseInt(parts[1].substring(1)) - 1;
            int endRow = moveConversion.indexOf(parts[2].charAt(0));
            int endColumn = Integer.parseInt(parts[2].substring(1)) - 1;
            CellStatus cellStatus;

            switch (shipType) {
                case 'C':
                    fleet.updateFleet(ShipType.ST_CRUISER);
                    cellStatus = CellStatus.CRUISER;
                    break;
                case 'A':
                    fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
                    cellStatus = CellStatus.AIRCRAFT_CARRIER;
                    break;
                case 'B':
                    fleet.updateFleet(ShipType.ST_BATTLESHIP);
                    cellStatus = CellStatus.BATTLESHIP;
                    break;
                case 'S':
                    fleet.updateFleet(ShipType.ST_SUB);
                    cellStatus = CellStatus.SUB;
                    break;
                case 'D':
                    fleet.updateFleet(ShipType.ST_DESTROYER);
                    cellStatus = CellStatus.DESTROYER;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid ship type: " + shipType);
            }
            if (startColumn == endColumn)
            {
                for (int i = startRow; i < endRow; i++)
                {
                    layout.get(i).set(startColumn, cellStatus);
                    System.out.println("Should contain a ship" + layout.get(i));
                }
            }
            else
            {
                for (int i = startColumn; i < endColumn; i++)
                {
                    layout.get(startRow).set(i, cellStatus);
                    System.out.println("Should contain a ship" + layout.get(startRow));
                }
            }
        }
        System.out.println("Should print 10 lines");
        for (int i = 0; i < layout.size(); i++) {
            System.out.println(Integer.toString(i) + layout.get(i) + "\n");
        }
        scanner.close();
    }

    /**
     * Applies a move to the board layout and returns the status of the cell.
     *
     * @param move The move to be applied.
     * @return The status of the cell after the move.
     */
    public CellStatus applyMoveToLayout(Move move) {
        int row = move.row();
        int column = move.col();
        CellStatus status = layout.get(row).get(column);

        if (status == CellStatus.NOTHING && moveAvailable(move) || status == CellStatus.NOTHING_HIT) {
            layout.get(row).set(column, CellStatus.NOTHING_HIT);
            System.out.println(status.name());
            return CellStatus.NOTHING_HIT;
        } else if (status.name().contains("_SUNK")) {
            return status;
        } else {
            if (fleet.updateFleet(ShipType.valueOf("ST_" + status.name()))) {
                layout.get(row).set(column, CellStatus.valueOf(status.name().replace("_HIT", "_SUNK")));
                return status;
            }
            else
            {
                layout.get(row).set(column, CellStatus.valueOf(status.name().replace("_HIT", "_HIT")));
            }
        }
        return status;
    }

    /**
     * Checks if a move is available at a given position.
     *
     * @param move The move to be checked.
     * @return True if the move is available, false otherwise.
     */
    public boolean moveAvailable(Move move) {
        int row = move.row();
        int column = move.col();
        CellStatus status = layout.get(row).get(column);

        if (status.toString().contains("HIT") || status.toString().contains("SUNK"))
        {
            return false;
        }
        return true;
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean gameOver() {
        return fleet.gameOver();
    }

    /**
     * Retrieves the layout of the board.
     *
     * @return The layout of the board.
     */
    public ArrayList<ArrayList<CellStatus>> getLayout() {
        return layout;
    }

    /**
     * Retrieves the fleet of ships on the board.
     *
     * @return The fleet of ships.
     */
    public Fleet getFleet() {
        return fleet;
    }
}