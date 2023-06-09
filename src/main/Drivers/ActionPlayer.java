package src.main.Drivers;

import java.awt.Graphics;

import src.main.Main;
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

    /**
     * Calculates damage
     * 
     * @param damage taken
     */
    public void damage(int damage) {
        health -= damage;
        if (health <= 0)
            Main.changeScene(2);
    }

    /**
     * Checks player collision
     * 
     * @param w
     */
    protected void collide(Window w) {
        super.collide(w);
        int tempx = x + directions[direction][0];
        int tempy = y + directions[direction][1];
        if (parent.hasWolf(tempx, tempy))
            walking = false;
    }

    /**
     * Checks player collision while moving
     * 
     * @param w
     */
    private void collideMoving(Window w) {
        int tempx = x + directions[direction][0];
        int tempy = y + directions[direction][1];
        if (parent.hasWolf(tempx, tempy)) {
            // New wolf here, turn around
            x = tempx;
            y = tempy;
            direction = (direction + 2) % 4;
            interpolation = 10 - interpolation;
            nextDirection = -1;
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
                getInput(w);
                if (nextDirection == -1)
                    walking = false;
                else
                    direction = nextDirection;
                collide(w);
            } else if (interpolation < 10) {
                interpolation++;
                getInput(w);
                collideMoving(w);
                // Do some interpolation in walking between tiles
                realx += directions[direction][0] / 10.0;
                realy += directions[direction][1] / 10.0;
            } else {
                interpolation = 0;
                getInput(w);
                walk();
                if (nextDirection == -1)
                    walking = false;
                else
                    direction = nextDirection;
                collide(w);
            }
            // Clear input and update level
            l.updatePlayerPos(realx, realy);
            // System.out.println("Player at " + x + ", " + y + ", " +
            // (x+directions[direction][0]) + ", " + (y+directions[direction][1]));
            parent.updatePlayerPos(realx, realy, x, y, x + directions[direction][0], y + directions[direction][1],
                    walking);
            animation = (animation + 1) % 32;
        }

        render(w, g);
    }
}
