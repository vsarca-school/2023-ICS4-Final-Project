package src.main;

import src.main.Drivers.*;
import src.main.Scenes.*;

/**
 * Implemented main function
 *      - Victor
 */
public class Main {
    /**
     * Control for the entire program, however, each individual object is responsible for its own control
     * Switching scenes only happens when the scene wants to switch
     *      - Victor
     * @param args cmd args
     */
    public static void main(String[] args) {
        Sprite.init();

        Window w = new Window("Timber Trek", 800, 600);

        Player p = new Player();

        MainMenu m = new MainMenu();
        Options o = new Options();
        Maze z = new Maze(p);
        ActionLevel a = new ActionLevel(p);
        // Other elements such as lessons and levels will be loaded from files

        //w.addElement(m);
        w.addElement(a);

        while (true)
        {
            w.update();
            w.tick(60);
        }

        /*
        Sound test = new Sound("src/main/Sounds/boom.wav");
        Sound amogus = new Sound("src/main/Sounds/amongus.wav");
        Sound duck = new Sound("src/main/Sounds/duck.wav");
        amogus.loop(5, -25f);
        duck.loop(5, 0f);
        Testing t = new Testing();
        w.addElement(t);*/
    }

}

/**
 * - Victor
 /
class Testing implements ScreenElement {
    private int x, y;

    public void update(Window w)
    {
        if (w.keydown(KeyEvent.VK_W) || w.keydown(KeyEvent.VK_UP))
        {
            y--;
        }
        if (w.keydown(KeyEvent.VK_A) || w.keydown(KeyEvent.VK_LEFT))
        {
            x--;
        }
        if (w.keydown(KeyEvent.VK_S) || w.keydown(KeyEvent.VK_DOWN))
        {
            y++;
        }
        if (w.keydown(KeyEvent.VK_D) || w.keydown(KeyEvent.VK_RIGHT))
        {
            x++;
        }
    }

    public void paint(Graphics g) {
        g.drawRect(x, y, 20, 20);
    }
}*/