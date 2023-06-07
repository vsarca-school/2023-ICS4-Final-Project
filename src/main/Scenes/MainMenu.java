package src.main.Scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.main.Drivers.*;

public class MainMenu implements ScreenElement {
    public void update(Window w, Graphics g) {
        g.drawString("This is the Main Menu!", 20, 20);
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}