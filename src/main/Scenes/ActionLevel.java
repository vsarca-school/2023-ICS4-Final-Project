package src.main.Scenes;

import src.main.Main;
import src.main.Drivers.*;

public class ActionLevel extends ScreenElement {
    private ActionPlayer p;
    private Level l;
    private Wolf[] wolves;

    public ActionLevel() {
        p = new ActionPlayer(100);
        l = Level.fromFile("src/main/Levels/Level-2.lvl");
        loadWolves();
    }

    public void loadWolves() {
        int[][] start = l.getWolves();
        wolves = new Wolf[start.length];
        for (int i=0; i<start.length; i++) {
            wolves[i] = new Wolf(start[i][0], start[i][1], l);
        }
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
        for (Wolf wf : wolves) wf.addToWindow(w);
        w.addElement(this);
        System.out.println(wolves.length);
    }

    public void removeFromWindow(Window w) {
        l.removeFromWindow(w);
        l = l.nextLevel();
        for (Wolf wf : wolves) wf.addToWindow(w);
        loadWolves();
        p.removeFromWindow(w);
        w.removeElement(this);
    }
}   