package src.main.Drivers;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Hnadles all player movement, plus rendering
 */
public class Player extends ScreenElement {
    protected Level l;
    protected int direction = 2;
    protected int nextDirection = 2;
    protected int animation = 0;
    protected int x, y;
    protected double realx, realy;
    protected boolean walking = false;
    protected int interpolation = 0;
    String[][] animations = { { "player-12", "player-13", "player-14", "player-15" },
            { "player-4", "player-5", "player-6", "player-7" },
            { "player-0", "player-1", "player-2", "player-3" },
            { "player-8", "player-9", "player-10", "player-11" } };
    protected static final int[][] directions = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

    /**
     * - Victor
     * 
     * @param lv
     */
    public void joinLevel(Level lv) {
        l = lv;
        x = l.getStartX();
        y = l.getStartY();
        realx = x;
        realy = y;
    }

    /**
     * - Victor
     * - Radin (contribution to subfunctions - Victor)
     */
    public void update(Window w, Graphics g) {
        if (!isPaused()) {
            if (!walking) {
                getInput(w);
                direction = nextDirection;
                collide();
            } else if (interpolation < 6) {
                interpolation++;
                // Do some interpolation in walking between tiles
                realx += directions[direction][0] / 6.0;
                realy += directions[direction][1] / 6.0;
            } else {
                interpolation = 0;
                direction = nextDirection;
                walk();
                collide();
            }
            // Clear input and update level
            l.updatePlayerPos(realx, realy);
            animation = (animation + 1) % 32;
        }

        render(w, g);
    }

    protected void getInput(Window w) {
        // Do keyboard input
        int[] moves = { 0, 0, 0, 0 };
        for (int i = 0; i < 4; i++)
            if (w.keyDown(i))
                moves[i] = 1;

        if (moves[0] + moves[1] + moves[2] + moves[3] == 1) // Only one direction pressed
        {
            walking = true;
            if (moves[0] == 1)
                nextDirection = 0;
            else if (moves[1] == 1)
                nextDirection = 1;
            else if (moves[2] == 1)
                nextDirection = 2;
            else
                nextDirection = 3;
        }
    }

    protected void collide() {
        // Check for collision
        String block = l.getBlock(x + directions[direction][0], y + directions[direction][1])
        if (block == null) return;
        if (block == "sign-0")
        {
            // Give the user a question
        }
            walking = false;
    }

    protected void walk() {
        // Walk
        x += directions[direction][0];
        y += directions[direction][1];
        realx = x;
        realy = y;
    }

    protected void render(Window w, Graphics g) {
        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Sprite.getTileScale() * 16;
        hww -= scale / 2;
        hwh -= scale / 2;
        
        // Render player
        String cur;
        if (walking)
            cur = animations[direction][animation / 8];
        else
            cur = animations[direction][0];

        g.drawImage(Sprite.getScaledTile(cur), (int) hww, (int) hwh, null);
    }
}
