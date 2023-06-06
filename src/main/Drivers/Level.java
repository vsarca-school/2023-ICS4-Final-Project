package src.main.Drivers;

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;

/**
 * All of this class
 * - Victor
 */
public class Level implements Serializable, ScreenElement {
    private static final int[] perm = { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36,
            103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219,
            203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71,
            134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46,
            245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196,
            135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38,
            147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213,
            119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110,
            79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179,
            162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115,
            121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215,
            61, 156, 180 };

    private String[][] ground, objects;
    private double px, py; // Player x and y position
    private int startx, starty;
    private String nextLevel;

    /**
     * Used for level creation for initializing a level before saving
     * - Victor
     */
    public Level(String[][] ground, String[][] objects, int px, int py, String nextLevel) {
        this.ground = ground;
        this.objects = objects;
        this.startx = px;
        this.starty = py;
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

    public void updatePlayerPos(double x, double y) {
        px = x;
        py = y;
    }

    private String getFloor(int x, int y) {
        if (x < 0 || x >= objects.length || y < 0 || y >= objects.length)
            return "wall-" + (Math.abs(x + y) % 4);
        return ground[x][y];
    }

    public String getBlock(int x, int y) {
        if (x < 0 || x >= objects.length || y < 0 || y >= objects.length)
            return "wall-" + (Math.abs(x + y) % 4);
        return objects[x][y];
    }

    public int getStartX()
    {
        return startx;
    }

    public int getStartY()
    {
        return starty;
    }

    public void update(Window w, Graphics g) {
        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Math.sqrt(hww * hwh) / 5;
        double hwwt = hww / scale;
        double hwht = hwh / scale;
        hww -= scale / 2;
        hwh -= scale / 2;

        int start_i, start_j, max_x, max_y;
        double new_x, new_y, start_x, start_y;

        max_x = (int) (px + hwwt + 1);
        max_y = (int) (py + hwht + 1);
        start_i = (int) (px - hwwt - 1);
        start_j = (int) (py - hwht - 1);

        start_x = hww + (start_i - px) * scale;
        start_y = hwh + (start_j - py) * scale;

        new_x = start_x;
        for (int i = start_i; i <= max_x; i++, new_x += scale) {
            new_y = start_y;
            for (int j = start_j; j <= max_y; j++, new_y += scale) {
                g.drawImage(
                        Sprite.getTile(getFloor(i, j)).getScaledInstance((int) scale + 1, (int) scale + 1,
                                Image.SCALE_SMOOTH),
                        (int) new_x, (int) new_y, null);
                String temp = getBlock(i, j);
                if (temp == null)
                    continue;
                g.drawImage(
                        Sprite.getTile(temp).getScaledInstance((int) scale + 1, (int) scale + 1,
                                Image.SCALE_SMOOTH),
                        (int) new_x, (int) new_y, null);
            }
        }

        // Render floor
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                g.drawImage(
                        Sprite.getTile(ground[i][j]).getScaledInstance((int) scale + 1, (int) scale + 1,
                                Image.SCALE_SMOOTH),
                        (int) (hww + scale * (i - px)),
                        (int) (hwh + scale * (j - py)), null);
            }
        }
        // Render objects
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[i].length; j++) {
                if (objects[i][j] == null)
                    continue;
                g.drawImage(
                        Sprite.getTile(objects[i][j]).getScaledInstance((int) scale + 1, (int) scale + 1,
                                Image.SCALE_SMOOTH),
                        (int) (hww + scale * (i - px)),
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
