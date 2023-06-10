package src.main.Questions;

import java.io.*;

import src.main.Drivers.*;

/**
 * - Victor
 */
public class QuestionCreatorFood1 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] answers = {
            "Insects",
            "Fish",
            "Plants",
            "Leaves"
        };

        Question q = new Question("Which of the following is not a food source?", answers, 4);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Questions/Question-Food-1.qsn"));
        out.writeObject(q);
        out.close();
    }
}
