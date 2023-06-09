package src.main.Drivers;

import java.awt.Graphics;
import java.awt.Image;

import src.main.Main;

/**
 * <h1>Player Class</h1>
 * Time spent: 4 hours
 * 
 * @version 1.7
 * @version 6/7/2023
 * @author Victor Sarca, Felix Zhao, Radin Ahari
 */
public class Player extends ScreenElement {
    protected Level l;
    protected int direction = 2;
    protected int nextDirection = 2;
    protected int animation = 0;
    protected int x, y;
    protected double realx, realy;
    protected boolean walking = false;
    protected boolean askingQuestion = false;
    protected int interpolation = 0;
    protected boolean hasWon = false;
    protected static final String[][] animations = { { "player-12", "player-13", "player-14", "player-15" },
            { "player-4", "player-5", "player-6", "player-7" },
            { "player-0", "player-1", "player-2", "player-3" },
            { "player-8", "player-9", "player-10", "player-11" } };
    protected static final int[][] directions = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

    /**
     * Victor Sarca - Joins the level
     * 
     * @param lv level to join
     */
    public void joinLevel(Level lv) {
        hasWon = false;
        l = lv;
        x = l.getStartX();
        y = l.getStartY();
        realx = x;
        realy = y;
    }

    /**
     * Victor Sarca - ends the question sequence
     */
    public void endQuestion() {
        askingQuestion = false;
    }

    /**
     * Victor Sarca, Radin Ahari (subfunctions) - main update loop, implements all
     * player actions
     */
    public void update(Window w, Graphics g) {
        if (!isPaused() && !askingQuestion) {
            if (!walking) {
                getInput(w);
                direction = nextDirection;
                collide(w);
            } else if (interpolation < 6) {
                interpolation++;
                // Do some interpolation in walking between tiles
                realx += directions[direction][0] / 6.0;
                realy += directions[direction][1] / 6.0;
            } else {
                interpolation = 0;
                direction = nextDirection;
                walk();
                collide(w);
            }
            // Clear input and update level
            l.updatePlayerPos(realx, realy);
            animation = (animation + 1) % 32;
            // Check if won
            if (hasWon)
                Main.changeScene(4);
        }

        render(w, g);
    }

    /**
     * Victor Sarca, Felix Zhao - gets the player input to move the player
     * 
     * @param w window object
     */
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

    /**
     * Victor Sarca - checks if the player is colliding with any obstacles
     * 
     * @param w
     */
    protected void collide(Window w) {
        // Check for collision
        int tempx = x + directions[direction][0];
        int tempy = y + directions[direction][1];
        String block = l.getBlock(tempx, tempy);
        if (block == null)
            return;
        if (block.equals("sign-0")) {
            // Give the user a question
            l.getQuestion(tempx, tempy).addToWindow(w);
            askingQuestion = true;
        } else if (block.equals("campfire-0") || block.equals("tent-0")) {
            // Won
            hasWon = true;
        }
        walking = false;
    }

    /**
     * Victor Sarca - lets the player walk in a direction
     */
    protected void walk() {
        // Walk
        x += directions[direction][0];
        y += directions[direction][1];
        realx = x;
        realy = y;
    }

    /**
     * Victor Sarca - renders the player in the middle and redraws tiles to
     * compensate for movement
     * 
     * @param w window object
     * @param g graphics window
     */
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
