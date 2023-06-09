package src.main.Drivers;

import java.awt.Graphics;

import src.main.Scenes.ActionLevel;

/**
 * <h1>ActionPlayer Class</h1>
 * @version 1.2
 * @version 6/8/2023
 * @author Victor Sarca
 */
public class ActionPlayer extends Player {
    private int health;
    private ActionLevel parent;

    public ActionPlayer(int health) { 
        this.health = health;
    }

    /**
     * - Victor
     * 
     * @param lv
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
        if (parent.hasWolf(tempx, tempy))
            walking = false;
    }

    /**
     * - Victor
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
