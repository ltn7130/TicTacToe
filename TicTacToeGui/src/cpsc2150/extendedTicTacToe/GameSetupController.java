package cpsc2150.extendedTicTacToe;

import static cpsc2150.extendedTicTacToe.IGameBoard.*;

/**
 * The GameSetupController class will handle communication between our GameSetupScreen
 * and start a new game when all the required fields to build an IGameBoard is met.
 * <p>
 * You do not need to make any changes to this code.
 */
public class GameSetupController {

    private GameSetupScreen view;
    private int max_size = 20;
    private final int MEM_CUTOFF = 64;
    //initialize view
    public GameSetupController(GameSetupScreen v) {
        view = v;
    }

    public void processButtonClick(int rows, int cols, int players, int numWin) {
        String errorMsg = "";
        if (rows < minRow || rows > max_size) {
            errorMsg += "Rows must be between " + minRow + " and " + max_size;
        }

        if (cols < minCol || cols > max_size) {
            errorMsg += "Columns must be between " + minCol + " and " + max_size;
        }

        if (numWin > rows) {
            errorMsg += "Can't have more to win than the number of rows";
        }
        if (numWin > cols) {
            errorMsg += "Can't have more to win than the number of Columns";
        }

        if (numWin < minNumWin) {
            errorMsg += "Number to win must be at least " + minNumWin;
        }

        if (!errorMsg.equals("")) {
            view.displayError(errorMsg);
        } else {
            view.closeScreen();
            IGameBoard model;
            if (rows * cols <= MEM_CUTOFF) {
                model = new GameBoard(rows, cols, numWin);
            } else {
                model = new GameBoardMem(rows, cols, numWin);
            }
            TicTacToeView tview = new TicTacToeView(rows, cols);
            TicTacToeController tcontroller = new TicTacToeController(model, tview, players);

            tview.registerObserver(tcontroller);
        }
    }
}