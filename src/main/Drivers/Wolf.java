package src.main.Drivers;

import java.awt.Graphics;

import src.main.Scenes.ActionLevel;

public class Wolf extends ScreenElement {
    private ActionPlayer p;
    private ActionLevel parent;
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
    private boolean paw = false;
    private int whichpaw = 0;
    private int pawpolation = 0;
    private double pawx, pawy;
    private static final String[][] animations = { { "wolf-0", "wolf-1", "wolf-2", "wolf-3" },
            { "wolf-7", "wolf-6", "wolf-5", "wolf-4" } };
    private Level l;
    private static final int[][] directions = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

    public Wolf(int x, int y, Level l, ActionLevel p) {
        this.x = x;
        this.y = y;
        this.l = l;
        realx = x;
        realy = y;
        parent = p;
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
                collide(w);
            } else if (interpolation < 16) {
                interpolation++;
                // Do some interpolation in walking between tiles
                realx += directions[direction][0] / 16.0;
                realy += directions[direction][1] / 16.0;
            } else {
                interpolation = 0;
                walk();
                orient();
                collide(w);
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
        x = nextx;
        y = nexty;
        realx = x;
        realy = y;
        if (Math.random() < 0.1)
            walking = false; // Stop walking
    }

    private void orient() {
        // Try to walk
        walking = false;
        if (Math.random() < 0.2 && (playerWalking || Math.random() < 0.5)) {
            walking = true;
        }
        int dir = Math.random() < 0.5 ? 0 : 1; // Which way to go, vertical or sideways
        double decision = Math.random() * 3;
        if (dir == 0) {
            if (y < py)
                direction = 2;
            else if (y > py)
                direction = 0;
            else if (decision < 0.2)
                direction = 2;
            else if (decision < 0.4)
                direction = 0;
            else
                walking = false;
        } else {
            if (x < px)
                direction = 3;
            else if (x > px)
                direction = 1;
            else if (decision < 0.2)
                direction = 3;
            else if (decision < 0.4)
                direction = 1;
            else
                walking = false;
        }
        nextx = x + directions[direction][0];
        nexty = y + directions[direction][1];
    }

    private void collide(Window w) {
        // Check for collision
        String block = l.getBlock(nextx, nexty);
        boolean player = (nextx != apx || nexty != apy) && (nextx != npx || nexty != npy);
        boolean other = block == null && !parent.hasWolf(nextx, nexty, this);
        if (player && other)
            return;
        walking = false;
        if (player)
            return;
        if (paw)
            ; // Pass
        else if (damagepolation < 15) {
            damagepolation++;
        } else {
            damagepolation = 0;
            try {
                p.damage((int) (5 + Math.random() * 10), w);
                paw = true;
                pawx = realx + directions[direction][0]*0.5;
                pawy = realy + directions[direction][1]*0.5;
                pawpolation = 0;
                if (Math.random() < 0.5)
                    whichpaw = 0;
                else
                    whichpaw = 1;
            } catch (NullPointerException e) {
                // Do nothing, normal when changing scenes
            }
        }
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
        // System.out.println("Updated " + px + " " + py + " " + this.x + " " + this.y);
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
            if (paw)
                pawpolation++;
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
            default:
                cur = animations[0][0];
        }
        /*
         * if (walking)
         * cur = animations[px < x ? 1 : 0][animation / 8];
         * else
         * cur = animations[px < x ? 1 : 0][0];
         */
        g.drawImage(Sprite.getScaledTile(cur), (int) (hww + (realx - px) * scale), (int) (hwh + (realy - py) * scale),
                null);

        if (paw) {
            if (pawpolation >= 16)
                paw = false;
            else {
                cur = "paw-" + (4 * whichpaw + pawpolation / 4);
                g.drawImage(Sprite.getScaledTile(cur), (int) (hww + (pawx - px) * scale),
                        (int) (hwh + (pawy - py) * scale),
                        null);
            }
        }
    }
}
