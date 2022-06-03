
package cpsc2150.extendedTicTacToe;
/**
 * this class is used to store the position coordinating
 * to value of row and col
 * @invariant
 * row >=0 and row < NUM_ROW
 * col >=0 and col < NUM_COLUMN
 *
 */
public class BoardPosition {
    private int row;
    private  int col;

    /**
     * This constructor initializes value of row and col
     * @param r
     * @param c
     * @pre
     * 0 <= r < NUM_ROW AND
     * 0 <= c < NUM_COLUMN
     * @post
     * row = r
     * col = c
     */
    public BoardPosition(int r, int c)
    {
        row = r;
        col = c;
    }

    /**
     * this method return the row value of potion
     * @post
     * getRow = row
     * @return row
     */
    public int getRow() {
        return  row;
    }

    /**
     * This method return the column value of position
     * @post
     * getColumn = col
     * @return col
     */
    public int getColumn(){
        return col;
    }
    @Override
    /**
     * This method overrides {@link Object#equals()} to provide comparison
     * of 2 BoardPosition objects
     * @param obj
     * @pre
     * [obj has been initialized]
     * @post
     * false iff obj = null OR
     *          obj.getClass() != this.getClass() OR
     *          obj.getROw != row OR
     *          obj.getColumn != col
     * True otherwise
     * return: true or false
     */
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final BoardPosition pos = (BoardPosition) obj;
        if (pos.getRow() == this.getRow() && pos.getColumn() == this.getColumn())
        {
            return true;
        }
        return false;
    }

    /**
     * This method overrides {@link Object#toString()} to provide a string
     * representation for BoardPosition object
     * @pre
     * [BoardPosition has been initialized before modified]
     * @return the string representation of current state of GameBoard;
     */
    @Override
    public String toString()
    {
        String position  = new String();
        position = String.valueOf(this.getRow() + ",")  +
                   String.valueOf(this.getColumn());
         return position;
    }
}
