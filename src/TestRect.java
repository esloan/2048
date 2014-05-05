 

import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestRect extends GraphicsProgram {
    private static RandomGenerator rand = new RandomGenerator();

        public void run() {
            final GRect up = new GRect(10, 10, 100, 100);
            up.setFilled(true);
            up.setColor(Color.RED);
            add(up);

            up.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    up.setColor(rand.nextColor());
                }
            });
       
            final GRect down = new GRect(10, 10, 100, 150);
            down.setFilled(true);
            down.setColor(Color.BLUE);
            add(down);

            down.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    down.setColor(rand.nextColor());
                }
            });
            final GRect left = new GRect(10, 10, 75, 125);
            left.setFilled(true);
            left.setColor(Color.BLUE);
            add(left);
          
            left.addMouseListener(new MouseAdapter() {
             @Override
                        public void mouseClicked(MouseEvent arg0) {
                            left.setColor(rand.nextColor());     
                        }
             });
                 final GRect right = new GRect(10, 10, 125, 150);
                 right.setFilled(true);
                 right.setColor(Color.BLUE);
                 add(right);

                 right.addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseClicked(MouseEvent arg0) {
                   right.setColor(rand.nextColor());  
                   
                   
                 }
              });
                        }
     }
                
                 
        
    
