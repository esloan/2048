
import java.awt.Color; 
import java.util.Random;
import acm.program.*;
import acm.graphics.*;
/**
     * Constructor for objects of class Tiles
     */
   public class Tiles
{
    public int x;
    public int y;
    public int value;
    Color tcolor=new Color(240, 240, 15);
    public GRoundRect tile;
    /**
     * Constructor for objects of class Tiles
     */
    public Tiles(int x, int y, int value)
    {
        this.x = x;
        this.y = y;
        this.value = value;
        tile=new GRoundRect(10,10);
        tile.setColor(tcolor);
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

