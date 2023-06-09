package src.main.Levels;

import java.io.*;

import src.main.Drivers.*;

/**
 * This class extends the Level class in order to access all of its protected
 * fields and modify them. This class will be used to create level objects with
 * specifc setups
 * - Victor
 */

public class LevelCreator {
    /**
     * the main method
     * @param argv arguments
     * @throws IOException exception thrown
     */
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;
        String[][] ground, objects;
        int px, py, scene; // scene is 0=forest, 1=cave
        String next;
        Level l;
        int[][] wolves;
        Question[] questions;

        // Grass tiles
        ground = new String[40][40];
        objects = new String[40][40];
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                int temp = (int) (Math.random() * 4);
                ground[i][j] = "grass-" + temp;
            }
        }

        // trees
        for (int i = 0; i < 200; i++) {
            int temp = (int) (Math.random() * 7);
            int x = (int) (Math.random() * 40);
            int y = (int) (Math.random() * 40);
            while (x >= 20 && x <= 26 && y >= 30 && y <= 34) {
                x = (int) (Math.random() * 40);
                y = (int) (Math.random() * 40);
            }
            while (x >= 29 && x <= 35 && y >= 30 && y <= 35) {
                x = (int) (Math.random() * 40);
                y = (int) (Math.random() * 40);
            }
            objects[x][y] = "tree-" + temp;
        }

        // water tiles
        for (int i = 21; i < 26; i++) {
            for (int j = 31; j < 34; j++) {
                objects[i][j] = "water-0";
            }
        }

        // Top Dirt Tiles (no corners)
        for (int i = 21; i < 26; i++) {
            int temp = (int) (Math.random() * 3 + 4);
            ground[i][30] = "dirt-" + temp;
        }

        // Corners
        ground[20][30] = "dirt-32";
        ground[26][30] = "dirt-20";
        ground[20][34] = "dirt-28";
        ground[26][34] = "dirt-24";

        // Bottom Dirt Tiles (no corners)
        for (int i = 21; i < 26; i++) {
            int temp = (int) (Math.random() * 3 + 12);
            ground[i][34] = "dirt-" + temp;
        }

        // Right Dirt Tiles (no corners)
        for (int j = 31; j < 34; j++) {
            int temp = (int) (Math.random() * 3 + 8);
            ground[26][j] = "dirt-" + temp;
        }

        // Left Dirt Tiles (no corners)
        for (int j = 31; j < 34; j++) {
            int temp = (int) (Math.random() * 3 + 16);
            ground[20][j] = "dirt-" + temp;
        }

        int s = 25;
        // campsite
        objects[7+s][5+s] = "tent-0";
        objects[10+s][7+s] = "tent-0";
        objects[4+s][6+s] = "tent-0";
        objects[7+s][9+s] = "campfire-0";
        ground[6+s][8+s] = "dirt-32";
        ground[7+s][8+s] = "dirt-4";
        ground[8+s][8+s] = "dirt-20";
        ground[6+s][9+s] = "dirt-16";
        ground[8+s][9+s] = "dirt-8";
        ground[6+s][10+s] = "dirt-28";
        ground[7+s][10+s] = "dirt-12";
        ground[8+s][10+s] = "dirt-24";

        px = 5;
        py = 5;
        next = "src/main/Levels/Level-2.lvl";
        // Save level 1
        scene = 0; // forest
        wolves = new int[1][2];
        wolves[0][0] = 7+s;
        wolves[0][1] = 9+s;
        l = new Level(ground, objects, px, py, next, scene, wolves, null);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Level-1.lvl"));
        out.writeObject(l);
        out.close();

        // Create Cave
        ground = new String[15][15];
        objects = new String[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                int temp = (int) (Math.random() * 4);
                ground[i][j] = "wall-" + temp;
            }
        }

        // rocks
        for (int i = 0; i < 25; i++) {
            int temp = (int) (Math.random() * 4);
            int x = (int) (Math.random() * 15);
            int y = (int) (Math.random() * 15);
            objects[x][y] = "rock-" + temp;
        }

        // Save level 2
        scene = 1; // cave
        wolves = new int[][]{{8,1}};
        l = new Level(ground, objects, px, py, next, scene, wolves, null);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Level-2.lvl"));
        out.writeObject(l);
        out.close();

        // Create maze 1
        ground = new String[10][10];
        objects = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int temp = (int) (Math.random() * 4);
                ground[i][j] = "grass-" + temp;
            }
        }
        objects[0][3] = "sign-0";
        objects[5][0] = "campfire-0";
        px = 5;
        py = 5;
        next = "Maze-2.lvl";
        scene = 0;
        questions = new Question[1];
        questions[0] = new Question("TEST QUESTION", new String[]{"A: test", "B: test", "C: test", "d: test"}, 1, 0, 3);
        // Save level 1
        l = new Level(ground, objects, px, py, next, scene, new int[0][0], questions);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Maze-1.lvl"));
        out.writeObject(l);
        out.close();
    }
}
