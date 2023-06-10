package src.main.Questions;

import java.io.*;

import src.main.Drivers.*;

/**
 * - Victor
 */
public class QuestionCreatorFire1 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] answers = {
            "Large logs",
            "Small sticks",
            "Kindling",
            "Twigs"            
        };

        Question q = new Question("What kind of wood keeps a fire alive?", answers, 1);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Questions/Question-Fire-1.qsn"));
        out.writeObject(q);
        out.close();
    }
}
