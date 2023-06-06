package src.main.Drivers;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

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

/**
 * Class created by Radin
 * 
 * Drawing class created by Victor, plus methods
 * 
 * update method, add/remove element, keyPressed/released created by Victor
 * changes to constructor by Victor
 * 
 * Class completely changed again by Victor
 */
public class Window implements KeyListener, MouseListener {
    private JFrame frame;
    private Canvas canvas;
    private ArrayList<ScreenElement> elements = new ArrayList<>();
    private boolean[] keysdown = new boolean[4]; // Stores whether key is up or down, 0-3 are up left down right
    private Queue<int[]> mouseclicks = new ArrayDeque<>(); // Stores the location of mouse clicks, only left click

    public Window(String name, int width, int height) {
        frame = new JFrame(name);
        frame.setSize(width, height);
        canvas = new Canvas(this);
        frame.add(canvas);
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    /*
     * public JFrame getFrame() {
     * return frame;
     * }
     */

    public void addElement(ScreenElement s) {
        elements.add(s);
    }

    public void removeElement(ScreenElement s) {
        elements.remove(s);
    }

    public void update() {
        canvas.repaint();
    }

    /*
     * public void clearInput() {
     * keysPressed.clear();
     * mousePressed.clear();
     * }
     */

    void paint(Graphics g) {
        for (ScreenElement s : elements) {
            s.update(this, g);
        }
    }

    public void tick(int fps) {
        try {
            Thread.sleep(1000 / fps);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Dummy class to receive Graphics object for drawing
     * Immediately passes it back to window class for use
     * - Victor
     */
    class Canvas extends JComponent {
        Window parent;

        public Canvas(Window w) {
            parent = w;
        }

        public void paint(Graphics g) {
            parent.paint(g);
        }
    }

    // Accessor methods
    public boolean keyDown(int key) {
        return keysdown[key];
    }

    public int[] nextMouse() {
        if (mouseclicks.isEmpty()) return null;
        return mouseclicks.remove();
    }

    // Only useful methods
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                keysdown[0] = true;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                keysdown[1] = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                keysdown[2] = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                keysdown[3] = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                keysdown[0] = false;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                keysdown[1] = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                keysdown[2] = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                keysdown[3] = false;
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1) return;
        mouseclicks.add(new int[]{e.getX(), e.getY()});
    }

    // Junk methods
    @Override
    public void mousePressed(MouseEvent event) {
        // Not necessary
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Not necessary
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not necessary
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not necessary
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not necessary
    }
}