package src.main.Drivers;

import java.awt.Graphics;

import src.main.Scenes.ActionLevel;

/**
 * <h1>ActionPlayer Class</h1>
 * Time spent: 30 minutes
 * @version 1.2
 * @version 6/8/2023
 * @author Victor Sarca (code)/Radin Ahari(comments)
 */
public class ActionPlayer extends Player {
    private int health;
    private ActionLevel parent;

    public ActionPlayer(int health) { 
        this.health = health;
    }

    /**
     * Adds player to an action level
     * @param l level being added to
     * @param lv action level being added
     */
    public void joinLevel(Level l, ActionLevel lv) {
        this.l = l;
        parent = lv;
        x = l.getStartX();
        y = l.getStartY();
        realx = x;
        realy = y;
    }

    /**
     * Updates window
     * @param w window
     * @param g graphics
     */
    public void update(Window w, Graphics g) {
        if (!isPaused()) {
            if (!walking) {
                getInput(w);
                direction = nextDirection;
                collide(w);
            } else if (interpolation < 10) {
                interpolation++;
                getInput(w);
                collide(w);
                // Do some interpolation in walking between tiles
                realx += directions[direction][0] / 10.0;
                realy += directions[direction][1] / 10.0;
            } else {
                interpolation = 0;
                getInput(w);
                walk();
                direction = nextDirection;
                collide(w);
            }
            // Clear input and update level
            l.updatePlayerPos(realx, realy);
            parent.updatePlayerPos(realx, realy);
            animation = (animation + 1) % 32;
        }

        render(w, g);
    }
}
