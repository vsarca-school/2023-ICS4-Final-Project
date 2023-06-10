package src.main.Scenes;

import src.main.Main;
import src.main.Drivers.*;

/**
 * <h1>ActionLevel Class</h1>
 * Time spent: 1.8 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Radin Ahari(comment)/Victor Sarca(code)
 */

public class ActionLevel extends ScreenElement {
    private ActionPlayer p;
    private Level l;
    private Wolf[] wolves;

    /**
     * Action level constructor
     */
    public ActionLevel() {
        p = new ActionPlayer(100);
        l = Level.fromFile("src/main/Levels/Level-1.lvl");
        loadWolves();
    }

    /**
     * Loads all of the wolves for the cave
     */
    public void loadWolves() {
        // Make new wolves
        int[][] start = l.getWolves();
        wolves = new Wolf[start.length];
        for (int i = 0; i < start.length; i++) {
            wolves[i] = new Wolf(start[i][0], start[i][1], l, this);
            int[] temp = wolves[i].getCoords();
            // System.out.println("Wolf at " + temp[0] + ", " + temp[1]);
        }
        // System.out.println("Loading wolves");
    }

    public void refreshWolves(Window w) {
        // Delete old wolves
        for (Wolf wf : wolves)
            if (w != null)
                wf.removeFromWindow(w);
        loadWolves();
    }

    public void addWolves(Window w) {
        for (Wolf wf : wolves)
            wf.addToWindow(w);
    }

    /**
     * Checks if a wolf is already at a position x,y
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return returns whether there is a wolf at x,y
     */
    public boolean hasWolf(int x, int y, Wolf me) {
        for (Wolf wl : wolves) {
            if (wl == me)
                continue;
            int[] temp = wl.getCoords();
            if ((x == temp[0] && y == temp[1]) || (x == temp[2] && y == temp[3])) {
                //if (me != null)
                //    System.out.println(true);
                return true;
            }
        }
        //if (me != null)
        //    System.out.println(false);
        return false;
    }

    /**
     * Tells wolves where the player is
     * 
     * @param x x coordinate
     * @param y y coordinate
     */
    public void updatePlayerPos(double x, double y, int ax, int ay, int nx, int ny, boolean walking) {
        for (Wolf w : wolves) {
            w.updatePlayerPos(x, y, ax, ay, nx, ny, walking, p);
        }
    }

    /**
     * Adds to window
     * 
     * @param w window
     */
    public void addToWindow(Window w) {
        if (l == null) {
            Main.changeScene(5);
            return;
        }

        l.addToWindow(w);
        p.joinLevel(l, this);
        p.addToWindow(w);
        addWolves(w);
        w.addElement(this);
        // System.out.println(wolves.length);
    }

    /**
     * Removes from window
     * 
     * @param w window
     */
    public void removeFromWindow(Window w) {
        if (l == null)
            return;
        l.removeFromWindow(w);
        l = l.nextLevel();
        if (l != null)
            refreshWolves(w);
        p.removeFromWindow(w);
        w.removeElement(this);
    }
}