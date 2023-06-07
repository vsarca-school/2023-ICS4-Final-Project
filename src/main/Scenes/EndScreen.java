package src.main.Scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.main.Drivers.*;

public class EndScreen implements ScreenElement {
    private int x, y;

    public void update(Window w, Graphics g) {
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }
    public void removeFromWindow(Window w)
    {
        w.removeElement(this);
    }
}
