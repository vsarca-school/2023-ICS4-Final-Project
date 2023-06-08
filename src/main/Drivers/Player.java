package src.main.Drivers;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Hnadles all player movement, plus rendering
 */
public class Player extends ScreenElement {
    private double scale;
    private Level l;
    private int direction = 2;
    private int animation = 0;
    private int x, y;
    private double realx, realy;
    private boolean walking = false;
    private int interpolation = 0;
    String[][] animations = { { "player-12", "player-13", "player-14", "player-15" },
            { "player-4", "player-5", "player-6", "player-7" },
            { "player-0", "player-1", "player-2", "player-3" },
            { "player-8", "player-9", "player-10", "player-11" } };
    private static final int[][] directions = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

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
     * - Victor/Radin
     */
    public void update(Window w, Graphics g) {
        /*
         * / DEBUG
         * System.out.println(w.keypressed(KeyEvent.VK_W) + " " +
         * w.keypressed(KeyEvent.VK_UP) + " "
         * + w.keypressed(KeyEvent.VK_A) + " " + w.keypressed(KeyEvent.VK_LEFT) + " "
         * + w.keypressed(KeyEvent.VK_S) + " " + w.keypressed(KeyEvent.VK_DOWN) + " "
         * + w.keypressed(KeyEvent.VK_D) + " " + w.keypressed(KeyEvent.VK_DOWN));//
         */

        if (!walking) {
            // Do keyboard input
            int[] moves = { 0, 0, 0, 0 };
            for (int i = 0; i < 4; i++)
                if (w.keyDown(i))
                    moves[i] = 1;

            if (moves[0] + moves[1] + moves[2] + moves[3] == 1) // Only one direction pressed
            {
                walking = true;
                if (moves[0] == 1)
                    direction = 0;
                else if (moves[1] == 1)
                    direction = 1;
                else if (moves[2] == 1)
                    direction = 2;
                else
                    direction = 3;
            }

            // Check for collision
            if (l.getBlock(x + directions[direction][0], y + directions[direction][1]) != null)
                walking = false;
        } else if (interpolation < 6) {
            interpolation++;
            // Do some interpolation in walking between tiles
            realx += directions[direction][0] / 6.0;
            realy += directions[direction][1] / 6.0;
        } else {
            interpolation = 0;
            // Walk
            x += directions[direction][0];
            y += directions[direction][1];
            realx = x;
            realy = y;
            // Check for collision
            if (l.getBlock(x + directions[direction][0], y + directions[direction][1]) != null)
                walking = false;
        }
        // Clear input and update level
        l.updatePlayerPos(realx, realy);

        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        scale = Sprite.getTileScale()*16;
        hww -= scale / 2;
        hwh -= scale / 2;
        // Render player
        animation = (animation + 1) % 32;

        String cur;
        if (walking)
            cur = animations[direction][animation / 8];
        else
            cur = animations[direction][0];

        g.drawImage(Sprite.getScaledTile(cur), (int) hww, (int) hwh, null);
    }
}
