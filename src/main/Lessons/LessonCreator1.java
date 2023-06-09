package src.main.Lessons;

import java.io.*;

import src.main.Drivers.Lesson;

/**
 * - Victor
 */
public class LessonCreator1 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] strs = {
            "In the forest, navigating without a\ncompass is possible using various methods.",
            "At night, the North Star, located near the big dipper,\nremains stationary and within 1 degree of true north.",
            "During the day, cardinal directions can be\ndetermined by observing the sun's position and natural indicators.",
            "In the northern hemisphere, the sun predominantly\nappears in the southern sky, resulting in denser vegetation on\nthe southern side and moss growth on the northern side.",
            "However, these signs may not always be reliable.",
            "When no other options remain, it's crucial to walk in a straight\nline to avoid walking in circles and worsening your situation.",
            "Choose a landmark as a reference point and proceed\ntowards it for a higher chance of rescue."
        };
        int[] pos = {1, 2, 5, 7};
        String[] images = {"droplet", "dipper", "droplet", "bigrock"};
        String next = "src/main/Lessons/Lesson-2.lsn";
        // Create level 1
        Lesson l1 = new Lesson("Navigation", strs, pos, "map", images, next);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Lessons/Lesson-1.lsn"));
        out.writeObject(l1);
        out.close();
    }
}
