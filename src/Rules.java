/**
 * Rules is the superclass for the GameOfLife rules. They are the rules that apply to every cell regardless of the born
 * and survival rules that are being implemented.
 */

public abstract class Rules {
    public abstract boolean shouldBeBorn(int liveNeighbors);
    public abstract boolean shouldSurvive(int liveNeighbors);

    /**
     * applyRules applies the GameOfLife rules to each cell by looking at the cell's cellState.
     * @param cellState the state of the cell
     * @param liveNeighbors the number of live neighbors around a cell
     * @return the cellstate that the cell gets right before evolution
     */

    public CellState applyRules(CellState cellState, int liveNeighbors) {
        if (cellState == CellState.DEAD && shouldBeBorn(liveNeighbors) == true) {
            return CellState.WILL_REVIVE;
        } else if (cellState == CellState.ALIVE && shouldSurvive(liveNeighbors) == false) {
            return CellState.WILL_DIE;
        } else {
            return cellState;
        }
    }
}