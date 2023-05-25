package src.main.Drivers;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * - Victor
 */
public class Lesson implements Serializable, ScreenElement, Scene {
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

    public int change() {
        return 0;
    }
}
