import java.awt.Color;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class GUI extends GraphicsProgram {
	private static final int ROWS = 4; //number of rows in the background
	private static final int COLUMNS = 4; //number of columns in the background
	private static final double SIZE = 80; //size of each brick (pixels, width and height since they're squares)
	private static final int SEP = 10; //separator size (pixels)
	private static final double WIDTH = COLUMNS*(SIZE+SEP)+SEP;
	private static final double HEIGHT = ROWS*(SIZE+SEP)+SEP;
	static GUI g = new GUI();
	static GRect[][] grid;
	
	public GUI(){
		grid = new GRect[ROWS][COLUMNS];
		for(int i=0; i<ROWS;i++){
			for(int j=0;j<COLUMNS;j++){
				grid[i][j]=new GRoundRect(SIZE,SIZE);
				grid[i][j].setFilled(true);
				grid[i][j].setFillColor(Color.MAGENTA);
				grid[i][j].setLocation(SEP+(SIZE+SEP)*i,SEP+(SIZE+SEP)*j);
			}
		}
	}
	
	public static void main(String[] args){
		String[] sizeArgs = { "width=" + (int)WIDTH, "height=" + (int)HEIGHT};
		g.start(sizeArgs);
		for(int i=0;i<ROWS;i++){
			for(int j=0;j<COLUMNS;j++){
				g.add(grid[i][j]);
			}
		}
		
		Blocks block = new Blocks();
		g.add(block.b);
		g.add(block.l);
		
	}
}
