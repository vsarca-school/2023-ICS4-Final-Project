package src.main.Scenes;

import java.awt.Graphics;
import java.util.Random;

import src.main.Main;
import src.main.Drivers.*;

public class ActionLevel extends ScreenElement {
    private ActionPlayer p;
    private Level l;
    private Wolf[] wolves;

    public ActionLevel() {
        p = new ActionPlayer(100);
        l = Level.fromFile("src/main/Levels/Level-1.lvl");
    }

    public void updatePlayerPos(double x, double y) {
        for (Wolf w : wolves) {
            w.updatePlayerPos(x, y);
        }
    }

    public void addToWindow(Window w) {
        if (l == null) Main.changeScene(5);

        l.addToWindow(w);
        p.joinLevel(l, this);
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