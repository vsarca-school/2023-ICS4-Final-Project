package src.main.Drivers;

import java.awt.Graphics;

public class Wolf extends ScreenElement {
    private int x, y;
    private double px, py;
    private int animation = 0;
    private int direction = 0;
    private boolean walking;
    private static final String[][] animations = { { "wolf-0", "wolf-1", "wolf-2", "wolf-3" },
            { "wolf-7", "wolf-6", "wolf-5", "wolf-4" } };
    private Level l;
    private static final int[][] directions = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

    public Wolf(int x, int y, Level l) {
        this.x = x;
        this.y = y;
        this.l = l;
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
            walk();
            collide();
        }

        render(w, g);
    }

    private void walk() {
        // Try to walk
        int dir = Math.random() < 0.5 ? 0 : 1; // Which way to go, vertical or sideways
        if (dir == 0) {
            if (y < py)
                direction = 2;
            else if (y > py)
                direction = 0;
        } else {
            if (x < px)
                direction = 3;
            else if (x > px)
                direction = 1;
        }
    }

    private void collide() {
        // Check for collision
        String block = l.getBlock(x + directions[direction][0], y + directions[direction][1]);
        if (block == null)
            return;
            System.out.println("WALL");
        walking = false;
    }

    public void updatePlayerPos(double x, double y) {
        px = x;
        py = y;
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
        if (walking)
            cur = animations[px < x ? 1 : 0][animation / 8];
        else
            cur = animations[px < x ? 1 : 0][0];
        g.drawImage(Sprite.getScaledTile(cur), (int) (hww + (x - px) * scale), (int) (hwh + (y - py) * scale), null);
    }
}
