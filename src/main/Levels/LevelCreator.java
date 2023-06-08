package src.main.Levels;

import java.io.*;

import src.main.Drivers.*;

/**
 * This class extends the Level class in order to access all of its protected
 * fields and modify them. This class will be used to create level objects with
 * specifc setups
 *      - Victor
 */

public class LevelCreator {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;
        String[][] ground, objects;
        int px, py;
        String next;
        Level l;

        // Create level 1
        ground = new String[40][40];
        objects = new String[40][40];
        for (int i=0; i<40; i++)
        {
            for (int j=0; j<10; j++)
            {
                int temp = (int)(Math.random()*4);
                ground[i][j] = "grass-"+temp;
            }
        }
        px = 5;
        py = 5;
        next = "Level-2.lvl";
        // Save level 1
        l = new Level(ground, objects, px, py, next);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Level-1.lvl"));
        out.writeObject(l);
        out.close();

        // Create maze 1
        ground = new String[10][10];
        objects = new String[10][10];
        for (int i=0; i<10; i++)
        {
            for (int j=0; j<10; j++)
            {
                int temp = (int)(Math.random()*4);
                ground[i][j] = "dirt-"+temp;
            }
        }
        px = 5;
        py = 5;
        next = "Maze-2.lvl";
        // Save level 1
        l = new Level(ground, objects, px, py, next);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Maze-1.lvl"));
        out.writeObject(l);
        out.close();
    }
}
