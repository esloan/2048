
/**
 * Write a description of class Tiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**
     * Constructor for objects of class Tiles
     */
   public class Tiles
{
    public int x;
    public int y;
    public int value;

    /**
     * Constructor for objects of class Tiles
     */
    public Tiles(int x, int y, int value)
    {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public void augmentValue(int value){
        this.value = value*2;
    }
    
}