package src.main.Questions;

import java.io.*;

import src.main.Drivers.*;

/**
 * - Victor
 */
public class QuestionCreatorNavigation1 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] answers = {
            "The North Star",
            "Sirius",
            "Orion",
            "Haedus"
        };
        Question q = new Question("Which star is near the Big Dipper?", answers, 3);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Questions/Question-Navigation-1.qsn"));
        out.writeObject(q);
        out.close();
    }
}
