package src.main.Drivers;

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;

/**
 * All of this class
 * - Victor
 */
public class Level implements Serializable, ScreenElement {
    private double scale;
    private String[][] ground, objects;
    private int px, py; // Player x and y position
    private String nextLevel;

    /**
     * Used for level creation for initializing a level before saving
     * - Victor
     */
    public Level(String[][] ground, String[][] objects, int px, int py, String nextLevel) {
        this.ground = ground;
        this.objects = objects;
        this.px = px;
        this.py = py;
        this.nextLevel = nextLevel;
    }

    /**
     * Loads a level from file
     * Returns null if there is an error; after the final level, a null string will
     * be passed as a parameter and as such null will be returned
     * 
     * @param fromFile the file containing the level info
     *                 - Victor
     */
    public static Level fromFile(String file) {
        Level level = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            level = (Level) in.readObject();
            in.close();
        } catch (NullPointerException e) {
            // Since this is intentional do not print traceback
        } catch (Exception e) {
            e.printStackTrace();
        }
        return level;
    }

    public Level nextLevel() {
        if (nextLevel != null)
            return Level.fromFile(nextLevel);
        return null;
    }

    public void updatePlayerPos(int x, int y) {
        px = x;
        py = y;
    }

    public String getBlock(int x, int y) {
        return objects[x][y];
    }

    public void update(Window w, Graphics g) {
        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        scale = Math.sqrt(hww * hwh) / 5;
        hww -= scale/2;
        hwh -= scale/2;
        // Render floor
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                g.drawImage(Sprite.getTile(ground[i][j]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) (hww + scale * (i - px)),
                        (int) (hwh + scale * (j - py)), null);
            }
        }
        // Render objects
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[i].length; j++) {
                if (objects[i][j] == null) continue;
                g.drawImage(Sprite.getTile(objects[i][j]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) (hww + scale * (i - px)),
                        (int) (hwh + scale * (j - py)), null);
            }
        }
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}
