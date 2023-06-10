package src.main.Questions;

import java.io.*;

import src.main.Drivers.*;

/**
 * - Victor
 */
public class QuestionCreatorShelter1 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] answers = {
            "Protection against nature",
            "Protection against wild animals",
            "Not in grassy areas",
            "Inside a ravine"       
        };

        Question q = new Question("Which of the following is not a criterion for shelter?", answers, 4);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Questions/Question-Shelter-1.qsn"));
        out.writeObject(q);
        out.close();
    }
}
