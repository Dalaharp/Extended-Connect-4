package cpsc2150.extendedConnectX.models;


public abstract class AbsGameBoard implements IGameBoard
{
    /**
     * The method shows the current GameBoard
     * @post toString = [ String representation of the game board ] and self = #self
     *  @return Returns string that shows the board
     */
    @Override
    public String toString()
    {
        String current = "|";

        for(int j = 0; j < getNumColumns(); j++)
        {

            current = current + String.format("%d",j) + "|";


        }

        current = current + "\n";


        for(int i = MAX_ROWS-1; i >= 0; i--)
        {
            current = current + "|";

            for(int k = 0; k < getNumColumns(); k++)
            {
                BoardPosition temp = new BoardPosition(i,k);
                current = current + String.format("%c",whatsAtPos(temp)) + "|";

            }
            current = current + "\n";


        }



        return current;

    }

}
