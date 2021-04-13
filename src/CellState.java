import java.util.Random;

/**
 * CellState is a list of all of the possible states the cell could have.
 */

public enum CellState {
    ALIVE,
    DEAD,
    WILL_DIE,
    WILL_REVIVE;

    private static final CellState[] STATES = {
            ALIVE,
            DEAD
    };
    private static final Random RANDOM = new Random();

    //randomState returns a random cellState
    private static CellState randomState() {
        return STATES[RANDOM.nextInt(2)];
    }
}