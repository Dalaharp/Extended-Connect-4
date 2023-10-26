package cpsc2150.extendedConnectX.models;

/**
 * This is an interface for game board. Game board is a 2-D array board of blank spaces.
 * The following interface contains some of the methods which are secondary methods implemented
 * using the primary methods. This game is based on player input makers: 'X' or 'O'. if they have consecutive five makers in any direction, they win.
 *
 * Initialization ensure: Game Board is created with an array of balcnk spaces of size: getNumRows() * getNumColumns
 *
 * Defines:
 numRow = Z;
 numCol = Z;
 numToWin = Z;
 * Constraints :
 *         MIN_SIZE <= rows <= MAX_ROWS
 *         MIN_SIZE <= cols <= MAX_COL
 *         MIN_SIZE <= toWin <= NUM_TO_WIN
 */
public interface IGameBoard
{
    public static final int MIN_SIZE = 0;
    public static final int MAX_ROWS = 9;
    public static final int MAX_COL = 7;
    public static final int NUM_TO_WIN = 5;


    /**
     * Places the character token 'p' in column 'c'. The token will be placed in the lowest available row in column 'c'
     * @param p The character token to be placed in the column
     * @param c The column where the token should be placed. colum c is intended to be a colum that is not full
     * @pre (c >= MIN_SIZE and c <= MAX_ROWS) AND p is a valid token and checkifFree(int c) = true
     * @pre p == 'X' AND p == '0' AND c >= MIN_SIZE and c <= MAX_COLUMNS
     * @post dropToken = [The game board will be updated with the token placed in the lowest
     *  available row of column 'c'. Rest of the board has remained the same] self =#self
     * @post the lowest empty slot in column c = char p.
     */
    public void dropToken(char p, int c);

    /**
     * Retrieves the character at the specified position on the game board
     * @param pos is a BoardPosition object that gives the coordinates of a cell.
     * @post whatsAtPos = [Returns char at pos in [pos.getRow][pos.getColumn]] AND self == #self
     * @return Returns The character at position pos, or a blank space character if no marker is there.
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * Checks if a player's token is at the specified position on the game board.
     * @pre (player != NULL) AND (player == 'X') AND (player == 'O')
     * @pre pos is a valid position on the game board
     * @param pos    The position in grid
     * @param player The player's token ('X' or 'O')
     * @post isPlayerAtPos = [Returns true if the player is at pos] AND self == #self
     * @return Returns true if the player's token is at position pos false otherwise
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        if(whatsAtPos(pos) == player)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Checks if a column can accept another token.
     * @param c The column to check.
     * @pre c >= MIN_SIZE and c <= MAX_COL
     * @post checkIfFree = [Returns true if the column is filled with pieces otherwise returns false] AND self = #self
     * @return Returns true if the column can accept another token, false otherwise.
     */
    public default boolean checkIfFree(int c) {

        BoardPosition temp = new BoardPosition(8,c);

        if(whatsAtPos(temp) == ' ')
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * Checks if the last token placed in column 'c' resulted in the player winning the game
     * @param c The column where the last token was placed
     * @pre c >= MIN_SIZE AND c <= MAX_COL
     * @pre The input column index 'c' must be a valid column within the game board
     * @post checkForWin = [Returns true if 5 of the same type of pieces are in a row horizontal or diagonally or vertically] AND self == #self
     * @return Returns true if the last token placed in column 'c' resulted in a win, otherwise false
     */
    public default boolean checkForWin(int c)
    {
       // int rowPosition = -1;
        for(int r = 0; r < getNumRows(); r++) {
            BoardPosition pos = new BoardPosition(r, c);
            char token = whatsAtPos(pos);
            if (token == ' ') {
                continue;
            }

        if (checkVertWin(pos, token) || checkHorizWin(pos, token) || checkDiagWin(pos, token)) {
            return true;
        }
    }
        return false;
}

    /**
     * Checks if there is a vertical win starting from the given position.
     *
     * @param pos The position to start checking from.
     * @param p   The player's token ('X' or 'O')
     * @post checkVertWin = [returns true if 5 piece of the same type are 5 in a row vertically from each other
     * Otherwise, returns false] AND self == #self,
     * @return Returns true if there are 5 in a row vertically false otherwise
     *
     */
    public default boolean checkVertWin(BoardPosition pos, char p)
    {
        int marker = 1; // You have at least one already

        int numBelow = pos.getRow();

        while(numBelow > 1)
        {
            numBelow = pos.getRow();

            BoardPosition temp = new BoardPosition(pos.getRow() - 1, pos.getColumn());

            // Just have to check below because you can not place a marker below another
            for(int i = 0; i < numBelow;i++)
            {
                if(temp.getRow() >= 0 && temp.getRow() < MAX_ROWS)
                {
                    if (marker == getNumToWin()) {
                        return true;
                    } else if (isPlayerAtPos(temp, p)) {
                        marker++;
                        temp = new BoardPosition(temp.getRow() - 1, temp.getColumn());
                    }
                }
            }

            if(marker == NUM_TO_WIN)
            {
                return true;
            }
            else
            {
                return false;
            }


        }

        return false;

    }

    /**
     * Detects if a player has won the game by 5 peaces horizontally next to each-other.
     * @param pos game-board position of current piece being checked
     * @param p the piece object being tested if 5 are horizontally next to each other
     * @pre  MIN_SIZE <= pos.getRow() < getNumRows() AND
     *      MIN_SIZE <= pos.getColumn() < getNumColumns()
     * @post checkHorizWin = [returns true if 5 piece of the same type are 5 in a row horizontally from each other  Otherwise, returns false] AND self = #self
     * @return Returns true if the same type species are 5 in a row horizontally otherwise returns false
     */
    public default boolean checkHorizWin(BoardPosition pos, char p)
    {
        int marker = 1;

        int numLeft = pos.getColumn();
        int numRight = getNumColumns() - pos.getColumn();

        BoardPosition temp = new BoardPosition(pos.getRow() , pos.getColumn() + 1);

        // Checking to the right
        for(int i = 0; (i < numRight);i++)
        {
            if(temp.getColumn() < getNumColumns())
            {

                if (isPlayerAtPos(temp, p) == true) {
                    marker++;
                    temp = new BoardPosition(temp.getRow(), temp.getColumn() + 1);
                } else {
                    break;
                }

            }
        }

        // Checking to the left
        if(numLeft > 0)
        {
            temp = new BoardPosition(pos.getRow(), pos.getColumn() - 1);

            for(int j = 0; j < numLeft ;j++)
            {
                if(marker == NUM_TO_WIN)
                {
                    return true;
                }
                else if(isPlayerAtPos(temp,p))
                {
                    marker++;
                    temp = new BoardPosition(temp.getRow(), temp.getColumn() - 1);

                }

            }

        }


        if(marker == NUM_TO_WIN)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * Checks if there is a diagonal win starting from the given position.
     *
     * @param pos The position to start checking from.
     * @param p   The player's token ('X' or 'O')
     * @pre [pos is a valid position on the game board]
     * @post checkDiadWin = [returns true if 5 piece of the same type are 5 in a row diagonally from each other
     *  Otherwise, returns false] AND self == #self
     * @return Returns true if there are 5 in a row diagonally false otherwise
     */
    public default boolean checkDiagWin(BoardPosition pos, char p)
{

    int marker = 1;
    BoardPosition temp = new BoardPosition(pos.getRow(),pos.getColumn());

    if((pos.getRow() >= 0) && (pos.getRow() < MAX_ROWS) && (pos.getColumn() >= 0) && (pos.getColumn() <=MAX_COL))
    {
        temp = new BoardPosition(pos.getRow() + 1, pos.getColumn() + 1);

    }
    if((pos.getRow() >= 0) && (pos.getRow() < MAX_ROWS) && (pos.getColumn() >= 0) && (pos.getColumn() <=MAX_COL)) {



            // This is the algorithm for moving it up and right
            for (int i = temp.getColumn(); i < getNumColumns(); i++) {
                for (int j = temp.getRow(); j > 0; j--)
                {
                    if ((temp.getRow() >= 0) && (temp.getRow() < MAX_ROWS) && (temp.getColumn() >= 0) && (temp.getColumn() < MAX_COL)) {
                        if (isPlayerAtPos(temp, p) == true) {
                            marker++;
                            temp = new BoardPosition(temp.getRow() + 1, temp.getColumn() + 1);
                        } else {
                            break;
                        }
                    }

                }

            }

            // This is the rest of the algorithm that moves down and left
            temp = new BoardPosition(pos.getRow() - 1, pos.getColumn() - 1);

            for (int i = temp.getColumn(); i > 0; i--) {
                for (int j = temp.getRow(); j < getNumRows(); j++)
                {
                    if ((temp.getRow() >= 0) && (temp.getRow() < MAX_ROWS) && (temp.getColumn() >= 0) && (temp.getColumn() < MAX_COL)) {

                        if (isPlayerAtPos(temp, p) == true) {
                            marker++;
                            temp = new BoardPosition(temp.getRow() - 1, temp.getColumn() - 1);
                        } else {
                            break;
                        }
                    }

                }

            }

        if (marker == getNumToWin()) {
            return true;
        }

            marker = 1;
            // Now to check for the other directions
            // This goes down and right
            temp = new BoardPosition(pos.getRow() - 1, pos.getColumn() + 1);

            for (int i = temp.getColumn(); i < getNumColumns(); i++) {
                for (int j = temp.getRow(); j < getNumRows(); j++) {
                    if ((temp.getRow() >= 0) && (temp.getRow() < MAX_ROWS) && (temp.getColumn() >= 0) && (temp.getColumn() < MAX_COL)) {

                        if (isPlayerAtPos(temp, p) == true) {
                            marker++;
                            temp = new BoardPosition(temp.getRow() - 1, temp.getColumn() + 1);
                        } else {
                            break;
                        }
                    }

                }

            }

            // This goes up and left
            temp = new BoardPosition(pos.getRow() + 1, pos.getColumn() - 1);

            for (int i = marker; i < getNumToWin(); i++) {
                if ((temp.getRow() >= 0) && (temp.getRow() < MAX_ROWS) && (temp.getColumn() >= 0) && (temp.getColumn() < MAX_COL)) {

                    if (isPlayerAtPos(temp, p) == true) {
                        marker++;
                        temp = new BoardPosition(temp.getRow() + 1, temp.getColumn() - 1);
                    } else {
                        break;
                    }
                }
            }


            if (marker == getNumToWin()) {
                return true;
            } else {
                return false;
            }


    }

    return false;







}

    /**
     *
     * Function that checks if the player has tied the game or not by using checkIfFree function.
     * @pre checkForWin() != true
     * @post checkTie = [returns true if board is full and there are no wins, Otherwise, returns false] AND self = #self
     * @return Returns true if games are tied with every colum being populated with none of the same type of pieces being 5 in a row; horizontally,vertically, and diagonally, otherwise return false
     */
    public default boolean checkTie()
    {

        for(int i = 0; i < getNumColumns();i++)
        {
            if(checkIfFree(i) == true)
            {
                return false;

            }
        }

        return true;


    }
    /**
     * Returns number of rows
     *
     * @return Returns the number of rows.
     * @post getNumRows() = numRows AND numRows =#numRows AND numCol = #numCol AND numToWin = #numToWin
     */
    public int getNumRows();
    /**
     * Returns number of rows
     *
     * @return Returns the number of Columns.
     * @post getNumColumns() = numCol AND numRows =#numRows AND numCol = #numCol AND numToWin = #numToWin
     */
    public int getNumColumns();
    /**
     * Returns number of rows
     *
     * @return Returns the number in a row to win.
     * @post getNumRows() = numToWin AND numRows =#numRows AND numCol = #numCol AND numToWin = #numToWin
     */
    public int getNumToWin();
}