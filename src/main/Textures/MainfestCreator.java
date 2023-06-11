package src.main.Textures;

import java.io.*;

public class MainfestCreator {
    public static void main(String[] argv) {
        try {
            Manifest m = new Manifest();
            // Save folders
            File folder = new File("src/main/Textures/Tilemaps");
            m.tilemaps = folder.list();
            folder = new File("src/main/Textures/Images");
            m.images = folder.list();
            folder = new File("src/main/Textures/Backgrounds");
            m.backgrounds = folder.list();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/Textures/.lst"));
            out.writeObject(m);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
