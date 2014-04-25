import java.awt.Color;
import java.util.Random;

import acm.program.*;
import acm.graphics.*;

public class Blocks extends GraphicsProgram{
	private static final int ROWS = 4; //number of rows in the background
	private static final int COLUMNS = 4; //number of columns in the background
	private static final double SIZE = 80; //size of each brick (pixels, width and height since they're squares)
	private static final int SEP = 10; //separator size (pixels)
	private static final double WIDTH = COLUMNS*(SIZE+SEP)+SEP;
	private static final double HEIGHT = ROWS*(SIZE+SEP)+SEP;

	public GRect b;
	public GLabel l;
	private Random r = new Random();
	double xPos = 10;
	double yPos = 10;

	public Blocks(){
		b = new GRoundRect(SIZE,SIZE);
		xPos = SEP+(SIZE+SEP)*r.nextInt(4);
		yPos = SEP+(SIZE+SEP)*r.nextInt(4);
		GObject n = getElementAt(xPos,yPos);

		while(n!=null){
			xPos = SEP+(SIZE+SEP)*r.nextInt(4);
			yPos = SEP+(SIZE+SEP)*r.nextInt(4);
		}
		b.setColor(Color.YELLOW);
		b.setFilled(true);
		b.setLocation(xPos,yPos);
		b.sendToFront();
		l=new GLabel("2",xPos+SIZE/2,yPos+SIZE/2);	
		l.sendToFront();
	}
	public Blocks(double x, double y){
		xPos = x;
		yPos = y;
		b = new GRoundRect(SIZE,SIZE,x,y);
		b.setVisible(true);
		while(getElementAt(xPos,yPos)!=null){
			xPos = SEP+(SIZE+SEP)*r.nextInt(4);
			yPos = SEP+(SIZE+SEP)*r.nextInt(4);
		}
		b.setColor(Color.YELLOW);
		b.setFilled(true);
		b.sendToFront();
		l=new GLabel("2",x+SIZE/2,y+SIZE/2);
		l.sendToFront();
	}

	public int getX(){
		return (int) xPos;
	}

	public int getY(){
		return (int) yPos;
	}
	
	public int getLabel(){
		return Integer.parseInt(l.getLabel());
	}

	public void up(){
		double x = xPos-SIZE-SEP;
		double y = yPos-SIZE-SEP;
		if(x>0&&x<WIDTH&&y>0&&y<HEIGHT){
			if(getElementAt(x,y)==null){
				b.move(-SIZE,-SEP);
				l.move(-SIZE,-SEP);
			}
			else{
				if(getElementAt(x,y).getColor()==b.getColor()){
					getElementAt(x,y).setVisible(false);
					l.setLabel(String.valueOf(getLabel()*2));
				}
			}
		}
	}
}
