package src.main;
import java.awt.*;
import java.awt.event.*;

import src.main.Drivers.ScreenElement;
import src.main.Drivers.Sound;
import src.main.Drivers.Window;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Poopoo 2 3!");
        Sound test = new Sound("src/main/Sounds/boom.wav");
        Sound amogus = new Sound("src/main/Sounds/amongus.wav");
        Sound duck = new Sound("src/main/Sounds/duck.wav");
        amogus.loop(5, -25f);
        duck.loop(5, 0f);
        Window w = new Window("Timber Trek", 800, 600);
        Testing t = new Testing();
        w.addElement(t);

        while (true) {
            t.update(w);
            w.update();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
            }
        }
    }

}

/**
 * - Victor
 */
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
}