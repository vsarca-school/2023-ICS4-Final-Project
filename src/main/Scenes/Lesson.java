package src.main.Scenes;

import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;

import src.main.Drivers.Scene;
import src.main.Drivers.ScreenElement;
import src.main.Drivers.Window;

/**
 * All of this classs
 * - Victor
 */
public class Lesson implements Serializable, ScreenElement, Scene {
    public static final int sceneId = 2;

    public ArrayList<String> text = new ArrayList<>();

    /**
     * Used for lesson creation, doesn't do anything.
     * - Victor
     */
    public Lesson() {
        ;
    }

    /**
     * Loads a lesson from file
     * 
     * @param fromFile the file containing the lesson info
     *                 - Victor
     */
    public static Lesson fromFile(String file) {
        Lesson lesson = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            lesson = (Lesson) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lesson;
    }


    public void update(Window w, Graphics g) {
        ;
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }

    public int nextScene() {
        return Lesson.sceneId;
    }
}
