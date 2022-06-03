
package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.List;
/**
 * @descripton
 * use this class as a 5x8 board, after player make a move
 * it will check for valid position, check if that position make a win,
 * and check if the game is tied.
 * @invariant
 * 0 <= row <= NUM_ROW
 * 0 <= col <= NUM_COLUMN
 * [pos is within NUM_ROW X NUM_COLUMN board AND in an available position]
 * board[NUM_ROW][NUM_COLUMN] = [ game board represent current state of the game]
 * @Correspondence
 * board = GameBoard AND
 * NUM_TO_WIN = NumWin AND
 * NUM_ROW = NumRow
 * NUM_COLUMN = NumColumn
 */
public class GameBoard extends AbsGameBoard implements  IGameBoard{

    private Character board[][];
    private int NUM_TO_WIN;
    private int NUM_ROW;
    private int NUM_COLUMN;
    /**
     * @description
     * this constructor initializes a game board to all blank element
     * @post
     * NUM_COLUMN = row AND
     * NUM_ROW = col AND
     * NUM_TO_WIN = numWin AND
     * GameBoard =  [blank set of 2d character array]
     */
    public GameBoard(int row, int col, int numWin)
    {
        NUM_COLUMN = col;
        NUM_ROW = row;
        NUM_TO_WIN = numWin;
        //make all grids of the board empty
        board =  new Character[NUM_ROW][NUM_COLUMN];
        for(int i = 0; i < NUM_ROW; ++i)
        {
            for (int j = 0; j < NUM_COLUMN; ++j)
            {
                board[i][j] = ' ';
            }
        }
    }
    @Override
    public void placeMarker(BoardPosition marker, char player) {
        board[marker.getRow()][marker.getColumn()] = player;
    }
    @Override
    public char whatsAtPos(BoardPosition pos) {
        return board[pos.getRow()][pos.getColumn()];
    }
    @Override
    public int getNumRows()
    {
        return NUM_ROW;
    }
    @Override
    public int getNumColumns()
    {
        return NUM_COLUMN;
    }
    @Override
    public int getNumToWin() {
        return  NUM_TO_WIN;
    }
}
