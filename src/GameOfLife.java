import processing.core.PApplet;

/**
 * GameOfLife is the superclass. It sets up the program, initializes the 2D array of cells, and displays that same 2D
 * array of cells. It uses its subclasses to explore other functionalities of GameOfLife.
 */

public class GameOfLife extends PApplet {
    private Cell[][] cells; // cells is the 2D array of cells in GameOfLife.
    private boolean evolve; // when evolve is true, the cells evolve.
    private CellState cellState;
    /* cellState is the "status" of a cell. A cell can be dead or alive, and when cells are
                                       evolving, a cell can also be in line to die or in line to be born.*/
    private Rules rules; // rules is the rule applied to GameOfLife.
    private int rule = 1;
    /* rule controls the rules applied to GameOfLife.
     * When rule = 0, the basic B3/S23 rule is applied.
     * When rule = 1, the B35/S4 rule is applied.
     * When rule = 2, the B3/S012345678 rule is applied.
     */
    private static GameOfLife app;
    private static final int CELL_SIZE = 10; // this is how big a cell will be.

    /**
     * main is the first method called in GameOfLife. It essentially starts the whole program.
     * @param args arguments in an array of type String that we don't use. They would be command-line parameters.
     */

    public static void main(String[] args) {
        app = new GameOfLife();
        app.runSketch();
    }

    /**
     * GameOfLife initializes variables that can give us more details later. When app is initialized, it is initializing
     * the object that refers to itself. Evolve is also set to false here because we don't want the program to start
     * evolving immediately.
     */

    public GameOfLife() {
        app = this;
        evolve = false;
    }

    /**
     * settings overrides the settings method in PApplet. Here, it is setting the size of the display window to 1000 x 500.
     */

    @Override
    public void settings() {
        super.settings();
        size(1000, 500);
    }

    /**
     * setup overrides the setup method in PApplet. Here, it is initializing the 2D array cells and determining the size
     * of cells using the dimensions of the canvas and CELL_SIZE. Then, setup initializes rules to the desired rule and
     * assigns a cell with a CellState to each "slot" in the array cells. Lastly, setup sets the frame rate to 5 because
     * the default frame rate in PApplet is very fast.
     */

    @Override
    public void setup() {
        cells = new Cell[height / CELL_SIZE][width / CELL_SIZE];
        if (rule == 0) {
            rules = new MooreRules(new int[] {
                    3
            }, new int[] {
                    2,
                    3
            }, rule);
        } else if (rule == 1) {
            rules = new MooreRules(new int[] {
                    3,
                    5
            }, new int[] {
                    4
            }, rule);
        } else {
            rules = new MooreRules(new int[] {
                    3
            }, new int[] {
                    0,
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8
            }, rule);
        }
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[0].length; column++) {
                CellState cellstate;
                if (row == 0 || row == cells.length - 1 || column == 0 || column == cells[0].length - 1) {
                    cellState = CellState.DEAD;
                } else {
                    cellState = CellState.DEAD;
                }
                Cell cell = new Cell(column * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, row, column, cellState, rules, 0);
                cells[row][column] = cell;
            }
        }
        frameRate(5);
    }

    /**
     * draw overrides the draw method in PApplet. If evolve = true, then draw calls the applyRules method to assign each
     * cell its "fate state," or a state that will determine what will happen to it in the next life (like WILL_DIE).
     * Once each cell has its "fate state," draw calls evolve and all the cells move on to the next generation. Lastly,
     * the next generation of cells is displayed.
     */

    @Override
    public void draw() {
        if (evolve) {
            applyRules();
            evolve();
            history();
        }
        display();
    }

    /**
     * mouseClicked overrides the mouseClicked method in PApplet. Howver, since mouseClicked also calls super.mouseClicked,
     * it does the things super.mouseClicked would do and then adds some extra steps. When a cell is clicked,
     * mouseClicked calls handleClick which changes both the fill of the cell and the cellState of the cell. If the cell is dead, then it becomes alive,
     * and if the cell is alive, it dies.
     */

    @Override
    public void mouseClicked() {
        super.mouseClicked();
        int col = mouseX / CELL_SIZE;
        int row = mouseY / CELL_SIZE;
        cells[row][col].handleClick();
    }

    /**
     * keyPressed overrides the keyPressed method in PApplet. However, since keyPressed also calls super.keyPressed,
     * it does the things super.keyPressed would do and then adds some extra steps. When a key is pressed, then the
     * program either starts evolving or stops evolving depending on whether or not the program is already evolving.
     */

    @Override
    public void keyPressed() {
        super.keyPressed();
        evolve = !evolve;
    }

    /**
     * getApp allows other classes to access the app, and since the app is a reference to the class it allows other
     * places access this class. This is also an object of PApplet so it allows us to access PApplet methods.
     * @return app
     */

    public static GameOfLife getApp() {
        return app;
    }

    //applyRules goes through the 2D array cells and applies the evolutionary rules to each cell one by one.
    private void applyRules() {
        for (int r = 1; r < cells.length - 1; r++) {
            for (int c = 1; c < cells[0].length - 1; c++) {
                cells[r][c].applyRules(cells);
            }
        }
    }

    //evolve goes through the 2D array cells and evolves each cell individually through the evolve method in the cells class.
    private void evolve() {
        for (int r = 1; r < cells.length - 1; r++) {
            for (int c = 1; c < cells[0].length - 1; c++) {
                Cell x = cells[r][c];
                x.evolve();
            }
        }
    }

    //display goes through the 2D array cells and displays each cell individually through the display method in the cells class.
    private void display() {
        for (int r = 1; r < cells.length - 1; r++) {
            for (int c = 1; c < cells[0].length - 1; c++) {
                Cell x = cells[r][c];
                x.display();
            }
        }
    }

    //history goes through the 2D array cells and displays each cell individually through the display method in the cells class.
    private void history() {
        for (int r = 0; r < cells.length - 1; r++) {
            for (int c = 1; c < cells[0].length - 1; c++) {
                Cell x = cells[r][c];
                x.history();
            }
        }
    }
}