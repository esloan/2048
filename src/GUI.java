import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class GUI extends GraphicsProgram {
    private static final int ROWS = 4; //number of rows in the background
    private static final int COLUMNS = 4; //number of columns in the background
    private static final double SIZE = 80; //size of each brick (pixels, width and height since they're squares)
    private static final int SEP = 10; //separator size (pixels)
    private static final double WIDTH = COLUMNS*(SIZE+SEP)+SEP;
    private static final double HEIGHT = ROWS*(SIZE+SEP)+SEP;
    static GUI gui = new GUI();

    public void init()
    {
        //setBackground(Color.blue);//sets background color

        GRect[][] grid = new GRect[ROWS][COLUMNS];
        for(int i=0; i<ROWS;i++){
            for(int j=0;j<COLUMNS;j++){
                grid[i][j]=new GRoundRect(SIZE,SIZE);
                grid[i][j].setFilled(true);
                grid[i][j].setFillColor(Color.MAGENTA);
                gui.add(grid[i][j],SEP+(SIZE+SEP)*i,SEP+(SIZE+SEP)*j);
            }
        }
    }
    /**
       * This method runs the animation.
         */
        public void run()
        {
            String[] sizeArgs = { "width=" + (int)WIDTH, "height=" + (int)HEIGHT};
            if(!gui.isStarted()){
                gui.start(sizeArgs);
            }
        }


        
    
    
    private Color getTileColor(Tiles t) {
        Color color = new Color(255, 255 - t.value/8, 15);
        return color;
    }

}

    

