package src.main.Lessons;

import java.io.*;

import src.main.Drivers.Lesson;

/**
 * - Victor
 */
public class LessonCreator4 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] strs = {
            "Finding suitable shelter in a forest can be a challenge,\nbut it comes down to three main criteria.",
            "Firstly, the shelter should provide protection\nfrom the elements.",
            "Secondly, it should be located away from potential\nhazards and wild animals.",
            "Lastly, it's advisable to avoid large grassy areas.",
            "One of the most ideal forest shelters is a small cave.",
            "However, if such a shelter is unavailable, you can construct\na makeshift shelter using sticks and a covering."
        };
        int[] pos = {4, 5, 6};
        String[] images = {"droplet", "entrance", "droplet"};
        String next = "src/main/Lessons/Lesson-5.lsn";
        // Create level 1
        Lesson l1 = new Lesson("Making Shelter", strs, pos, "cave", images, next);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Lessons/Lesson-4.lsn"));
        out.writeObject(l1);
        out.close();
    }
}
