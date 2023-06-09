package src.main.Drivers;

import java.awt.Graphics;

import src.main.Scenes.ActionLevel;

/**
 * <h1>ActionPlayer Class</h1>
 * Time spent: 3.5 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Victor Sarca (code)/Radin Ahari(comments)
 */
public class ActionPlayer extends Player {
    private int health;
    private ActionLevel parent;
    private boolean backward;

    public ActionPlayer(int health) {
        this.health = health;
    }

    /**
     * Adds player to an action level
     * 
     * @param l  level being added to
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

    protected void collide(Window w) {
        int tempx = x + directions[direction][0];
        int tempy = y + directions[direction][1];
        super.collide(w);
        if (parent.hasWolf(tempx, tempy)) {
            backward = !backward;
            x = tempx;
            y = tempy;
            direction = (direction + 2) % 4;
            interpolation = 10 - interpolation;
        }
    }

    /**
     * Updates window
     * 
     * @param w window
     * @param g graphics
     */
    public void update(Window w, Graphics g) {
        if (!isPaused()) {
            if (!walking) {
                backward = false;
                getInput(w);
                direction = nextDirection;
                collide(w);
            } else if (interpolation < 10) {
                interpolation++;
                getInput(w);
                collide(w);
                // Do some interpolation in walking between tiles
                if (!backward) {
                    realx += directions[direction][0] / 10.0;
                    realy += directions[direction][1] / 10.0;
                } else {
                    realx -= directions[direction][0] / 10.0;
                    realy -= directions[direction][1] / 10.0;
                }
            } else {
                interpolation = 0;
                backward = false;
                getInput(w);
                walk();
                direction = nextDirection;
                collide(w);
            }
            // Clear input and update level
            l.updatePlayerPos(realx, realy);
            parent.updatePlayerPos(realx, realy, x, y, walking);
            animation = (animation + 1) % 32;
        }

        render(w, g);
    }
}
