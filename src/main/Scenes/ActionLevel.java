package src.main.Scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.main.Main;
import src.main.Drivers.*;

public class ActionLevel implements ScreenElement {
    private int x, y;
    private Player p;
    private Level l;

    public ActionLevel(Player pl) {
        p = pl;
        l = Level.fromFile("src/main/Levels/Level-1.lvl");
        p.joinLevel(l);
    }

    public void update(Window w, Graphics g) {
    }

    public void addToWindow(Window w) {
        if (l == null) Main.changeScene(5);

        w.addElement(this);
        l.addToWindow(w);
        p.addToWindow(w);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
        l.removeFromWindow(w);
        l = l.nextLevel();
        p.removeFromWindow(w);
    }
}
