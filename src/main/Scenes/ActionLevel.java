package src.main.Scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.main.Drivers.*;

public class ActionLevel implements ScreenElement, Scene {
    private int x, y;
    private Player p;
    private Level l;

    public ActionLevel(Player pl) {
        p = pl;
        l = Level.fromFile("src/main/Levels/Level-1.lvl");
        p.joinLevel(l);
    }

    public void update(Window w, Graphics g) {
        // Update
        if (w.keydown(KeyEvent.VK_W) || w.keydown(KeyEvent.VK_UP)) {
            y--;
        }
        if (w.keydown(KeyEvent.VK_A) || w.keydown(KeyEvent.VK_LEFT)) {
            x--;
        }
        if (w.keydown(KeyEvent.VK_S) || w.keydown(KeyEvent.VK_DOWN)) {
            y++;
        }
        if (w.keydown(KeyEvent.VK_D) || w.keydown(KeyEvent.VK_RIGHT)) {
            x++;
        }

        // Paint
        g.drawRect(x, y, 20, 20);
    }

    public void addToWindow(Window w) {
        p.addToWindow(w);
        l.addToWindow(w);
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        p.removeFromWindow(w);
        l.removeFromWindow(w);
        w.removeElement(this);
    }

    public int change() {
        return 0;
    }
}
