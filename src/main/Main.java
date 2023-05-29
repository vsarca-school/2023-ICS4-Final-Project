package src.main;

import src.main.Drivers.*;
import src.main.Scenes.*;

/**
 * Implemented main function
 *      - Victor
 */
public class Main implements ScreenElement {
    /**
     * Control for the entire program, however, each individual object is responsible for its own control
     * Switching scenes only happens when the scene wants to switch
     *      - Victor
     * @param args cmd args
     */
    public static void main(String[] args) {
        Sprite.init();
        Sprite.print(); // DEBUG

        Window w = new Window("Timber Trek", 800, 600);

        Player p = new Player();

        MainMenu m = new MainMenu();
        Options o = new Options();
        Lesson l = new Lesson();
        Maze z = new Maze(p);
        ActionLevel a = new ActionLevel(p);

        m.addToWindow(w);
        int scene = MainMenu.sceneId;
        int newScene;
        while (true)
        {
            while (scene == MainMenu.sceneId)
            {
                w.update();
                w.tick(60);
                newScene = m.nextScene();
            }
        }
    }
}
