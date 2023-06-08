package src.main.Scenes;

import java.awt.Graphics;

import src.main.Main;
import src.main.Drivers.*;

public class Combat implements ScreenElement {
    private Player p;
    private Level l;

    public Combat(PlayerCombat pl) {
        
    }

    public void update(Window w, Graphics g) {
    }

    public void addToWindow(Window w) {
        if (l == null) Main.changeScene(5);

        l.addToWindow(w);
        p.addToWindow(w);
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        l.removeFromWindow(w);
        l = l.nextLevel();
        p.removeFromWindow(w);
        w.removeElement(this);
    }
}
