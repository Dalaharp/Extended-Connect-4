package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE

    Prahalad Gururajan (PravClem)
    Connor Burkart (crburka)
    Sean Farrell (sfarrellClemosn)
    Parthiv Patel (Parthivp2205)

 */

/**
 *
 *@invariant row <= MAX_ROWS AND row >= MIN_SIZE AND column <= MAX_COL AND column >= MIN_SIZE
 **/
public class BoardPosition
{
    private int row;
    private int column;

    /**
     * The constructor takes in the parameters row and column to store them in an object
     * @param aColumn holds the column position
     * @param aRow holds the row position
     * @pre (aRow <= MAX_ROWS and aRow >= MIN_SIZE) AND (aColumn <= MAX_COL and aColumn >= MIN_SIZE)
     * @post column = aColumn and row = aRow
     */
    public BoardPosition(int aRow, int aColumn)
    {
        //parameterized constructor for BoardPosition
        this.row = aRow;
        this.column = aColumn;
    }
    /**
     * Returns the current position of the row
     *
     * @post getRow = row AND row = #row AND column = #column
     * @return the row stored within the Row field.
     */
    public int getRow()
    {
        return row;
    }
    /**
     * Returns the current position of the column
     *
     * @post row = #row AND column = #column AND getColumn = column
     * @return the column stored within the Column field
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * Checks to see if two objects board position are equal and returns true if two board position have same column and row,
     * and false otherwise
     *
     * @param obj is a BoardPosition object
     * @pre None
     * @post equals iff (this.row == obj.row AND this.column == obj.column AND [this and obj are both instances of BoardPosition]) AND row = #row AND column = #column
     * @return True if the objects this and obj have equal values for row and column and are instances of BoardPosition, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {

        if(!(obj instanceof BoardPosition))
        {
            return false;
        }
        else
        {
            return (row == ((BoardPosition)obj).getRow() && (column == ((BoardPosition)obj).getColumn()));
        }

    }

    /**
     * The method makes it easier to read the current row and column
     *
     * @pre MIN_SIZE <= column <= MAX_SIZE and MIN_SIZE <= row <= MAX_ROWS
     * @post toString = ["<row>, <column>"] AND row = #row AND column = #column AND self = #self
     * @return String with a format that is easy to read in  "<rows>, <columns>"
     */
    public String toString()
    {
        String temp;

        temp = "<" + getRow() + "," + getColumn() + ">";

        return temp;

    }
}
