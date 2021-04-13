/**
 * Cell is a class that creates each individual cell. Rule applying and evolving all happen within the cell class.
 */

public class Cell {
    private final int x;
    private final int y;
    private final int size;
    private final int row;
    private final int column;
    private Rules rules;
    private CellState cellState;
    private int numLives;
    private int r;
    private int b;
    private int g;

    /**
     * Cell is the Cell constructor. This is the "blueprint" that creates all of the cells.
     *
     * @param x         the x-coordinate of the top left corner of a cell
     * @param y         the y-coordinate of the top left corner of a cell
     * @param size      the size of a cell
     * @param row       the row the cell is in
     * @param column    the column the cell is in
     * @param cellState the state of the cell (ie. alive, dead)
     * @param rules     the rules being applied to the cell during evolution
     */

    public Cell(int x, int y, int size, int row, int column, CellState cellState, Rules rules, int numLives) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
        this.numLives = numLives;
    }

    /**
     * display is called by GameOfLife to draw each cell on the canvas. Alive cells are black with a white border and
     * dead cells are white with a black border.
     */

    public void display() {
        GameOfLife app = GameOfLife.getApp();
        if (cellState == CellState.ALIVE) {
            app.fill(r, g, b);
            app.stroke(255);
        } else if (cellState == CellState.DEAD) {
            app.fill(255);
            app.stroke(0);
        }
        app.rect(x, y, size, size);
    }

    /**
     * handleClick toggles the cellState of a cell. If a cell is dead, then it becomes alive and if a cell is alive then
     * then it becomes dead.
     */

    public void handleClick() {
        if (cellState == CellState.ALIVE) {
            cellState = CellState.DEAD;
        } else {
            cellState = CellState.ALIVE;
        }
    }

    /**
     * applyRules applies the rules to the cell.
     *
     * @param cells the 2D array of cells from GameOfLife
     */

    public void applyRules(Cell[][] cells) {
        cellState = rules.applyRules(cellState, countLiveNeighbors(cells));
    }

    /**
     * evolve makes the cells evolve to the next generation.
     */

    public void evolve() {
        if (cellState == CellState.WILL_REVIVE) {
            cellState = CellState.ALIVE;
            numLives++;
        } else if (cellState == CellState.WILL_DIE) {
            cellState = CellState.DEAD;
        } else {
            cellState = cellState;
            if (cellState == CellState.ALIVE) {
                numLives++;
            }
        }
    }

    /**
     * getSize returns the size of the cell
     *
     * @return size
     */

    public int getSize() {
        return size;
    }


    /**
     * getCellState returns the cellState of a cell.
     *
     * @return cellState
     */

    public CellState getCellState() {
        return cellState;
    }

    /**
     * history changes the RGB fill values of each cell object based on the number of times the cell has been alive.
     * Every time a cell comes alive, the R, G, and B fill values are decreased by 10, making the cell darker. Cells
     * that have been alive the longest have the darkest fill.
     */

    public void history() {
        if (numLives > 0) {
            r = 230 - (numLives * 20);
            g = 205 - (numLives * 20);
            b = 215 - (numLives * 20);
        } else {
            r = 0;
            g = 0;
            b = 0;
        }
    }

    /**
     * countLiveNeighbors counts the number of live neighbors around a cell.
     *
     * @param cells the 2D array of cells from GameOfLife
     * @return liveNeighbors, the number of live neighbors around a cell. This number is used to determine the life and
     * death of a cell.
     */

    private int countLiveNeighbors(Cell[][] cells) {
        int liveNeighbors = 0;
        for (int r = row - 1; r < row + 2; r++) {
            for (int c = column - 1; c < column + 2; c++) {
                if (cells[r][c].getCellState() == CellState.ALIVE || cells[r][c].getCellState() == CellState.WILL_DIE) {
                    liveNeighbors++;
                }
            }
        }
        if (cellState == CellState.ALIVE) {
            liveNeighbors--;
        }
        return liveNeighbors;
    }
}