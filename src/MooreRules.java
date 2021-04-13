/**
 * MooreRules is a subclass of Rules that adds more rules to GameOfLife. This class adds guidelines for when a cell should
 * survive, when a cell should be born, and when a cell should die.
 */

public class MooreRules extends Rules {
    private boolean[] birthRules;
    private boolean[] survivalRules;
    public static final int NUM_NEIGHBORS = 9;

    /**
     * MooreRules determines what should happen to a cell based on the number of live neighbors it has.
     * @param birthNeighbors the number of live neighbors needed to be born
     * @param survivalNeighbors the number of live neighbors needed to survive
     * @param rule the index of each set of birth and survival rules
     */

    public MooreRules(int[] birthNeighbors, int[] survivalNeighbors, int rule) {
        super();
        birthRules = new boolean[NUM_NEIGHBORS]; // false, false, false, false, false, false, false, false, false
        survivalRules = new boolean[NUM_NEIGHBORS]; // false, false, false, false, false, false, false, false, false

        for (int neighbors: birthNeighbors) {
            birthRules[neighbors] = true; // false, false, false, true, false, false, false, false, false
        }
        for (int neighbors: survivalNeighbors) {
            survivalRules[neighbors] = true; // false, false, true, true, false, false, false, false, false
        }
    }

    /**
     * shouldBeBorn determines whether or not a dead cell should be born in the next generation.
     * @param liveNeighbors the number of live neighbors around a cell
     * @return true or false, whether or not a cell should be born
     */

    @Override
    public boolean shouldBeBorn(int liveNeighbors) {
        if (liveNeighbors == 2 || liveNeighbors == 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * shouldSurvive determine whether or not an alive cell should survive into the next generation.
     * @param liveNeighbors the number of live neighbors around a cell
     * @return true or false, whether or not a cell should survive
     */

    @Override
    public boolean shouldSurvive(int liveNeighbors) {
        if (liveNeighbors == 4) {
            return true;
        } else {
            return false;
        }
    }
}