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
	private Tile[][] Grid = new Tile[ROWS][COLUMNS];
	ArrayList<Point> available = new ArrayList<Point>();
	GRect[][] grid = new GRect[ROWS][COLUMNS];

	public void init() {
		// setBackground(Color.blue);//sets background color

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				grid[i][j] = new GRoundRect(SIZE, SIZE);
				grid[i][j].setFilled(true);
				grid[i][j].setFillColor(Color.GRAY);
				//add(grid[i][j], SEP + (SIZE + SEP) * i, SEP + (SIZE + SEP)* j);
				Grid[i][j]=null;
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

		removeAll();
		for(int i=0;i<Grid.length;i++){
			for(int j=0;j<Grid[0].length;j++){
				if(Grid[i][j]!=null){
					add(Grid[i][j].tile);
					add(Grid[i][j].l);
					System.out.print((Grid[i][j].getValue())+" ");}
				else{System.out.print("0 ");}
				add(grid[i][j], SEP + (SIZE + SEP) * i, SEP + (SIZE + SEP)* j);
				grid[i][j].sendToBack();
			}
			System.out.println();
		}
		System.out.println();

	}

	/**
	 * This method adds tiles to the grid.
	 */
	private void addRandom(int n) {

		Random r = new Random();
		for (int i = 0; i < n; i++) {
			boolean t=true;
			while(t){
				Point p = available.get(r.nextInt(available.size()));
				if(Grid[(int) p.getY()][(int) p.getX()]==null){
					t=false;
					Grid[(int) p.getY()][(int) p.getX()] = new Tile((int) (p.getX()*(SIZE+SEP)+SEP), (int) (p.getY()*(SIZE + SEP)+SEP),r.nextInt(4)==1?4:2);
					//			add(Grid[(int) p.getY()][(int) p.getX()].tile);
					//			add(Grid[(int) p.getY()][(int) p.getX()].l);
				}
			}
			System.out.println("new tile");
		}


	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		displayInfo(e,"KEYPRESSED:");
	}

	public void up(){
		boolean changed = false;

		for(int i = 1; i < ROWS; i++ ) // i is rows
		{
			for(int j = 0; j < COLUMNS; j++)
			{
				if(Grid[i][j]!=null){				//Checks to see if there is a tile there
					if(Grid [i-1][j]==null )		//Checks to see if the space above is empty
					{
						Grid[i-1][j] = Grid [i][j]; // "Moves" the idea of a tile in the grid
						//						remove(Grid[i][j].tile);	// removes old tile from screen
						//						remove(Grid[i][j].l);		// removes old label from screen
						Grid [i][j] = null;			// removes old tile from grid
						Grid[i-1][j].setY((int) (Grid[i-1][j].getY()-SEP-SIZE));			//  Sets new y value
						//						add(Grid[i-1][j].tile,Grid[i-1][j].getX(),Grid[i-1][j].getY());		//  adds new tile to grid
						//						add(Grid[i-1][j].l,Grid[i-1][j].getX()+40,Grid[i-1][j].getY()+40);	//  adds new label to grid
						changed = true;														//  Allows for new tile to be added
					}
					else if(Grid [i][j].getValue() == Grid [i-1][j].getValue())				// Checks to see if it can merge
					{
						//						remove(Grid[i][j].tile);		//removes old tile
						//						remove(Grid[i][j].l);			// removes old label 
						Grid[i][j] = null;				//remves old tile from grid
						Grid[i-1][j].mult();			//Changes value + Color
						//						remove(Grid[i-1][j].tile);	
						//						add(Grid[i-1][j].tile);
						//
						//						remove(Grid[i-1][j].l);
						//						add(Grid[i-1][j].l,Grid[i-1][j].getX()+40,Grid[i-1][j].getY()+40);

						changed = true;					// alows new tile to be added
					}
				}
			}
		}

		if(changed){addRandom(1);}	// adds new tile

	}

	public void down(){
		boolean changed = false;

		for(int i = ROWS-2; i >= 0  ; i-- ) // i is rows
		{
			for(int j = 0; j < COLUMNS; j++)
			{
				if(Grid[i][j]!=null){
					if(Grid [i+1][j]==null )
					{
						Grid[i+1][j] = Grid [i][j];
						//						remove(Grid[i][j].tile);
						//						remove(Grid[i][j].l);
						Grid [i][j] = null;

						Grid[i+1][j].setY((int)(Grid[i+1][j].getY()+SIZE+SEP));
						//						add(Grid[i+1][j].tile,Grid[i+1][j].getX(),Grid[i+1][j].getY());
						//						add(Grid[i+1][j].l,Grid[i+1][j].getX()+40,Grid[i+1][j].getY()+40);	
						changed = true;
					}
					else if(Grid [i][j].getValue() == Grid [i+1][j].getValue())
					{
						//						remove(Grid[i][j].tile);
						//						remove(Grid[i][j].l);

						Grid[i][j] = null;
						Grid[i+1][j].mult();
						//						remove(Grid[i+1][j].tile);
						//						add(Grid[i+1][j].tile);
						//
						//						remove(Grid[i+1][j].l);
						//						add(Grid[i+1][j].l,Grid[i+1][j].getX()+40,Grid[i+1][j].getY()+40);	

						changed = true;
					}
				}
			}
		}

		if(changed){addRandom(1);}
	}

	public void left(){
		boolean changed = false;

		for(int j = 1; j < COLUMNS; j++ ) // J = columns
		{
			for(int i = 0; i < ROWS; i++)
			{
				if(Grid[i][j]!=null){
					if(Grid [i][j-1]==null )
					{
						Grid[i][j-1] = Grid [i][j];
						//						remove(Grid[i][j].tile);
						//						remove(Grid[i][j].l);
						Grid [i][j] = null;
						Grid[i][j-1].setX((int)(Grid[i][j-1].getX()-SIZE-SEP));
						//						add(Grid[i][j-1].tile,Grid[i][j-1].getX(),Grid[i][j-1].getY());
						//						add(Grid[i][j-1].l,Grid[i][j-1].getX()+40,Grid[i][j-1].getY()+40);	
						changed = true;
					}
					else if(Grid [i][j].getValue() == Grid [i][j-1].getValue())
					{
						//						remove(Grid[i][j].tile);
						//						remove(Grid[i][j].l);

						Grid[i][j] = null;
						Grid[i][j-1].mult();
						//						remove(Grid[i][j-1].tile);
						//						add(Grid[i][j-1].tile);
						//
						//						remove(Grid[i][j-1].l);
						//						add(Grid[i][j-1].l,Grid[i][j-1].getX()+40,Grid[i][j-1].getY()+40);	

						changed = true;
					}
				}
			}
		}

		if(changed){addRandom(1);}

	}

	public void right(){
		boolean changed = false;

		for(int j = COLUMNS-2; j >=0 ; j-- ) // J = columns
		{
			for(int i = 0; i < ROWS; i++)
			{
				if(Grid[i][j]!=null){
					if(Grid [i][j+1]==null )
					{
						Grid[i][j+1] = Grid [i][j];
						Grid [i][j] = null;
						Grid[i][j+1].setX((int)(Grid[i][j+1].getX()+SIZE+SEP));
						changed = true;
					}
					else if(Grid [i][j].getValue() == Grid [i][j+1].getValue())
					{

						Grid[i][j] = null;
						Grid[i][j+1].mult();
						
						changed = true;
					}
				}

			}
		}
		if(changed){addRandom(1);}
	}



	public void keyReleased(KeyEvent e) {
	}

	protected void displayInfo(KeyEvent e, String s) {
		int keyCode = e.getKeyCode();
		if (keyCode == 37) // left Key
		{
			System.out.println("left");
			left();
			System.out.println("panic!");

		}
		else if (keyCode == 38) // up
		{
			System.out.println("up");
			up();
			System.out.println("panic!");

		}
		else if (keyCode == 39) // right
		{
			System.out.println("right");
			right();
			System.out.println("panic!");
		}
		else if (keyCode == 40) // down
		{
			System.out.println("down");
			down();
			System.out.println("panic!");
		}
		removeAll();
		for(int i=0;i<Grid.length;i++){
			for(int j=0;j<Grid[0].length;j++){
				if(Grid[i][j]!=null){
					Grid[i][j].tile.sendToFront();
					add(Grid[i][j].tile,Grid[i][j].getX(),Grid[i][j].getY());
					add(Grid[i][j].l,Grid[i][j].getX()+25-10*(int)(Math.log10(Grid[i][j].value)),Grid[i][j].getY()+50);
					Grid[i][j].l.sendToFront();
					System.out.print((Grid[i][j].getValue())+" ");}
				else{System.out.print("0 ");}
				add(grid[i][j], SEP + (SIZE + SEP) * i, SEP + (SIZE + SEP)* j);
				grid[i][j].sendToBack();
			}
			System.out.println();
		}
		System.out.println();

		//		for(int i=0;i<Grid.length;i++){
		//			for(int j=0;j<Grid[0].length;j++){
		//				if(Grid[i][j]!=null){System.out.print(Grid[i][j].getValue()+" ");}
		//				else{System.out.print("0 ");}
		//			}
		//			System.out.println();
		//		}
		//		System.out.println();

	}
}
