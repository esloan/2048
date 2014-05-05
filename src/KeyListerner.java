
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class KeyListerner extends Applet implements KeyListener {
    TextArea displayArea;
    TextField typingArea;
    public void init() {
        typingArea = new TextField(20);
        typingArea.addKeyListener(this);

        setLayout(new BorderLayout());
        add("North", typingArea);
    }
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {displayInfo(e, "KEY PRESSED: ");}

    public void keyReleased(KeyEvent e) {}
    
    protected void displayInfo(KeyEvent e, String s){    
        int keyCode = e.getKeyCode();
        if (keyCode == 37) //left Key
        {
            //insert method here
        }
        else if (keyCode == 38) //up
        {
            //insert method here
        }
        else if (keyCode == 39) //right
        {
            //insert method here
        }
        else if (keyCode ==40) //down
        {
            //insert method here
        }
    }
}
