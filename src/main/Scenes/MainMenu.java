package src.main.Scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.main.Drivers.ScreenElement;
import src.main.Drivers.Window;

public class MainMenu implements ScreenElement {
    private int x, y;

    public void update(Window w, Graphics g)
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