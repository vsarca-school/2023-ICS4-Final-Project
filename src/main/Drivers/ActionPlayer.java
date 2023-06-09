package src.main.Drivers;

import java.awt.Graphics;

public class ActionPlayer extends Player {
    private int health;

    public ActionPlayer(int health) {
        this.health = health;
    }

    /**
     * - Victor
     */
    public void update(Window w, Graphics g) {
        if (!isPaused()) {
            if (!walking) {
                getInput(w);
                direction = nextDirection;
                collide();
            } else if (interpolation < 10) {
                interpolation++;
                getInput(w);
                collide();
                // Do some interpolation in walking between tiles
                realx += directions[direction][0] / 10.0;
                realy += directions[direction][1] / 10.0;
            } else {
                interpolation = 0;
                getInput(w);
                walk();
                direction = nextDirection;
                collide();
            }
            // Clear input and update level
            l.updatePlayerPos(realx, realy);
            animation = (animation + 1) % 32;
        }

        render(w, g);
    }
}
