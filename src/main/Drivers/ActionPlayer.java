package src.main.Drivers;

import java.awt.Graphics;
import java.awt.geom.GeneralPath;

import src.main.Main;
import src.main.Scenes.ActionLevel;

/**
 * ActionPlayer Class
 * Time spent: 3.5 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Victor Sarca (code)/Radin Ahari(comments)
 */
public class ActionPlayer extends Player {
    private int health, maxHealth;
    private ActionLevel parent;
    private int lastAttack = 15;
    private boolean fist = false;
    private int whichfist = 0;
    private double fistx, fisty;

    /**
     * Creates a new ActionPlayer with the desired health attributes
     * 
     * @param health
     */
    public ActionPlayer(int health) {
        maxHealth = health;
        this.health = maxHealth;
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
        walking = false;
        interpolation = 0;
        hasWon = false;
        this.health = maxHealth;
    }

    /**
     * Calculates damage
     * 
     * @param damage taken
     * @param w      The window
     */
    public void damage(int damage, Window w) {
        health -= damage;
        // System.out.println(health);
        if (health <= 0) {
            parent.refreshWolves(w);
            parent.addWolves(w);
            parent.restart();
            joinLevel(l, parent);
        }
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
        if (parent.wolfAt(tempx, tempy, null) != null)
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
        if (parent.wolfAt(tempx, tempy, null) != null) {
            // New wolf here, turn around
            x = tempx;
            y = tempy;
            direction = (direction + 2) % 4;
            interpolation = 10 - interpolation;
            nextDirection = -1;
        }
    }

    /**
     * Victor Sarca - moves the player one square in a direction
     */
    protected void walk() {
        super.walk();
        walking = false;
        lastWalk = 5;
    }

    /**
     * Victor - Draws the player and its attacks
     */
    protected void render(Window w, Graphics g) {
        super.render(w, g);

        if (fist) {
            if (lastAttack >= 15)
                fist = false;
            else {
                String cur = "fist-" + (4 * whichfist + lastAttack / 4);
                // Usused animations: + (directions[whichfist][0] * ((lastAttack+2)/4) / 10.0) +
                // (directions[whichfist][1] * ((lastAttack+2)/4) / 10.0)
                g.drawImage(Sprite.getScaledTile(cur),
                        (int) (w.getWidth() / 2.0 + (fistx - realx - 0.5) * 16 * Sprite.getTileScale()),
                        (int) (w.getHeight() / 2.0 + (fisty - realy - 0.5) * 16 * Sprite.getTileScale()),
                        null);
            }
        }
    }

    /**
     * Updates window
     * 
     * @param w window
     * @param g graphics
     */
    public void update(Window w, Graphics g) {
        if (!isPaused() && !instructions) {
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
            if (lastAttack < 35 - health / 5) { // 15 frames when at full health, 35 when about to die
                lastAttack++;
            } else {
                int[] mouse;
                if ((mouse = w.nextMouse()) != null) {
                    lastAttack = 0;
                    fist = true;
                    // whichfist = direction;
                    double rmousex = (double) mouse[0] / w.getWidth();
                    double rmousey = (double) mouse[1] / w.getHeight();
                    if (rmousex > rmousey) {
                        if (rmousex > 1 - rmousey)
                            whichfist = 3;
                        else
                            whichfist = 0;
                    } else if (rmousex > 1 - rmousey)
                        whichfist = 2;
                    else
                        whichfist = 1;
                    fistx = realx + directions[whichfist][0] / 2.0;
                    fisty = realy + directions[whichfist][1] / 2.0;
                    Wolf wl = parent.wolfAt(x + directions[whichfist][0], y + directions[whichfist][1], null);
                    if (wl != null)
                        wl.damage((int) (5 + Math.random() * 10));
                }
            }
            if (lastAttack < 25 - health / 5) {
                // If we're about to be able to attack, remember the click, otherwise discard it
                // More lenient with timings and spamming the mouse button
                while (w.nextMouse() != null)
                    ;
            }

            // System.out.println("Player at " + x + ", " + y + ", " +
            // (x+directions[direction][0]) + ", " + (y+directions[direction][1]));
            parent.updatePlayerPos(realx, realy, x, y, x + directions[direction][0], y + directions[direction][1],
                    walking);
            // Check if won
            if (hasWon)
                Main.changeScene(2);
        }

        render(w, g);
    }
}
