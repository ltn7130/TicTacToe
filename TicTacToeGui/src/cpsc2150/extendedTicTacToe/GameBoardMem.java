package cpsc2150.extendedTicTacToe;

import java.util.*;

public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private int numToWin;
    private int numRow;
    private int numCol;
    private Map<Character, List<BoardPosition>> board;
    /**
     * @descripton
     * use this class as map contain list of points, after player make a move
     * it will check for valid position, check if that position make a win,
     * and check if the game is tied.
     * @invariant
     * 0 <= row <= NUM_ROW
     * 0 <= col <= NUM_COLUMN
     * [pos is within NUM_ROW X NUM_COLUMN board AND in an available position]
     * board = [the map representation of current state of the game]
     * @Correspondence
     * board = GameBoard AND
     * NUM_TO_WIN = NumWin AND
     * NUM_ROW = NumRow
     * NUM_COLUMN = NumColumn
     */
    public GameBoardMem(int row, int col, int win) {
        numRow = row;
        numCol = col;
        numToWin = win;
        board = new HashMap<>();
    }
    /**
     *This method assign a character of player to an equivalent place in the map of board
     * @pre
     * 0 <= marker.getRow() <= NUM_ROW AND
     * 0 <= marker.getColumn <= NUM_COLUMN AND
     * [board is not full];
     * player = player[i]
     * @post
     * placeMarker = board.get(player).add(marker)
     * @param marker
     * @param player
     */
    @Override
    public void placeMarker(BoardPosition marker, char player) {
        if (board.containsKey(player) == false) {
            board.put(player, new ArrayList<>());
        }
        board.get(player).add(marker);
    }
    /**
     * this method determine which character is at a position
     * @param pos
     * @pre
     * 0 <= pos.getRow() <= NUM_ROW AND
     * 0 <= pos.getColumn <= NUM_COLUMN
     * @post
     * whatsAtPos = m.getKey() iff isPlayerAtPos(pos, m.getKey()) == true
     * whatsAtPos = " " otherwise
     * @return [content at position]
     */
    @Override
    public char whatsAtPos(BoardPosition pos) {
        //iterate the map
        for (Map.Entry<Character, List<BoardPosition>> m : board.entrySet()) {
            //check if pos exits in the list at that key
            if (isPlayerAtPos(pos, m.getKey()) == true) return m.getKey();
        }
        return ' ';
    }
    /**
     * this method checks whether a player character is at a position
     * return true if so, false otherwise
     * @pre
     * 0 <= pos.getRow() <= NUM_ROW AND
     * 0 <= pos.getColumn <= NUM_COLUMN
     * @post
     * true iff p.equals(pos) == true
     * false otherwise
     * @param pos
     * @param player
     * @return true or false
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        // check if map contain that key
        if (board.containsKey(player) == false) return false;
        // iterate the list in the map with key player
        for (BoardPosition p : board.get(player)) {
            //if found pos, return true
            if (p.equals(pos)) return true;
        }
        return false;
    }
    @Override
    public int getNumRows() {
        return numRow;
    }

    @Override
    public int getNumColumns() {
        return numCol;
    }

    @Override
    public int getNumToWin() {
        return numToWin;
    }
}

