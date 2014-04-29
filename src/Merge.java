
/**
 * Write a description of class Merge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Merge
{
    // instance variables - replace the example below with your own
    public boolean gridFull()
    {
        for(int i=0; i<WIDTH; i++)
        {
            for (int n=0; n<WIDTH; n++)
            {
                if(grid[x][y].isZeroValue())
                {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean movePossible()
    {
        for (int i=0; i<WIDTH; i++)
        {
            for(int n=0; n<(WIDTH-1); y++)
            {
                int m= n+1;
                if(grid[i][n].getValue()==grid[i][m].getValue())
                {
                    return true;
                }
            }
        }
        return false;
    }
    
}
