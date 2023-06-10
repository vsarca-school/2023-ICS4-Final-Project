package src.main.Levels;

import java.io.*;

import src.main.Drivers.*;

public class MazeCreator5 {
    public static String tree() {
        return "tree-" + (int) (Math.random() * 8);
    }

    public static String rock() {
        return "rock-" + (int) (Math.random() * 2 + 1);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ObjectOutputStream out;
        String[][] ground, objects;
        int px, py, scene; // scene is 0=forest, 1=cave
        String next;
        Level l;
        // int[][] wolves;
        Question[] questions;

        // Create maze 3
        ground = new String[25][25];
        objects = new String[25][25];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                int temp = (int) (Math.random() * 4);
                ground[i][j] = "wall-" + temp;
            }
        }
        objects[3][0] = rock();
        objects[17][0] = rock();

        objects[8][2] = rock();
        objects[13][2] = rock();
        objects[21][2] = rock();

        objects[18][3] = rock();
        objects[24][3] = rock();

        objects[4][4] = rock();
        objects[7][4] = rock();
        objects[12][4] = rock();

        objects[0][5] = rock();
        objects[21][5] = rock();

        objects[16][6] = rock();

        objects[3][7] = rock();
        objects[9][7] = rock();

        objects[0][8] = rock();
        objects[6][8] = rock();
        objects[12][8] = rock();
        objects[13][8] = rock();
        objects[15][8] = rock();

        objects[18][9] = rock();
        objects[21][9] = rock();

        objects[3][10] = rock();

        objects[10][11] = "sign-0";
        objects[13][11] = "campfire-0";
        objects[20][11] = rock();

        objects[7][12] = rock();
        objects[15][12] = rock();
        objects[24][12] = rock();

        objects[18][13] = rock();

        objects[5][14] = rock();

        objects[1][15] = rock();
        objects[8][15] = rock();
        objects[12][15] = rock();
        objects[20][15] = rock();

        objects[16][16] = rock();

        objects[22][17] = rock();

        objects[4][18] = rock();
        objects[8][18] = rock();
        objects[14][18] = rock();
        objects[18][18] = rock();

        objects[10][20] = rock();

        objects[0][21] = rock();
        objects[3][21] = rock();
        objects[7][21] = rock();

        objects[13][22] = rock();
        objects[16][22] = rock();
        objects[21][22] = rock();
        objects[24][22] = rock();

        objects[8][23] = rock();

        objects[14][24] = rock();

        px = 12;
        py = 12;
        next = null;
        scene = 1;
        questions = new Question[1];
        questions[0] = new Question("What kind of wood keeps a fire alive?",
                new String[] { "Large logs", "Small sticks", "Kindling", "Twigs" }, 0, 10, 11);
        // Save maze 5
        l = new Level(ground, objects, px, py, next, scene, new int[0][0], questions);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Maze-5.lvl"));
        out.writeObject(l);
        out.close();
    }
}
