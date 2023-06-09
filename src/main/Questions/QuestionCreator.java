package src.main.Questions;


import java.io.*;

import src.main.Drivers.*;

public class QuestionCreator {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream out;

        String[] strs = {
            "a"
        };
        Question q = new Question("Navigation", strs);

        // Save level 1
        out = new ObjectOutputStream(new FileOutputStream("src/main/Questions/Question-1.qsn"));
        out.writeObject(q);
        out.close();
    }
}
