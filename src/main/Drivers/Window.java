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
    private Map<Integer, Boolean> keys = new HashMap<>(); // Stores whether key is up or down
    private Map<Integer, Integer> keysPressed = new HashMap<>(); // Stores keys pressed
    private Map<Integer, Boolean> mouse = new HashMap<>(); // Stores whether mouse is up or down
    private Map<Integer, Integer> mousePressed = new HashMap<>(); // Stores mouse buttons pressed

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

    public JFrame getFrame() {
        return frame;
    }

    public void addElement(ScreenElement s) {
        elements.add(s);
    }

    public void removeElement(ScreenElement s) {
        elements.remove(s);
    }

    public void update() {
        keysPressed.clear();
        canvas.repaint();
    }

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

    public boolean keydown(int keyCode) {
        return keys.getOrDefault(keyCode, false);
    }

    public int keypressed(int keyCode) {
        return keysPressed.getOrDefault(keyCode, 0);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        keys.put(key, true);
        keysPressed.put(key, keysPressed.getOrDefault(key, 0) + 1); // increment
    }

    @Override
    public void keyReleased(KeyEvent event) {
        keys.put(event.getKeyCode(), false);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // TODO Not necessary
    }

    @Override
    public void mousePressed(MouseEvent event) {
        int mousey = event.getButton();
        mouse.put(mousey, true);
        mousePressed.put(mousey, keysPressed.getOrDefault(mousey, 0) + 1); // increment
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        mouse.put(event.getButton(), false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Not necessary
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Not necessary
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Not necessary
    }
}