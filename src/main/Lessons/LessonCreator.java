package src.main.Lessons;

import src.main.Scenes.Lesson;
import java.io.*;
/**
 * - Victor
 */
public class LessonCreator {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] strs = {"There are many ways to navigate yourself in the forest, even without a compass.", 
                         "During the night, you can use the North Star, which is a large star next to the big dipper, to locate north.", 
                         "The North Star rarely moves in the night sky, and it is always within 1 degree of true north.", 
                         "During the day, you can identify the cardinal directions based off of the sun and signs in nature.",
                         "In the northern hemisphere, the sun spends most of the time in the southern part of the sky,",
                         "so trees and plants tend to be heavier/more dense on the southern side.",
                         "Moss also tends to grow on the northern side, but take these signs with a grain of salt as they may not always work.",
                         "When you have no other options left, remember to walk in a straight line, as people tend to walk in circles and get even more lost.",
                         "Focus on a landmark and walk towards it, you have a higher chance of rescue if you walk in a straight line rather than a circle."};
        int[] pos = {-1};
        // Create level 1
        Lesson l1 = new Lesson(strs, pos, null);
        
        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Lessons/Lesson-1.lsn"));
        out.writeObject(l1);
        out.close();
    }
}
