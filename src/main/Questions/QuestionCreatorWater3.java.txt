package src.main.Questions;

import java.io.*;

import src.main.Drivers.*;

/**
 * - Victor
 */
public class QuestionCreatorWater3 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] answers = {
            "Filtering with sand",
            "Boiling water",
            "Industrial water filter",
            "Letting it sit out"
        };

        Question q = new Question("Which of the following doesn't purify water?", answers, 4);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Questions/Question-Water-3.qsn"));
        out.writeObject(q);
        out.close();
    }
}
