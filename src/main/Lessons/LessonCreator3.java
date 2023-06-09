package src.main.Lessons;

import java.io.*;

import src.main.Drivers.Lesson;

/**
 * - Victor
 */
public class LessonCreator3 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] strs = {
            "In the forest, a variety of food sources are available,\nincluding plants, fish, insects, and mushrooms.",
            "However, it's important to exercise\ncaution and make informed choices.",
            "While edible plants like dandelions and clovers can\nbe found throughout the forest, it's crucial to \nbe selective in your choices.",
            "Similarly, if you encounter berries, consume them\nonly when you are absolutely certain of their safety.",
            "When it comes to fish, lakes can provide\na viable food source.",
            "You can fashion a makeshift net using your clothing\nor create a spear from long, sharp sticks to catch fish.",
            "Remember to cook the fish thoroughly to eliminate\nany potential parasites or diseases.",
            "Another often-overlooked food source\nin the forest is insects.",
            "They are abundant and rich in nutrients.",
            "Ants, crickets, maggots, and earthworms\nare among the edible insects you can find.",
            "However, exercise caution and avoid consuming\nbrightly colored or hard-to-chew insects,\nas they may be dangerous.",
            "Lastly, mushrooms can provide a valuable source\nof food, but they also come with added risk.",
            "Pay attention to warning signs such as bright colors,\nspots, or strangely shaped caps,\nas they may indicate poisonous varieties.",
            "Always be meticulous and consume mushrooms only\nwhen you are completely certain about their safety.",
            "By exercising caution and making informed choices,\nyou can successfully gather food from the forest.",
        };
        int[] pos = {2, 3, 4, 7, 11, 14, 15};
        String[] images = {"droplet", "dandelion", "berries", "fish", "spider", "mushroom", "droplet"};
        String next = "src/main/Lessons/Lesson-4.lsn";
        // Create level 1
        Lesson l1 = new Lesson("Foraging", strs, pos, "apples", images, next);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Lessons/Lesson-3.lsn"));
        out.writeObject(l1);
        out.close();
    }
}
