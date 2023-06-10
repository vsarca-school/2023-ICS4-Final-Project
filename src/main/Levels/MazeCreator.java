package src.main.Levels;

import java.io.*;

import src.main.Drivers.*;

public class MazeCreator {
    public static String tree() {
        return "tree-" + (int)(Math.random()*8);
    }

    public static String rock() {
        return "rock-" + (int)(Math.random()*2+1);
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ObjectOutputStream out;
        String[][] ground, objects;
        int px, py, scene; // scene is 0=forest, 1=cave
        String next;
        Level l;
        int[][] wolves;
        Question[] questions;

        // Create maze 1
        ground = new String[10][10];
        objects = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int temp = (int) (Math.random() * 4);
                ground[i][j] = "grass-" + temp;
            }
        }
        objects[3][0] = "tree-5";
        objects[4][0] = "rock-2";
        objects[5][0] = "tree-3";
        objects[8][1] = "tree-0";
        objects[3][2] = "sign-0";
        objects[4][2] = "tree-2";
        objects[0][3] = "tree-1";
        objects[1][3] = "rock-1";
        objects[7][3] = "tree-2";
        objects[8][3] = "rock-2";
        objects[6][5] = "tree-5";
        objects[7][5] = "rock-1";
        objects[9][5] = "tree-7";
        objects[2][6] = "tree-3";
        objects[4][6] = "rock-1";
        objects[6][6] = "rock-1";
        objects[7][6] = "rock-2";
        objects[1][8] = "rock-1";
        objects[2][8] = "rock-2";
        objects[3][8] = "tree-2";
        objects[4][8] = "rock-2";
        objects[6][8] = "rock-1";
        objects[7][8] = "rock-1";
        objects[6][9] = "tree-3";
        objects[7][9] = "rock-1";
        objects[8][9] = "rock-2";
        objects[9][9] = "campfire-0";
        px = 0;
        py = 0;
        next = "src/main/Levels/Maze-2.lvl";
        scene = 0;
        questions = new Question[1];
        questions[0] = new Question("Which star is near the Big Dipper?", new String[]{"The North Star","Sirius","Orion","Haedus"}, 0, 3, 2);
        // Save level 1
        l = new Level(ground, objects, px, py, next, scene, new int[0][0], questions);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Maze-1.lvl"));
        out.writeObject(l);
        out.close();

        // Create maze 2
        ground = new String[10][10];
        objects = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int temp = (int) (Math.random() * 4);
                ground[i][j] = "grass-" + temp;
            }
        }
        objects[3][0] = tree();
        objects[5][1] = tree();
        objects[0][1] = tree();
        objects[8][2] = tree();
        objects[1][3] = tree();
        objects[5][4] = tree();
        objects[7][5] = tree();
        objects[1][6] = tree();
        objects[3][7] = tree();
        objects[9][7] = tree();
        objects[6][8] = tree();
        objects[8][8] = "sign-0";
        objects[8][9] = tree();
        objects[9][9] = "campfire-0";
        px = 3;
        py = 2;
        next = "src/main/Levels/Maze-3.lvl";
        scene = 0;
        questions = new Question[1];
        questions[0] = new Question("What is a good source of water?", new String[]{"Lakes", "Rain", "Oceans", "Runoff water"}, 1, 8, 8);
        // Save level 1
        l = new Level(ground, objects, px, py, next, scene, new int[0][0], questions);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Maze-2.lvl"));
        out.writeObject(l);
        out.close();

        // Create maze 3
        ground = new String[20][20];
        objects = new String[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int temp = (int) (Math.random() * 4);
                ground[i][j] = "wall-" + temp;
            }
        }
        objects[5][0] = rock();
        objects[12][0] = rock();
        objects[13][0] = rock();
        objects[14][0] = "campfire-0";

        objects[2][1] = rock();
        objects[14][1] = rock();
        objects[16][1] = "sign-0";
        objects[18][1] = rock();

        objects[6][2] = rock();
        objects[19][2] = rock();

        objects[2][3] = rock();
        objects[10][3] = rock();

        objects[13][4] = rock();

        objects[5][6] = rock();

        objects[8][7] = rock();

        objects[3][8] = rock();

        objects[4][9] = rock();
        objects[14][9] = rock();

        objects[0][10] = rock();
        objects[8][10] = rock();
        objects[12][10] = rock();

        objects[2][11] = rock();
        objects[5][11] = rock();
        objects[13][11] = rock();

        objects[5][12] = "sign-0";
        objects[17][12] = rock();

        objects[4][13] = rock();
        objects[10][13] = rock();

        objects[7][14] = rock();

        objects[2][15] = rock();

        objects[13][16] = rock();

        objects[2][17] = rock();
        objects[6][17] = rock();
        objects[10][17] = rock();

        objects[8][19] = rock();
        objects[10][19] = rock();

        px = 10;
        py = 0;
        next = "src/main/Levels/Maze-4.lvl";
        scene = 1;
        questions = new Question[2];
        questions[0] = new Question("Which of the following is not a food source?", new String[]{"Insects","Fish","Plants","Leaves"}, 3, 16, 1);
        questions[1] = new Question("Which of the following is high in nutrients?", new String[]{"Maggots","Cockroaches","Spiders","Butterfly"}, 0, 5, 12);
        // Save level 1
        l = new Level(ground, objects, px, py, next, scene, new int[0][0], questions);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Maze-3.lvl"));
        out.writeObject(l);
        out.close();

        // Create maze 4
        ground = new String[5][5];
        objects = new String[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int temp = (int) (Math.random() * 4);
                ground[i][j] = "grass-" + temp;
            }
        }
        objects[1][0] = tree();
        objects[1][1] = "sign-0";
        objects[3][1] = "campfire-0";
        objects[3][2] = rock();
        objects[4][3] = rock();
        objects[2][4] = tree();
        px = 4;
        py = 4;
        next = "src/main/Levels/Maze-5.lvl";
        scene = 0;
        questions = new Question[1];
        questions[0] = new Question("Which of the following is not a criterion for shelter?", new String[]{"Protection against nature","Protection against wild animals","Not in grassy areas","Inside a ravine"}, 3, 1, 1);
        // Save level 1
        l = new Level(ground, objects, px, py, next, scene, new int[0][0], questions);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Maze-4.lvl"));
        out.writeObject(l);
        out.close();
    }
}
