/**
 * Le Nguyen
 */
package cpsc2150.extendedTicTacToe;
import java.util.*;

/**
 * This Object will hold the information of the game board, check the result after
 * each move to determine if a player has won or the game has been draw.
 *
 * @defines:
 * NumRow: the number of rows of the board
 * numColumn: the number of columns of the board
 * GameBoard: the game board with size numRow x numColumn
 * numToWin: the number of consecutive same character to reach to win the game
 * BoardPosition: the exact location in the game board
 * count: count number of consecutive character in a row, column or diagonal
 * @Initialization Ensures:
 * initial GameBoard will have all blank element in fast game.
 * NumRow,NumColumn, and NumToWin will be assigned with fixed values.
 * @constraints
 * NUM_ROW = numRow
 * NUM_COLUMN = numCol
 * NUM_TO_WIN = numWin
 * 0 <= row < NUM_ROW
 * 0 <= col < NUM_COLUMN
 */

public interface IGameBoard {
    public static final int minCol = 3;
    public static final int minRow = 3;
    public static final int minNumWin = 3;
    /**
     *This method assign a character of player to an equivalent grid in game board
     * @pre
     * 0 <= marker.getRow() <= NUM_ROW AND
     * 0 <= marker.getColumn <= NUM_COLUMN AND
     * board[marker.getRow()][marker.getColumn] = ' ';
     * @post
     * board[marker.getRow()][marker.getColumn()] = player
     * @param marker
     * @param player
     */
    public void placeMarker(BoardPosition marker, char player);
    /**
     * this method determine which character is at a position
     * @param pos
     * @pre
     * 0 <= pos.getRow() <= NUM_ROW AND
     * 0 <= pos.getColumn <= NUM_COLUMN
     * @post
     * whatsAtPos = [the content at pos (player1, player 2, or empty)]
     * @return [content at position]
     */
    public char whatsAtPos(BoardPosition pos);
    /**
     * this method return the number of row
     * @post
     * getNumRows =NUM_ROW
     * @return [number of rows]
     */
    public int getNumRows();
    /**
     * This method return the number of column
     * @post
     * getNumColumn = NUM_COLUMN
     * @return [number of column]
     */
    public int getNumColumns();
    /**
     * this method return the number of consecutive character to make a win
     * @post
     * getNumWin = NUM_TO_WIN
     * @return [number of consecutive character to make a win]
     */
    public int getNumToWin();

    /**
     * check if the position is valid.
     * return true if the position specified in pos is available
     * return false if the position is not available or out of the default board
     * @post
     * true iff whatsAtPos(pos) = " " AND
     *          pos.getRow() < MAX_ROW AND
     *          pos.getRow() >= 0
     *          pos.getColumn() < MAX_COL AND
     *          pos.getColumn() >= 0
     * false otherwise
     * @param pos
     * @return true or false
     */
    default boolean checkSpace(BoardPosition pos)
    {
        //if position has character different to space, return false
        //otherwise return true;
        if(pos.getRow() < 0 || pos.getRow() >= getNumRows()
                || pos.getColumn() < 0 || pos.getColumn() >= getNumColumns()
                || this.whatsAtPos(pos) != ' ') return false;
        return true;
    }
    /**
     * This method checks to see if the game has resulted in a tie
     * It is tied if there are not free board position remaining
     * @pre
     * [no player has not won]
     * @post
     * true iff board[i][j] != " " AND
     * i >= 0 AND i < MAX_ROW AND j >= 0 AND j < MAX_COLUMN
     *
     * false otherwise
     * @return true or false
     */
    default boolean checkForDraw()
    {
        //if one of grid is empty, return false
        // if all full, return true
        for(int i = 0; i < getNumRows(); ++i)
        {
            for (int j =0; j < getNumColumns(); ++j)
            {
                BoardPosition pos = new BoardPosition(i,j);
                if(this.whatsAtPos(pos) == ' ') return false;
            }
        }
        return true;
    }
    /**
     * This method use the lastPos to check whether that position has
     * make a win. If so it will return true, otherwise return false
     *
     * @pre
     * checkSpace(lastPos) = true
     * @post
     * true iff checkHorizontalWin(marker, player) = true or
     *          checkVerticalWin(marker, player) = true or
     *          checkDiagonalWin(marker, player) = true
     * false otherwise
     * @param lastPos
     * @return true or false 
     */
    default boolean checkForWinner(BoardPosition lastPos)
    {
        //return true if horizontal or vertical or diagonal win
        char player = whatsAtPos(lastPos);
        if (checkHorizontalWin(lastPos,player) == true ||
                checkVerticalWin(lastPos,player) == true ||
                checkDiagonalWin(lastPos,player) == true) return true;
        return false;
    }
    /**
     * @description
     * this method checks whether the last position has made a win with
     * 5 same characters next to each other on a row.
     * @pre
     * checkSpace(pos) = true
     * player = player1 or player2
     *
     * @post
     * count = count + 1 iff found same character with player at position
     * count = 0 otherwise
     * true iff count = NumToWin
     * false otherwise
     *
     * @param lastPos
     * @param player
     * @return true or false
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        //use count to count number of consecutive in a same row
        int count = 0;
        //loop to the row contain lastPos and count consecutive matching charter with player
        for (int i = 0; i < getNumColumns(); ++i)
        {
            BoardPosition pos = new BoardPosition(lastPos.getRow(), i);
            //if different player found on a grid, reset count to 0;
            if(this.whatsAtPos(pos) != player) {
                count = 0;
            }
            else if (this.whatsAtPos(pos) == player) count ++;
            if( count == getNumToWin()) return true;

        }
        return false;
    }
    /**
     * @description
     * this method checks whether the last position has made a win with
     * 5 same characters next to each other on a column
     *
     * @pre
     * checkSpace(pos) = true
     * player = player1 or player2
     * @post
     * count = count + 1 iff [found same character with player at position]
     * count = 0 otherwise
     * true iff count = NumToWin
     * false otherwise
     * @param lastPos
     * @param player
     * @return true or false
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        int count = 0;
        //loop to the column contain lastPos and count consecutive matching charter with player
        for (int i = 0; i < getNumRows(); ++i)
        {
            BoardPosition pos = new BoardPosition(i, lastPos.getColumn());
            if(this.whatsAtPos(pos) == player) count ++;
            if( count == getNumToWin()) return true;
            //if different player found on a grid, reset count to 0;
            if(this.whatsAtPos(pos) != player)
            {
                count = 0;
            }
        }
        return false;
    }
    /**
     * this method checks whether the last position has made a win with
     * 5 same characters next to each other on left or right diagonal
     *
     * @pre
     * checkSpace(pos) = true
     * player = player1 or player2
     * @post
     * left diagonal = [list of character on the left diagonal that contains lasPos]
     * right diagonal = [list of character on the right diagonal that contains lasPos]
     * count = count + 1 iff [found same character with player at position]
     * count = 0 otherwise
     * true iff count = NumToWin
     * false otherwise
     * @param lastPos
     * @param player
     * @return true or false
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        //create a list of left and right  diagonal which contain lastPos
        List<Character> leftDiagonal = new ArrayList<>();
        List<Character> rightDiagonal = new ArrayList<>();
        int col = lastPos.getColumn();
        //load character to upper left diagonal list
        for(int row = lastPos.getRow(); row >= 0; --row)
        {
            if(col >= getNumColumns() || col < 0 || row >= getNumRows() ||row < 0) break;
            BoardPosition pos = new BoardPosition(row, col);
            leftDiagonal.add(whatsAtPos(pos));
            ++col;
        }
        //load character to bottom left diagonal list
        col = lastPos.getColumn() - 1;
        for (int row = lastPos.getRow() +1; row < getNumRows(); ++row)
        {
            if(col >= getNumColumns() || col < 0 || row >= getNumRows() ||row < 0) break;
            BoardPosition pos = new BoardPosition(row, col);
            leftDiagonal.add(0,whatsAtPos(pos));
            --col;
        }
        //loop the list of left diagonal and count
        int count = 0;
        for (char x : leftDiagonal)
        {
            if ( x == player) count ++;
            if(count == getNumToWin()) return true;
            if ( x != player) count = 0;
        }
        //load character to upper right diagonal list
        col = lastPos.getColumn();
        for (int row = lastPos.getRow(); row >= 0; --row)
        {
            if (col >= getNumColumns() || col < 0 || row >= getNumRows() ||row < 0) break;
            BoardPosition pos = new BoardPosition(row, col);
            rightDiagonal.add(whatsAtPos(pos));
            --col;
        }
        //load character to bottom right diagonal list
        col = lastPos.getColumn() + 1;
        for (int row = lastPos.getRow() + 1; row <  getNumRows(); ++row)
        {
            if(col >= getNumColumns()|| col < 0 || row >= getNumRows() || row < 0) break;
            BoardPosition pos = new BoardPosition(row, col);
            rightDiagonal.add(0,whatsAtPos(pos));
            ++col;
        }
        //loop the right diagonal and count
        count = 0;
        for (char x : rightDiagonal)
        {
            if ( x == player) count ++;
            if(count == getNumToWin()) return true;
            if ( x != player) count = 0;
        }
        return false;
    }

    /**
     * this method checks whether a player character is at a position
     * return true if so, false otherwise
     * @pre
     * 0 <= pos.getRow() <= NUM_ROW AND
     * 0 <= pos.getColumn <= NUM_COLUMN
     * @post
     * true iff whatsAtPos(pos) == player
     * false otherwise
     * @param pos
     * @param player
     * @return true or false
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(whatsAtPos(pos) == player) return true;
        return false;
    }
}
