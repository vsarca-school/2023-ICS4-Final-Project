package src.main.Questions;

import java.io.*;

import src.main.Drivers.*;

/**
 * - Victor
 */
public class QuestionCreatorFood2 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] answers = {
            "Maggots",
            "Cockroaches",
            "Spiders",
            "Butterfly"
        };

        Question q = new Question("Which of the following is high in nutrients?", answers, 1);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Questions/Question-Food-2.qsn"));
        out.writeObject(q);
        out.close();
    }
}
