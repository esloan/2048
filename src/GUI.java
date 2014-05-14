import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.util.Random;
import java.awt.event.*;

public class GUI extends GraphicsProgram implements KeyListener{
	private static final int ROWS = 4; // number of rows in the background
	private static final int COLUMNS = 4; // number of columns in the background
	private static final double SIZE = 80; // size of each brick (pixels, width
	// and height since they're squares)
	private static final int SEP = 10; // separator size (pixels)
	private static final double WIDTH = COLUMNS * (SIZE + SEP) + SEP;
	private static final double HEIGHT = ROWS * (SIZE + SEP) + SEP;
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
				add(grid[i][j], SEP + (SIZE + SEP) * i, SEP + (SIZE + SEP)* j);
			}
		}

		addKeyListeners();

		for (int i = 0; i < Grid.length; i++) {
			for (int m = 0; m < Grid.length; m++) {
				available.add(new Point(i,m));
			}
		}

		addRandom(2);
	}

	/**
	 * This method runs the animation.
	 */
	public void run() {
		String[] sizeArgs = { "width=" + (int) WIDTH, "height=" + (int) HEIGHT };
		if(!isStarted()){
			start(sizeArgs);}

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
			add(t.tile);
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
		displayInfo(e,"KEYPRESSED:");
	}

	public void up(){
		boolean changed = false;
		//add some way to keep track of and remove the invisible tiles
		Tile[] toRemove = new Tile[tiles.size()];
		int n = tiles.size();
		for (int i = 0; i < n; i++) {
			Tile t = tiles.get(i);
			//System.out.println("Tile at "+t.getX()+","+t.getY());
			if(t.getY()>10){
				int x=(int)(t.getX()-SEP)/(int)(SEP+SIZE);
				int y=(int)(t.getY()-SEP)/(int)(SEP+SIZE);
				if(Grid[x][y-1]==0){
					remove(t.tile);
					t.setY((int)(t.getY()-SIZE-SEP));
					add(t.tile,t.getX(),t.getY());
					Grid[x][y-1]=Grid[x][y];
					Grid[x][y]=0;
					changed=true;
					y--;
				}
				else if(Grid[x][y-1]==Grid[x][y]){
					Tile t1 = null;
					for(Tile t2:tiles){
						if(t2.getX()==t.getX()&&t2.getY()==t.getY()){
							t1=t2;
						}
					}
					t1.augmentValue(t.getValue());
					System.out.println("Combined tile at "+t.getX()+","+t.getY());
					remove(t.tile);
					toRemove[i]=tiles.get(i);
					Grid[x][y]=0;
					Grid[x][y-1]*=2;
					changed=true;
					y--;
				}
			}
		}
		for(int m=0;m<toRemove.length;m++){
			if(toRemove[m]!=null){tiles.remove(toRemove[m]);}
		}
		if(changed){addRandom(1);}
	}
	public void down(){
		boolean changed = false;
		Tile[] toRemove = new Tile[tiles.size()];
		int n = tiles.size();
		for (int i = 0; i < n; i++) {
			Tile t = tiles.get(i);
			if(t.getY()<HEIGHT-SIZE-2*SEP){
				int x=(int)(t.getX()-SEP)/(int)(SEP+SIZE);
				int y=(int)(t.getY()-SEP)/(int)(SEP+SIZE);
				if(Grid[x][y+1]==0){
					remove(t.tile);
					t.setY((int)(t.getY()+SIZE+SEP));
					add(t.tile,t.getX(),t.getY());
					Grid[x][y+1]=Grid[x][y];
					Grid[x][y]=0;
					changed=true;
				}
				else if(Grid[x][y+1]==Grid[x][y]){
					Tile t1 = null;
					for(Tile t2:tiles){
						if(t2.getX()==t.getX()&&t2.getY()==t.getY()){
							t1=t2;
						}
					}
					t1.augmentValue(t.getValue());
					System.out.println("Combined tile at "+t.getX()+","+t.getY());
					remove(t.tile);
					toRemove[i]=tiles.get(i);
					Grid[x][y]=0;
					Grid[x][y+1]*=2;
					changed=true;
				}
			}
		}
		for(int m=0;m<toRemove.length;m++){
			if(toRemove[m]!=null){tiles.remove(toRemove[m]);}
		}
		if(changed){addRandom(1);}
	}

	public void left(){
		boolean changed = false;
		Tile[] toRemove = new Tile[tiles.size()];
		int n = tiles.size();
		for (int i = 0; i < n; i++) {
			Tile t = tiles.get(i);
			if(t.getX()>SEP){
				int x=(int)(t.getX()-SEP)/(int)(SEP+SIZE);
				int y=(int)(t.getY()-SEP)/(int)(SEP+SIZE);
				if(Grid[x-1][y]==0){
					remove(t.tile);
					t.setX((int)(t.getX()-SIZE-SEP));
					add(t.tile,t.getX(),t.getY());
					Grid[x-1][y]=Grid[x][y];
					Grid[x][y]=0;
					changed=true;
				}
				else if(Grid[x-1][y]==Grid[x][y]){
					Tile t1 = null;
					for(Tile t2:tiles){
						if(t2.getX()==t.getX()&&t2.getY()==t.getY()){
							t1=t2;
						}
					}
					t1.augmentValue(t.getValue());
					System.out.println("Combined tile at "+t.getX()+","+t.getY());
					remove(t.tile);
					toRemove[i]=tiles.get(i);
					Grid[x][y]=0;
					Grid[x-1][y]*=2;
					changed=true;
				}
			}
		}
		for(int m=0;m<toRemove.length;m++){
			if(toRemove[m]!=null){tiles.remove(toRemove[m]);}
		}
		if(changed){addRandom(1);}
	}

	public void right(){
		boolean changed = false;
		Tile[] toRemove = new Tile[tiles.size()];

		int n = tiles.size();
		for (int i = 0; i < n; i++) {
			Tile t = tiles.get(i);
			if(t.getX()<WIDTH-SIZE-2*SEP){
				int x=(int)(t.getX()-SEP)/(int)(SEP+SIZE);
				int y=(int)(t.getY()-SEP)/(int)(SEP+SIZE);
				if(Grid[x+1][y]==0){
					remove(t.tile);
					t.setX((int)(t.getX()+SIZE+SEP));
					add(t.tile,t.getX(),t.getY());
					Grid[x+1][y]=Grid[x][y];
					Grid[x][y]=0;
					changed=true;
				}
				else if(Grid[x+1][y]==Grid[x][y]){
					Tile t1 = null;
					for(Tile t2:tiles){
						if(t2.getX()==t.getX()&&t2.getY()==t.getY()){
							t1=t2;
						}
					}
					t1.augmentValue(t.getValue());
					System.out.println("Combined tile at "+t.getX()+","+t.getY());
					remove(t.tile);
					toRemove[i]=tiles.get(i);
					Grid[x][y]=0;
					Grid[x+1][y]*=2;
					changed=true;
				}
			}
		}
		for(int m=0;m<toRemove.length;m++){
			if(toRemove[m]!=null){tiles.remove(toRemove[m]);}
		}
		if(changed){addRandom(1);}
	}



	public void keyReleased(KeyEvent e) {
	}

	protected void displayInfo(KeyEvent e, String s) {
		int keyCode = e.getKeyCode();
		if (keyCode == 37) // left Key
		{
			//System.out.println("left");
			left();

		}
		else if (keyCode == 38) // up
		{
			//System.out.println("up");
			up();

		}
		else if (keyCode == 39) // right
		{
			//System.out.println("right");
			right();
		}
		else if (keyCode == 40) // down
		{
			//System.out.println("down");
			down();
		}


	}
}
