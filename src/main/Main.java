package src.main;

import java.awt.Graphics;

import src.main.Drivers.*;
import src.main.Scenes.*;

/**
 * Implemented main function
 * - Victor
 */
public class Main implements ScreenElement {
    Window w;

    Player p;

    MainMenu m;
    Options o;
    Lesson l;
    Maze z;
    ActionLevel a;

    ScreenElement currentScene;

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

        // Create a main object to delegate rendering and control
        Main m = new Main();
    }

    public Main() {
        w = new Window("Timber Trek", 800, 600);

        p = new Player();

        m = new MainMenu();
        o = new Options();
        l = new Lesson();
        z = new Maze(p);
        a = new ActionLevel(p);

        currentScene = m;

        w.addElement(this);
        while (true) {
            w.update();
            w.tick(60);
        }
    }

    public void changeScene(int newScene) {
        switch (newScene) {
            case 0:
                currentScene = m;
        }
    }

    public void update(Window w, Graphics g) {
        currentScene.update(w, g);
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}
