package src.main;
import java.io.*;

/**
 * Future plans: level generation?
 * - Victor
 */
public class Level implements Serializable {
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
}
