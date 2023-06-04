package src.main.Lessons;

import src.main.Scenes.Lesson;
import java.io.*;
/**
 * - Victor
 */
public class LessonCreator {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        // Create level 1
        Lesson l1 = new Lesson("Hello World!");
        
        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Lessons/Lesson-1.lsn"));
        out.writeObject(l1);
        out.close();
    }
}
