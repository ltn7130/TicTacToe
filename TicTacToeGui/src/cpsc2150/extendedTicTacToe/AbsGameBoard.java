package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * This method overrides {@link Object#toString()} to provide a string
     * representation for the game board
     * @pre
     * [game board has been initialized]
     * @post
     * toString = [A string representation for game board]
     * @return a string representation for the game board
     */
    @Override
    public String toString() {
        String board = "";
        board += ("   ");
        for (int i = 0; i < getNumColumns(); ++i)
        {
                if (i < 10) board += (" " +i + "|");
                else board += (i + "|");
        }
        board += ("\n");
        for (int i = 0; i < getNumRows(); ++i)
        {
                if(i < 10) board += (" " + i + "|");
                else board += (i + "|");
            for (int j = 0; j < getNumColumns(); ++j)
            {
                BoardPosition pos = new BoardPosition(i,j);
                board += (whatsAtPos(pos) + " |");
            }
            board += ("\n");
        }
        return  board;
    }
}
