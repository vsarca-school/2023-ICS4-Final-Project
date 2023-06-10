package src.main.Questions;

import java.io.*;

import src.main.Drivers.*;

/**
 * - Victor
 */
public class QuestionCreatorWater1 {
    public static void main(String argv[]) throws IOException {
        ObjectOutputStream out;

        String[] answers = {
            "Lakes",
            "Rain",
            "Oceans",
            "Runoff water"
        };
        
        Question q = new Question("What is a good source of water?", answers, 2);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Questions/Question-Water-1.qsn"));
        out.writeObject(q);
        out.close();
    }
}
