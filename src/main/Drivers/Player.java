package src.main.Drivers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * Hnadles all player movement, plus rendering
 */
public class Player implements ScreenElement {
    private double scale;
    private Level l;
    private int direction = 2;
    private int animation = 0;
    private int x = 0;
    private int y = 0;
    private boolean walking = false;
    String[] walkDown = { "player-0", "player-1", "player-2", "player-3" };
    String[] walkLeft = { "player-4", "player-5", "player-6", "player-7" };
    String[] walkRight = { "player-8", "player-9", "player-10", "player-11" };
    String[] walkUp = { "player-12", "player-13", "player-14", "player-15" };
    private static final int[][] directions = {{0,-1}, {-1,0}, {0,1}, {1,0}};

    /**
     * - Victor
     * 
     * @param lv
     */
    public void joinLevel(Level lv) {
        l = lv;
        x = l.getStartX();
        y = l.getStartY();
    }

    /**
     * - Victor/Radin
     */
    public void update(Window w, Graphics g) {
        /*/ DEBUG
        System.out.println(w.keypressed(KeyEvent.VK_W) + " " + w.keypressed(KeyEvent.VK_UP) + " "
                + w.keypressed(KeyEvent.VK_A) + " " + w.keypressed(KeyEvent.VK_LEFT) + " "
                + w.keypressed(KeyEvent.VK_S) + " " + w.keypressed(KeyEvent.VK_DOWN) + " "
                + w.keypressed(KeyEvent.VK_D) + " " + w.keypressed(KeyEvent.VK_DOWN));//*/

        if (!walking) {
            // Do keyboard input
            int[] moves = { 0, 0, 0, 0 };
            if (w.keypressed(KeyEvent.VK_W) + w.keypressed(KeyEvent.VK_UP) > 0)
                moves[0] = 1;
            if (w.keypressed(KeyEvent.VK_A) + w.keypressed(KeyEvent.VK_LEFT) > 0)
                moves[1] = 1;
            if (w.keypressed(KeyEvent.VK_S) + w.keypressed(KeyEvent.VK_DOWN) > 0)
                moves[2] = 1;
            if (w.keypressed(KeyEvent.VK_D) + w.keypressed(KeyEvent.VK_RIGHT) > 0)
                moves[3] = 1;
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
            if (l.getBlock(x+directions[direction][0], y+directions[direction][1]) != null) walking = false;
        } else {
            // Walk
            x += directions[direction][0];
            y += directions[direction][1];
            // Check for collision
            if (l.getBlock(x+directions[direction][0], y+directions[direction][1]) != null) walking = false;
        }
        // Clear input and update level
        w.clearInput();
        l.updatePlayerPos(x, y);

        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        scale = Math.sqrt(hww * hwh) / 5;
        hww -= scale / 2;
        hwh -= scale / 2;
        // Render player
        animation = (animation + 1) % 20;
        String cur = "player-0";
        switch (direction) {
            case 0:
                if (walking)
                    cur = walkUp[animation / 5];
                else
                    cur = walkUp[0];
                break;
            case 1:
                if (walking)
                    cur = walkLeft[animation / 5];
                else
                    cur = walkLeft[0];
                break;
            case 2:
                if (walking)
                    cur = walkDown[animation / 5];
                else
                    cur = walkDown[0];
                break;
            case 3:
                if (walking)
                    cur = walkRight[animation / 5];
                else
                    cur = walkRight[0];
                break;
        }

        g.drawImage(Sprite.getTile(cur).getScaledInstance((int) scale + 1, (int) scale + 1,
                Image.SCALE_SMOOTH), (int) hww, (int) hwh, null);
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}
