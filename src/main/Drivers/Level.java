package src.main.Drivers;

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;

/**
 * <h1>Level Class</h1>
 * Time spent: 3.6 hours
 * 
 * @version 1.2
 * @version 6/7/2023
 * @author Victor Sarca
 */
public class Level extends ScreenElement implements Serializable {
    /**
     * This hash table is copied from Victor's noise generation programs
     * This is the hash table used by Ken Perlin for his algorithms as well as many
     * other uses
     */
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
    private int backgroundScene;
    private int[][] startWolves;
    private Question[] questions;

    /**
     * Victor Sarca - Level class constructor
     * 
     * @param ground    ground tiles
     * @param objects   object collision tiles
     * @param px        player x
     * @param py        player y
     * @param nextLevel next level
     * @param scene     chooses scene: trees or rocks
     * @param wolves    wolf tiles
     * @param questions array of questions
     */
    public Level(String[][] ground, String[][] objects, int px, int py, String nextLevel, int scene, int[][] wolves,
            Question[] questions) {
        this.ground = ground;
        this.objects = objects;
        this.startx = px;
        this.starty = py;
        this.nextLevel = nextLevel;
        this.backgroundScene = scene;
        this.startWolves = wolves;
        this.questions = questions;
    }

    /**
     * Victor Sarca - gets the question at the x and y coordinates
     * 
     * @param x coordinate
     * @param y coordinate
     * @return the question
     */
    public Question getQuestion(int x, int y) {
        for (Question q : questions) {
            if (q.isAt(x, y))
                return q;
        }
        return null;
    }

    /**
     * Victor Sarca - deletes an object from the obstacles
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void delete(int x, int y) {
        objects[x][y] = null;
    }

    /**
     * Victor Sarca - Loads a level from file
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

    /**
     * Victor Sarca - gets the next level
     * 
     * @return
     */
    public Level nextLevel() {
        if (nextLevel != null)
            return Level.fromFile(nextLevel);
        return null;
    }

    /**
     * Victor Sarca - updates the player position
     * 
     * @param x
     * @param y
     */
    public void updatePlayerPos(double x, double y) {
        px = x;
        py = y;
    }

    /**
     * Victor Sarca - gets the floor tile and returns it as a string
     * 
     * @param x coordinate
     * @param y coordinate
     * @return name of the tile
     */
    private String getFloor(int x, int y) {
        if (x < 0 || x >= objects.length || y < 0 || y >= objects[0].length) {
            int sprite = perm[(Math.abs(x) + perm[Math.abs(y) % 256]) % 256];
            switch (backgroundScene) {
                case 0:
                    return "grass-" + sprite % 4;
                case 1:
                    return "wall-" + sprite % 4;
            }

        }
        return ground[x][y];
    }

    /**
     * Victor Sarca - gets the obstacle at x and y coordinate
     * 
     * @param x coordinate
     * @param y coordinate
     * @return the tile as a string
     */
    public String getBlock(int x, int y) {
        if (x < 0 || x >= objects.length || y < 0 || y >= objects[0].length) {
            int sprite = perm[(Math.abs(x) + 13 + perm[(Math.abs(y) + 13) % 256]) % 256];
            if (x == -1 || x == objects.length || y == -1 || y == objects[0].length || sprite % 2 == 0) {
                switch (backgroundScene) {
                    case 0:
                        return "tree-" + sprite % 8;
                    case 1:
                        return "rock-" + sprite % 4;
                }
            } else
                return null;
        }
        return objects[x][y];
    }

    /**
     * Victor Sarca - start position of player
     * 
     * @return x coordinate
     */
    public int getStartX() {
        return startx;
    }

    /**
     * Victor Sarca - start position of player
     * 
     * @return y coordinate
     */
    public int getStartY() {
        return starty;
    }

    /**
     * Victor Sarca - start position of the wolves
     * 
     * @return start coordinates of the wolves
     */
    public int[][] getWolves() {
        return startWolves;
    }

    /**
     * Victor Sarca - main update loop, handles all logic
     */
    public void update(Window w, Graphics g) {
        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Sprite.getTileScale() * 16;
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
                g.drawImage(Sprite.getScaledTile(getFloor(i, j)), (int) new_x, (int) new_y, null);
                String temp = getBlock(i, j);
                if (temp == null)
                    continue;
                g.drawImage(Sprite.getScaledTile(temp), (int) new_x, (int) new_y, null);
            }
        }

        // Render floor
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                g.drawImage(Sprite.getScaledTile(ground[i][j]), (int) (hww + scale * (i - px)),
                        (int) (hwh + scale * (j - py)), null);
            }
        }
        // Render objects
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[i].length; j++) {
                if (objects[i][j] == null)
                    continue;
                g.drawImage(Sprite.getScaledTile(objects[i][j]), (int) (hww + scale * (i - px)),
                        (int) (hwh + scale * (j - py)), null);
            }
        }
    }
}
