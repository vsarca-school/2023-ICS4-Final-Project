package src.main.Drivers;

import java.awt.Graphics;
import java.io.*;

/**
 * All of this class
 * - Victor
 */
public class Level implements Serializable, ScreenElement {
    private double scale;
    private String[][] ground, objects;
    private int px, py; // Player x and y position

    /**
     * Used for level creation for initializing a level before saving
     * - Victor
     */
    public Level(String[][] ground, String[][] objects, int px, int py) {
        this.ground = ground;
        this.objects = objects;
        this.px = px;
        this.py = py;
    }

    /**
     * Loads a level from file
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return level;
    }

    public void updatePlayerPos(int x, int y) {
        px = x;
        py = y;
    }

    public String getBlock(int x, int y)
    {
        return objects[x][y];
    }

    public void update(Window w, Graphics g) {
        // Render level
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        scale = Math.sqrt(w.getWidth() * w.getHeight());
        // Render floor
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                g.drawImage(Sprite.getTile(ground[i][j]), (int) (hww + scale * (i - px)),
                        (int) (hwh + scale * (j - py)), null);
            }
        }
        // Render objects
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[i].length; j++) {
                g.drawImage(Sprite.getTile(objects[i][j]), (int) (hww + scale * (i - px)),
                        (int) (hwh + scale * (j - py)), null);
            }
        }
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w)
    {
        w.removeElement(this);
    }
}
