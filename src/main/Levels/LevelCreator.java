package src.main.Levels;

import java.io.*;

import src.main.Drivers.*;

/**
 * This class extends the Level class in order to access all of its protected
 * fields and modify them. This class will be used to create level objects with
 * specifc setups
 *      - Victor
 */

public class LevelCreator extends Level {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        // Create level 1
        Level l1 = new Level();
        l1.ground = new String[10][10];
        l1.objects = new String[10][10];
        for (int i=0; i<10; i++)
        {
            for (int j=0; j<10; j++)
            {
                int temp = (int)(Math.random()*4);
                l1.ground[i][j] = "grass-"+temp;
            }
        }
        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Levels/Level-1.lvl"));
        out.writeObject(l1);
        out.close();
    }
}
