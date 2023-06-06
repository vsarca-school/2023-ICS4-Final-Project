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

        // Create level 1
        ground = new String[10][10];
        objects = new String[10][10];
        for (int i=0; i<10; i++)
        {
            for (int j=0; j<10; j++)
            {
                int temp = (int)(Math.random()*4);
                ground[i][j] = "grass-"+temp;
            }
        }
        px = 5;
        py = 5;
        // Save level 1
        Level l1 = new Level(ground, objects, px, py);
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Level-1.lvl"));
        out.writeObject(l1);
        out.close();
    }
}
