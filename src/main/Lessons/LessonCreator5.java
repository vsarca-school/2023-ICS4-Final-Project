package src.main.Lessons;

import java.io.*;

import src.main.Drivers.Lesson;

/**
 * - Victor
 */
public class LessonCreator5 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] strs = {
            "One of the most important things to learn to survive\nin the forest is how to start a fire.",
            "A fire can actually be started with only 2 sticks and a rope.",
            "The first step is to find a soft wood and cut it into a board.",
            "Then, you can create a makeshift drill with a\nharder wood, sharpening it to a point.",
            "Create a bow made out of a bent stick and string\nand wrap the rope around the drill.",
            "Carve a small hole into the fireboard and start\nmoving the bow back and forth.",
            "This would create enough friction to start a fire,\nwhich you can make bigger using kindling.",
            "The kindling catches the embers and ignites, which\nyou can sustain with smaller twigs.",
            "Finally, larger logs and sticks keep the fire alive.",
            "Make sure to prepare your fire pit beforehand,\nmaking sure the fire would be protected with the wind.",
            "This can be done with rocks surrounding the fire."
        };
        int[] pos = {2, 3, 4, 7, 11};
        String[] images = {"droplet", "board", "drill", "bow", "fire"};
        // Create level 1
        Lesson l1 = new Lesson("Starting Fire", strs, pos, "site", images, null);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Lessons/Lesson-5.lsn"));
        out.writeObject(l1);
        out.close();
    }
}
