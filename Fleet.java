/**
 * The Fleet class represents the fleet of ships in the game.
 * It manages the status of each ship and provides methods to update the fleet and check if the game is over.
 */
public class Fleet {
    
    /** The battleship in the fleet. */
    private Ship battleShip;
    
    /** The aircraft carrier in the fleet. */
    private Ship aircraftCarrier;
    
    /** The cruiser in the fleet. */
    private Ship cruiser;
    
    /** The submarine in the fleet. */
    private Ship sub;
    
    /** The destroyer in the fleet. */
    private Ship destroyer;

    /**
     * Constructs a Fleet object with all the ships initialized.
     */
    public Fleet() {
        this.battleShip = new Battleship();
        this.aircraftCarrier = new AircraftCarrier();
        this.cruiser = new Cruiser();
        this.sub = new Sub();
        this.destroyer = new Destroyer();
    }

    /**
     * Updates the status of a ship in the fleet based on the given ship type.
     *
     * @param shipType The type of ship to be updated.
     * @return True if the ship was hit, false otherwise.
     */
    public boolean updateFleet(ShipType shipType) {
        switch (shipType) {
            case ST_BATTLESHIP:
                return battleShip.hit();
            case ST_AIRCRAFT_CARRIER:
                return aircraftCarrier.hit();
            case ST_CRUISER:
                return cruiser.hit();
            case ST_SUB:
                return sub.hit();
            case ST_DESTROYER:
                return destroyer.hit();
            default:
                return false;
        }
    }

    /**
     * Checks if all ships in the fleet are sunk, indicating the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean gameOver() {
        return battleShip.getSunk() && aircraftCarrier.getSunk() && cruiser.getSunk() &&
                sub.getSunk() && destroyer.getSunk();
    }
}