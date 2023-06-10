package src.main.Drivers;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Queue;

import javax.swing.*;

import src.main.Main;

/**
 * <h1>Window Class</h1>
 * Time spent: 5 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Victor Sarca/Radin Ahari/Felix Zhao (code)/Radin Ahari(comments)
 */
public class Window implements KeyListener, MouseListener {
    /** Jframe */
    private JFrame frame;
    /** Canvas being drawn on */
    private Canvas canvas;
    /** all ScreenElements */
    private ArrayList<ScreenElement> elements = new ArrayList<>();
    /** Stores whether key is up or down, 0-3 are up left down right */
    private boolean[] keysdown = new boolean[4];
    /** Stores the location of mouse clicks, only left click */
    private Queue<int[]> mouseclicks = new ArrayDeque<>();
    /** width and height of canvas */
    private int width, height;
    /** whether window has updated */
    private boolean hasUpdated = true;
    /** whether escape is pressed or not */
    private boolean escaped = false;

    /**
     * Window constructor
     * 
     * @param name   window name
     * @param width  window width
     * @param height window height
     */
    public Window(String name, int width, int height) {
        frame = new JFrame(name);
        frame.setSize(width, height);
        canvas = new Canvas(this);
        frame.add(canvas);
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentListener(new WindowResizeListener(this));
    }

    /**
     * Changes hasUpdated
     */
    void hasUpdated() {
        hasUpdated = true;
    }

    /**
     * 
     * @return returns width
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @return returns height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Adds an element
     * 
     * @param s ScreenElement object being added
     */
    public void addElement(ScreenElement s) {
        if (s.isPaused())
            s.pause(); // Make sure it isn't pause
        elements.add(s);
    }

    /**
     * Removes an element
     * 
     * @param s ScreenElement object being removed
     */
    public void removeElement(ScreenElement s) {
        elements.remove(s);
    }

    /**
     * Pauses all elements
     */
    public void pauseAll() {
        try {
            for (ScreenElement s : elements) {
                s.pause();
            }
        } catch (ConcurrentModificationException e) {
            // Expected when scene is changed; do nothing, should work out anyways
        }
    }

    /**
     * Updates the canvas
     */
    public void update() {
        if (hasUpdated) {
            hasUpdated = false;
            width = frame.getWidth() - 16;
            height = frame.getHeight() - 39;
            Sprite.updateScale(width, height);
        }
        canvas.repaint();
    }

    /**
     * Closes the window
     */
    public void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Paints the the screen
     * 
     * @param g graphics
     */
    void paint(Graphics g) {
        try {
            for (int i = 0; i < elements.size(); i++) {
                elements.get(i).update(this, g);
            }
        } catch (ConcurrentModificationException e) {
            // Expected when scene is changed; do nothing, should work out anyways
        }
    }

    /**
     * makes the game run at a given fps
     * 
     * @param fps the fps the game runs at
     */
    public void tick(int fps) {
        try {
            Thread.sleep(1000 / fps);
        } catch (InterruptedException e) {
        }
    }

    class Canvas extends JComponent {
        Window parent;

        /**
         * sets window to canvas
         * 
         * @param w window
         */
        public Canvas(Window w) {
            parent = w;
        }

        /**
         * Paints to canvas
         * 
         * @param g graphics
         */
        public void paint(Graphics g) {
            parent.paint(g);
        }
    }

    private class WindowResizeListener implements ComponentListener {
        Window w;

        /**
         * not used
         * 
         * @param w window
         */
        public WindowResizeListener(Window w) {
            this.w = w;
        }

        /**
         * not used
         * 
         * @param arg0 window event
         */
        public void componentHidden(ComponentEvent arg0) {
        }

        /**
         * not used
         * 
         * @param arg0 window event
         */
        public void componentMoved(ComponentEvent arg0) {
        }

        /**
         * not used
         * 
         * @param arg0 window event
         */
        public void componentResized(ComponentEvent arg0) {
            w.hasUpdated();
        }

        /**
         * not used
         * 
         * @param arg0 window event
         */
        public void componentShown(ComponentEvent arg0) {

        }
    }

    /**
     * Returns whether the key has been pressed
     * 
     * @param key key
     * @return retuns whether the key has been pressed
     */
    public boolean keyDown(int key) {
        return keysdown[key];
    }

    /**
     * returns list of mouse clicks
     * 
     * @return list of mouse clicks
     */
    public int[] nextMouse() {
        if (mouseclicks.isEmpty())
            return null;
        return mouseclicks.remove();
    }

    @Override
    /**
     * checks if a key is pressed
     * 
     * @param event the KeyEvent
     */
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
            case KeyEvent.VK_ESCAPE:
                if (!escaped) {
                    escaped = true;
                    Main.pause();
                }
                break;
        }
    }

    @Override
    /**
     * Checks if a key is released
     * 
     * @param e the event
     */
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
            case KeyEvent.VK_R:
                Main.restartMaze();
                break;
            case KeyEvent.VK_ESCAPE:
                escaped = false;
                break;
        }
    }

    @Override
    /**
     * checks if mouse is clicked
     * 
     * @param e the event
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1)
            return;
        mouseclicks.add(new int[] { e.getX() - 8, e.getY() - 31 });
    }

    @Override
    /**
     * not used
     */
    public void mousePressed(MouseEvent event) {
    }

    /**
     * not used
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        // Not necessary
    }

    /**
     * not used
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // Not necessary
    }

    /**
     * not used
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // Not necessary
    }

    /**
     * not used
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not necessary
    }
}