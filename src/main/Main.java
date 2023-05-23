package src.main;
import src.main.Sound;
import src.main.Window;
import java.awt.*;
import java.awt.event.*;

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
        if (w.keypressed(KeyEvent.VK_W)>0 || w.keypressed(KeyEvent.VK_UP)>0)
        {
            y--;
        }
        if (w.keypressed(KeyEvent.VK_A)>0 || w.keypressed(KeyEvent.VK_LEFT)>0)
        {
            x--;
        }
        if (w.keypressed(KeyEvent.VK_S)>0 || w.keypressed(KeyEvent.VK_DOWN)>0)
        {
            y++;
        }
        if (w.keypressed(KeyEvent.VK_D)>0 || w.keypressed(KeyEvent.VK_RIGHT)>0)
        {
            x++;
        }
    }

    public void paint(Graphics g) {
        g.drawRect(x, y, 20, 20);
    }
}