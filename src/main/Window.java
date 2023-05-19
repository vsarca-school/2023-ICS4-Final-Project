package src.main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * An object of this class represents a window.
 * 
 * Constructor - receives parameters specifying window dimensions, etc.
 * 
 * The JComponent class has a paint method - this does NOT update in real time,
 * only when you move the window. (I think). Create a test setup and make sure
 * you can RUN IT AT 60 FPS. Also, the drawing will be outsourced to the game
 * class.
 * 
 * WHAT DO WE WANT:
 * We want to have a canvas that we can update at will, 60 times per second. We
 * can draw to the canvas as much as we want, and it will update the screen when
 * we tell it to, and will clear the screen when we want it to.
 * WE DO NOT WANT A PAINT METHOD. We don't want to draw all the graphics when
 * Java wants us to, we want to draw it when its convenient for us. That is how
 * most renderers work, including the default one from the OS so it must be
 * possible in Java.
 *          - Victor
 */ 
public class Window extends KeyAdapter{
    Drawing draw = new Drawing();
    int x = 0;
    int y = 0;
    public Window() {
        javax.swing.JFrame frame = new javax.swing.JFrame("Letter");
        frame.setSize(400, 200);
        frame.add(draw);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        time();
    }

    public void time(){
        while(true) {
            draw.repaint ();
            try {
                 Thread.sleep(1000/60);
            } catch (InterruptedException e) {
            }
        }
    }

    public void move(KeyEvent e){
        int key = e.getKeyChar();
        if(key == KeyEvent.VK_W){
            y--;
        }
        else if(key == KeyEvent.VK_S){
            y++;
        }
        else if(key == KeyEvent.VK_A){
            x--;
        }
        else if(key == KeyEvent.VK_D){
            x++;
        }

    }

    class Drawing extends javax.swing.JComponent {
        public void paint(Graphics g) {
            g.fillRect(x, y, 30, 30);
            move();
        }
    }
}
