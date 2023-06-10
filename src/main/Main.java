package src.main;

import src.main.Drivers.*;
import src.main.Scenes.*;

/**
 * <h1>Main Class</h1>
 * Time spent: 1.2 hours
 * 
 * @version 1.1
 * @version 6/6/2023
 * @author Victor Sarca, Felix Zhao, Radin Ahari
 */
public class Main {
    static Window w;

    static MainMenu m;
    static Options o;
    static LessonScene l;
    static Maze z;
    static ActionLevel a;
    static EndScreen e;

    static ScreenElement currentScene;

    static boolean running = true;

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
        // Sprite.print(); // DEBUG

        // Create all objects necessary
        w = new Window("Timber Trek", 800, 600);

        m = new MainMenu();
        o = new Options();
        l = new LessonScene();
        z = new Maze();
        a = new ActionLevel();
        e = new EndScreen();

        currentScene = m; // TODO: change back to menu when done debugging

        currentScene.addToWindow(w);

        while (running) {
            w.update();
            w.tick(60);
        }
        w.close();
    }

    /**
     * Changes the scene based on the number the scene is defined as
     * 
     * @param newScene The number that the scene is defined as.
     */
    public static void changeScene(int newScene) {
        //System.out.println("DEBUG: Change to scene " + newScene);
        switch (newScene) {
            case -1:
                running = false;
                break;
            case 0:
                currentScene.removeFromWindow(w);
                currentScene = m;
                reset();
                currentScene.addToWindow(w);
                break;
            /*case 1:
                // currentScene.removeFromWindow(w); See through?
                // o.previousScene(currentSceneNum);
                currentScene = o;
                currentScene.addToWindow(w);
                break;*/
            case 2:
                currentScene.removeFromWindow(w);
                currentScene = l;
                currentScene.addToWindow(w);
                break;
            case 3:
                currentScene.removeFromWindow(w);
                currentScene = z;
                currentScene.addToWindow(w);
                break;
            case 4:
                currentScene.removeFromWindow(w);
                currentScene = a;
                currentScene.addToWindow(w);
                break;
            case 5:
                currentScene.removeFromWindow(w);
                currentScene = e;
                currentScene.addToWindow(w);
                break;
            default:
                // Oops?
                running = false;
                //System.out.println("Error: invalid scene index " + newScene);
        }
    }

    public static void restartMaze() {
        if (currentScene == z) {
            z.restart();
        }
    }

    /**
     * Pauses the screen elements
     */
    public static void pause() {
        w.pauseAll();
        if (currentScene.isPaused()) {
            //System.out.println("Adding pause menu");
            o.addToWindow(w);
        }
        else {
            o.removeFromWindow(w);
            //System.out.println("Removing pause menu");
        }
    }

    /**
     * Check if the question at a given coordinate is correct
     * 
     * @param correct whether the answer is correct
     * @param x       x coordinate
     * @param y       y coordinate
     */
    public static void questionCorrect(boolean correct, int x, int y) {
        z.questionCorrect(correct, x, y);
    }

    /**
     * Changes the scene based on the number the scene is defined as
     * 
     * @param newScene The number that the scene is defined as.
     */
    public static void reset() {
        l = new LessonScene();
        z = new Maze();
        a = new ActionLevel();
    }
}
