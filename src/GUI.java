import java.applet.Applet;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.util.Random;
import java.awt.event.*;

public class GUI extends GraphicsProgram {
	private static final int ROWS = 4; // number of rows in the background
	private static final int COLUMNS = 4; // number of columns in the background
	private static final double SIZE = 80; // size of each brick (pixels, width
	// and height since they're squares)
	private static final int SEP = 10; // separator size (pixels)
	private static final double WIDTH = COLUMNS * (SIZE + SEP) + SEP;
	private static final double HEIGHT = ROWS * (SIZE + SEP) + SEP;
	static GUI gui = new GUI();
	private int[][] Grid = new int[ROWS][COLUMNS];
	ArrayList<Point> available = new ArrayList<Point>();
	ArrayList<Tile> tiles = new ArrayList<Tile>();

	public void init() {
		// setBackground(Color.blue);//sets background color

		GRect[][] grid = new GRect[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				grid[i][j] = new GRoundRect(SIZE, SIZE);
				grid[i][j].setFilled(true);
				grid[i][j].setFillColor(Color.MAGENTA);
				gui.add(grid[i][j], SEP + (SIZE + SEP) * i, SEP + (SIZE + SEP)* j);
			}
		}

		gui.addKeyListener(this);

		for (int i = 0; i < Grid.length; i++) {
			for (int m = 0; m < Grid.length; m++) {
				if (Grid[i][m] == 0) {
					available.add(new Point(i,m));
				}
			}
		}

		addRandom(2);

	}

	/**
	 * This method runs the animation.
	 */
	public void run() {
		String[] sizeArgs = { "width=" + (int) WIDTH, "height=" + (int) HEIGHT };
		if (!gui.isStarted()) {
			gui.start(sizeArgs);
		}
		
	}

	/**
	 * This method adds tiles to the grid.
	 */
	private void addRandom(int n) {

		Random r = new Random();
		if (available.isEmpty()) {
			System.out.println("Game Over");
			return;
		}
		for (int i = 0; i < n; i++) {
			Point p = available.get(r.nextInt(available.size()));
			Grid[(int) p.getX()][(int) p.getY()] = r.nextInt(4) == 1 ? 4 : 2;
			Tile t = new Tile((int)( p.getX()*(SIZE + SEP)+SEP), (int) (p.getY()*(SIZE + SEP)+SEP),
					r.nextInt(4) == 1 ? 4 : 2);
			gui.add(t.tile);
			tiles.add(t);
			available.remove(p);
		}
	}

	private Color getTileColor(Tile t) {
		Color color = new Color(255, 255 - t.value / 8, 15);
		return color;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 37) // left Key
		{
			System.out.println("left");
			
		}
		else if (keyCode == 38) // up
		{
			System.out.println("up");
			for (int i = 0; i < tiles.size(); i++) {
				Tile t = tiles.get(i);
				if(t.getY()>10){
					int x=(int)(t.getX()-SEP)/(int)(SEP+SIZE);
					int y=(int)(t.getY()-SEP)/(int)(SEP+SIZE);
					if(Grid[x][y-1]==0){
						t.setY((int)(t.getY()-SIZE-SEP));
						Grid[x][y-1]=Grid[x][y];
						Grid[x][y]=0;
						addRandom(1);
					}
					else if(Grid[x][y-1]==Grid[x][y]){
						int n=tiles.indexOf(new Tile(t.getX(),t.getY()-(int)(SIZE+SEP),t.getValue()));
						Tile t1 = tiles.get(n);
						t1.augmentValue(t.getValue());
						gui.remove(t.tile);
						Grid[x][y]=0;
						addRandom(1);
					}
				}
			}
		}
		else if (keyCode == 39) // right
		{
			System.out.println("right");
			// insert method here
		}
		else if (keyCode == 40) // down
		{
			System.out.println("down");
			// insert method here
		}
		
	}

	public void keyReleased(KeyEvent e) {
	}

	protected void displayInfo(KeyEvent e, String s) {
		
	}
}
