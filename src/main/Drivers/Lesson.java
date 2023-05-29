package src.main.Drivers;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * All of this classs
 * - Victor
 */
public class Lesson implements Serializable, ScreenElement, Scene {
    public ArrayList<String> text = new ArrayList<>();

    /**
     * Used for lesson creation, doesn't do anything.
     * - Victor
     */
    public Lesson() {
        ;
    }

    /**
     * Loads a lesson from file
     * 
     * @param fromFile the file containing the level info
     *                 - Victor
     */
    public Lesson(String fromFile) {
        ;
    }

    public void update(Window w, Graphics g) {
        ;
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }

    public int change() {
        return 0;
    }
}
