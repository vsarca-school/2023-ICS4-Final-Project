package src.main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
 * - Victor
 */
public class Window {
    Drawing draw;
    int x = 0;

    public Window() {
        javax.swing.JFrame frame = new javax.swing.JFrame("Letter");
        frame.setSize(400, 200);
        draw = new Drawing();
        frame.add(draw);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tim();
    }

    public void addElement(ScreenElement s) {
        draw.addElement(s);
    }

    public void removeElement(ScreenElement s) {
        draw.removeElement(s);
    }

    public void tim() {
        while (true) {
            draw.repaint();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
            }
        }
    }

    class Drawing extends JComponent {
        private ArrayList<ScreenElement> elements = new ArrayList<>();

        public void addElement(ScreenElement s) {
            elements.add(s);
        }

        public void removeElement(ScreenElement s) {
            elements.remove(s);
        }

        public void paint(Graphics g) {
            for (ScreenElement s : elements) {
                s.paint(g);
            }
        }
    }
}