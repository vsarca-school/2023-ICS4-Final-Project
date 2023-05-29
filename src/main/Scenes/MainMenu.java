package src.main.Scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.main.Drivers.*;

public class MainMenu implements ScreenElement, Scene {
    public static final int sceneId = 0;
    
    private int x, y;

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
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }

    public int nextScene() {
        return MainMenu.sceneId;
    }
}