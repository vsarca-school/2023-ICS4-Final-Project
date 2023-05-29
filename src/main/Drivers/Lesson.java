package src.main.Drivers;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * - Victor
 */
public class Lesson implements Serializable, ScreenElement, Scene {
    /**
     * Used for lesson creation, doesn't do anything.
     * - Victor
     */
    public ArrayList<String> text = new ArrayList<>();

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
    
    public void addToWindow(Window w);
    public void removeFromWindow(Window w);

    public int change() {
        return 0;
    }
}
