package src.main.Drivers;
import java.awt.Graphics;
import java.io.*;

/**
 * - Victor
 */
public class Level implements Serializable, ScreenElement {
    protected Tile layout[][];

    /**
     * Used for level creation, doesn't do anything.
     *      - Victor
     */
    public Level() {
        ;
    }

    /**
     * Loads a level form file
     * 
     * @param fromFile the file containing the level info
     *                 - Victor
     */
    public Level(String fromFile) {
        ;
    }

    public void update(Window w, Graphics g)
    {
        ;
    }
}
