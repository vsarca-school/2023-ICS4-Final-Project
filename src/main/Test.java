package src.main;

import src.main.Drivers.*;
import src.main.Scenes.*;

/**
 * Implemented main function
 * - Victor
 */
public class Test {
    static Window w;

    static Player p;

    static MainMenu m;
    static Options o;
    static Lesson l;
    static Maze z;
    static ActionLevel a;

    static ScreenElement currentScene;

    /**
     * Control for the entire program, however, each individual object is
     * responsible for its own control
     * Switching scenes only happens when the scene wants to switch
     * - Victor
     * 
     * @param args cmd args
     */
    public static void main(String[] args) {
        // Loads resources
        Sprite.init();
        Sprite.print(); // DEBUG

        // Create all objects necessary
        w = new Window("Timber Trek", 800, 600);

        p = new Player();

        m = new MainMenu();
        o = new Options();
        String[] strs = {"There are many ways to navigate yourself in the forest,\neven without a compass.", 
                         "During the night, you can use the North Star, which is a\nlarge star next to the big dipper, to locate north.", 
                         "The North Star rarely moves in the night sky, and it is\nalways within 1 degree of true north.", 
                         "During the day, you can identify the cardinal directions\nbased off of the sun and signs in nature.",
                         "In the northern hemisphere, the sun spends most of the time in\nthe southern part of the sky, so trees and plants tend\nto be heavier/more dense on the southern side.",
                         "Moss also tends to grow on the northern side,\nbut take all signs in nature with a grain of salt.",
                         "When you have no other options left, remember to walk in a straight line,\nas people tend to walk in circles and get even more lost.",
                         "Focus on a landmark and walk towards it, you have a higher chance of\nrescue as you will find a way out eventually."};
        int[] pos = {3, 5};
        String[] images = {"raymond", "logo"};
        // Create level 1
        Lesson l1 = new Lesson(strs, pos, images, "Lesson-2.lsn");
        z = new Maze(p);
        a = new ActionLevel(p);
        ScreenElement currentScene = l1; // TODO: change back to menu when done debugging

        currentScene.addToWindow(w);

        while (true) {
            w.update();
            w.tick(60);
        }
    }

    public static void changeScene(int newScene) {
        switch (newScene) {
            case 0:
                currentScene.removeFromWindow(w);
                currentScene = m;
                currentScene.addToWindow(w);
            case 1:
                currentScene.removeFromWindow(w);
                currentScene = o;
                currentScene.addToWindow(w);
            case 2:
                currentScene.removeFromWindow(w);
                currentScene = l;
                currentScene.addToWindow(w);
            case 3:
                currentScene.removeFromWindow(w);
                currentScene = z;
                currentScene.addToWindow(w);
            case 4:
                currentScene.removeFromWindow(w);
                currentScene = a;
                currentScene.addToWindow(w);
        }
    }
}
