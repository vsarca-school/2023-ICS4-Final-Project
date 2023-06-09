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

        // campsite
        objects[7][5] = "tent-0";
        objects[10][7] = "tent-0";
        objects[4][6] = "tent-0";
        objects[7][9] = "campfire-0";
        ground[6][8] = "dirt-32";
        ground[7][8] = "dirt-4";
        ground[8][8] = "dirt-20";
        ground[6][9] = "dirt-16";
        ground[8][9] = "dirt-8";
        ground[6][10] = "dirt-28";
        ground[7][10] = "dirt-12";
        ground[8][10] = "dirt-24";

        // trees
        for (int i = 0; i < 200; i++) {
            int temp = (int) (Math.random() * 7);
            int x = (int) (Math.random() * 40);
            int y = (int) (Math.random() * 40);
            while (x >= 20 && x <= 26 && y >= 30 && y <= 34) {
                x = (int) (Math.random() * 40);
                y = (int) (Math.random() * 40);
            }
            while (x >= 4 && x <= 10 && y >= 5 && y <= 10) {
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

        px = 5;
        py = 5;
        next = "Level-2.lvl";
        // Save level 1
        scene = 0; // forest
        wolves = new int[0][0];
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
