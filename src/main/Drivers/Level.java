package src.main.Drivers;
import java.awt.Graphics;
import java.io.*;

/**
 * - Victor
 */
public class Level implements Serializable, ScreenElement {
    double scale;
    public String[][] ground, objects;
    public int px, py; // Player x and y position

    /**
     * Used for level creation, doesn't do anything.
     *      - Victor
     */
    public Level() {
        ;
    }

    /**
     * Loads a level from file
     * 
     * @param fromFile the file containing the level info
     *                 - Victor
     */
    public Level(String fromFile) {
        ;
    }

    public void updatePlayerPos(int x, int y)
    {
        px = x;
        py = y;
    }

    public void update(Window w, Graphics g)
    {
        // Render level
        double hww = w.getWidth()/2.0;
        double hwh = w.getHeight()/2.0;
        scale = Math.sqrt(w.getWidth()*w.getHeight());
        for (int i=0; i<ground.length; i++)
        {
            for (int j=0; j<ground[i].length; j++)
            {
                g.drawImage(Tile.getSprite(ground[i][j]), (int)(hww + scale*(i-px)), (int)(hwh + scale*(j-py)), null);
            }
        }
        for (int i=0; i<objects.length; i++)
        {
            for (int j=0; j<objects[i].length; j++)
            {
                g.drawImage(Tile.getSprite(objects[i][j]), (int)(hww + scale*(i-px)), (int)(hwh + scale*(j-py)), null);
            }
        }
    }
}
