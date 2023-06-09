package src.main.Lessons;

import java.io.*;

import src.main.Drivers.Lesson;

/**
 * - Victor
 */
public class LessonCreator2 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] strs = {
            "When seeking water in nature, it's important to\nremember that clear water does not necessarily mean\nit's safe to drink.",
            "If the water source cannot even support the growth\nof algae, it is probably unsafe for you.",
            "The most reliable water sources include fast flowing\nstreams, rainwater, and groundwater.",
            "To locate a drinkable stream, listen for the sound\nof flowing water and head in that direction.",
            "Once you find the stream, move upstream towards its\nsource as it tends to be cleaner than downstream\nareas, which can contain contaminants.",
            "Rainwater can be collected by using your clothing \nto absorb it and then wringing it into a container.",
            "While obtaining groundwater is more challenging,\nyou can search for signs like mud and dig down to\naccess it.",
            "Remember to settle out any debris from the collected\nwater. Purification is crucial for all water\nsources.",
            "Begin by filtering the water using materials like\nsand and gravel.",
            "Next, boiling the water will effectively eliminate\nmicroorganisms.",
            "Following these steps, the water should be safe\nfor consumption."
        };
        int[] pos = {3, 5, 6, 7, 11};
        String[] images = {"droplet", "waterfall", "shirt", "shovel", "droplet"};
        String next = "src/main/Lessons/Lesson-3.lsn";
        // Create level 1
        Lesson l1 = new Lesson("Finding Water", strs, pos, "water", images, next);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Lessons/Lesson-2.lsn"));
        out.writeObject(l1);
        out.close();
    }
}
