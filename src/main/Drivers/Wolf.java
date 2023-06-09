package src.main.Drivers;

import java.awt.Graphics;

public class Wolf extends ScreenElement {
    private ActionPlayer p;
    private int x, y;
    private double realx, realy;
    private double px, py;
    private int nextx, nexty;
    private int apx, apy, npx, npy;
    private int animation = 0;
    private int direction = 0;
    private boolean walking;
    private int interpolation = 0;
    private int damagepolation = 0;
    private boolean playerWalking = false;
    private static final String[][] animations = { { "wolf-0", "wolf-1", "wolf-2", "wolf-3" },
            { "wolf-7", "wolf-6", "wolf-5", "wolf-4" } };
    private Level l;
    private static final int[][] directions = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

    public Wolf(int x, int y, Level l) {
        this.x = x;
        this.y = y;
        this.l = l;
        realx = x;
        realy = y;
    }

    /**
     * Update and render the screen element
     * 
     * @param w The parent window
     * @param g The java graphics interface
     *          - Victor
     */
    public void update(Window w, Graphics g) {
        if (!isPaused()) {
            if (!walking) {
                orient();
                collide();
            } else if (interpolation < 8) {
                interpolation++;
                // Do some interpolation in walking between tiles
                realx += directions[direction][0] / 8.0;
                realy += directions[direction][1] / 8.0;
            } else {
                interpolation = 0;
                walk();
                orient();
                collide();
            }
            // System.out.println("Wolf at " + x + ", " + y + ", " +
            // (x+directions[direction][0]) + ", " + (y+directions[direction][1]));
        }

        render(w, g);
    }

    public int[] getCoords() {
        if (!walking)
            return new int[] { x, y, x, y };
        return new int[] { x, y, nextx, nexty };
    }

    private void walk() {
        // Walk
        x  = nextx;
        y = nexty;
        realx = x;
        realy = y;
        if (Math.random() < 0.7)
            walking = false; // Stop walking
    }

    private void orient() {
        // Try to walk
        walking = false;
        if (Math.random() < 0.1 && playerWalking) {
            walking = true;
        }
        int dir = Math.random() < 0.5 ? 0 : 1; // Which way to go, vertical or sideways
        if (dir == 0) {
            if (y < py)
                direction = 2;
            else if (y > py)
                direction = 0;
            else if (Math.random() < 0.5)
                direction = 2;
            else
                direction = 0;
        } else {
            if (x < px)
                direction = 3;
            else if (x > px)
                direction = 1;
            else if (Math.random() < 0.5)
                direction = 3;
            else
                direction = 1;
        }
        nextx = x + directions[direction][0];
        nexty = y + directions[direction][1];
    }

    private void collide() {
        // Check for collision
        String block = l.getBlock(nextx, nexty);
        if (block == null && (nextx != apx || nexty != apy) && (nextx != npx || nexty != npy))
            return;
        if (damagepolation < 60) {
            damagepolation++;
        } else {
            damagepolation = 0;
            p.damage((int) (5 + Math.random() * 10));
        }
        walking = false;
    }

    public void updatePlayerPos(double x, double y, int ax, int ay, int nx, int ny, boolean walking, ActionPlayer p) {
        px = x;
        py = y;
        apx = ax;
        apy = ay;
        npx = nx;
        npy = ny;
        playerWalking = walking;
        this.p = p;
    }

    private void render(Window w, Graphics g) {
        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Sprite.getTileScale() * 16;
        hww -= scale / 2;
        hwh -= scale / 2;

        if (!isPaused()) {
            animation = (animation + 1) % 32;
        }

        String cur;
        switch (direction) {
            case 1:
                if (walking)
                    cur = animations[1][animation / 8];
                else
                    cur = animations[1][0];
                break;
            case 3:
                if (walking)
                    cur = animations[0][animation / 8];
                else
                    cur = animations[0][0];
                break;
            case 0:
            case 2:
                if (walking)
                    cur = animations[px < x ? 1 : 0][animation / 8];
                else
                    cur = animations[px < x ? 1 : 0][0];
                break;
        }
        if (walking)
            cur = animations[px < x ? 1 : 0][animation / 8];
        else
            cur = animations[px < x ? 1 : 0][0];
        g.drawImage(Sprite.getScaledTile(cur), (int) (hww + (realx - px) * scale), (int) (hwh + (realy - py) * scale),
                null);
    }
}
