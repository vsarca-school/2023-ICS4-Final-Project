package src.main.Questions;

import java.io.*;

import src.main.Drivers.*;

/**
 * - Victor
 */
public class QuestionCreatorWater2 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] answers = {
            "With a bucket",
            "With your hands",
            "With a t-shirt",
            "With a bottle"
        };

        Question q = new Question("How can you collect rainwater?", answers, 3);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Questions/Question-Water-2.qsn"));
        out.writeObject(q);
        out.close();
    }
}
