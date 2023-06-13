package src.main.Scenes;

import java.awt.Color;
import java.awt.Graphics;

import src.main.Main;
import src.main.Drivers.*;

/**
 * ActionLevel Class
 * Time spent: 1.8 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Radin Ahari(comment)/Victor Sarca(code)
 */

public class ActionLevel extends ScreenElement {
    Sound s = new Sound("src/main/Sounds/ActionLevel.wav");
    private ActionPlayer p;
    private Level l;
    private Wolf[] wolves;
    private int restart = 0;

    /**
     * Action level constructor
     */
    public ActionLevel() {
        p = new ActionPlayer(100);
        l = Level.fromFile("src/main/Levels/Level-1.lvl");
        loadWolves();
    }

    public void update(Window w, Graphics g) {
        if (restart > 0) {
            restart--;
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, w.getWidth(), w.getHeight());
        }
    }

    /**
     * Controls cutscene for restart
     */
    public void restart() {
        restart = 10;
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

    /**
     * Deletes all wolves and loads them from level again
     * 
     * @param w The window
     */
    public void refreshWolves(Window w) {
        // Delete old wolves
        for (Wolf wf : wolves)
            if (w != null)
                wf.removeFromWindow(w);
        loadWolves();
    }

    /**
     * Adds all wolves to the window
     * 
     * @param w The window
     */
    public void addWolves(Window w) {
        for (Wolf wf : wolves)
            wf.addToWindow(w);
    }

    /**
     * Checks if a wolf is already at a position x,y
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param me A wolf to exclude from calculations
     * @return returns whether there is a wolf at x,y
     */
    public Wolf wolfAt(int x, int y, Wolf me) {
        for (Wolf wl : wolves) {
            if (wl == me)
                continue;
            int[] temp = wl.getCoords();
            if ((x == temp[0] && y == temp[1]) || (x == temp[2] && y == temp[3])) {
                //if (me != null)
                //    System.out.println(true);
                return wl;
            }
        }
        //if (me != null)
        //    System.out.println(false);
        return null;
    }

    /**
     * Tells wolves where the player is
     * 
     * @param x Player x coordinate
     * @param y Player y coordinate
     * @param ax Actual player x
     * @param ay Actual player y
     * @param nx Next player x
     * @param ny Next player y
     * @param walking Whether the player is walking
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
        s.loop(-1,-5);
        if (l == null) {
            Main.changeScene(5);
            return;
        }

        l.addToWindow(w);
        addWolves(w);
        p.joinLevel(l, this);
        p.addToWindow(w);
        w.addElement(this);
        // System.out.println(wolves.length);
    }

    /**
     * Removes from window
     * 
     * @param w window
     */
    public void removeFromWindow(Window w) {
        s.stopAll();
        if (l == null)
            return;
        l.removeFromWindow(w);
        l = l.nextLevel();
        p.removeFromWindow(w);
        if (l != null)
            refreshWolves(w);
        w.removeElement(this);
    }
}