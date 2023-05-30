package src.main.Levels;

import src.main.Drivers.*;

/**
 * This class extends the Level class in order to access all of its protected
 * fields and modify them. This class will be used to create level objects with
 * specifc setups
 *      - Victor
 */

public class LevelCreator extends Level {
    public static void main(String argv[]) {
        // Create level 1
        Level l1 = new Level();
        l1.ground = new String[40][40];
        l1.objects = new String[40][40];
        l1.ground[0][0] = "Name of texture!";
    }
}
