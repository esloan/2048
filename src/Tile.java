
import java.awt.Color; 
import java.util.Random;
import acm.program.*;
import acm.graphics.*;
/**
 * Constructor for objects of class Tiles
 */

public class Tile
{
	private static final double SIZE = 80; // size of each brick (pixels, width
	// and height since they're squares)
	private static final int SEP = 10; // separator size (pixels)
	public int x;
	public int y;
	public int value;
	public GLabel l;
	Color [] colors ={new Color(255, 51, 51),new Color(255,153,51),
			new Color(255,255,51),new Color(51,255,51),new Color(51,255,153),
			new Color(51,255,255),new Color(51,153,255),new Color(51,51,255),new Color(153,51,255),new Color(255,51,153)};
	//Color tcolor=new Color(240, 240, 15);
	public GRoundRect tile;
	/**
	 * Constructor for objects of class Tiles
	 */
	public Tile(int x, int y, int value)
	{
		this.x = x;
		this.y = y;
		this.value = value;
		tile=new GRoundRect((double)x,(double)y,80,80);
		tile.setFilled(true);
		int c = (int)(Math.log(value)/Math.log(2)) -1;
		if (c > 10) { c = c%10;}
		tile.setColor(colors[(int)(Math.log(value)/Math.log(2)) -1]);
		l=new GLabel(String.valueOf(value),x+40,y+40);
		l.sendToFront();
	}

	public int getX() {
		return x;
	}

	public int getGridX(){
		return (int)(x-SEP)/(int)(SEP+SIZE);
	}
	
	public int getGridY(){
		return (int)(y-SEP)/(int)(SEP+SIZE);
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
		int c = (int)(Math.log(value)/Math.log(2)) -1;
		if (c > 10) { c = c%10;}
		tile.setColor(colors[5]);
		l.setLabel(String.valueOf(value));

		System.out.println(c);
	}
	public void augmentValue(int value){

		int c;
		c = (int)(Math.log(value)/Math.log(2)) -1;
		if (c > 10) { c = c%10;}

		this.value = value*2;

		tile.setColor(colors[c]);
		System.out.println(value);
		l.setLabel(String.valueOf(value));
	}

}

