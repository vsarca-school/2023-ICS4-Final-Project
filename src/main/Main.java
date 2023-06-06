package src.main;

import src.main.Drivers.*;
import src.main.Scenes.*;

/**
 * Implemented main function
 * - Victor
 */
public class Main {
    static Window w;

    static Player p;

    static MainMenu m;
    static Options o;
    static Lesson l;
    static Maze z;
    static ActionLevel a;
    static EndScreen e;

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
        l = Lesson.fromFile("src/main/Lessons/Lesson-1.lsn");
        z = new Maze(p);
        a = new ActionLevel(p);
        e = new EndScreen();
        ScreenElement currentScene = o; // TODO: change back to menu when done debugging

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
            case 5:
                currentScene.removeFromWindow(w);
                currentScene = e;
                currentScene.addToWindow(w);
        }
    }
}
