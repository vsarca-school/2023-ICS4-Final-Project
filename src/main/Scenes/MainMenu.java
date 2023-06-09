package src.main.Scenes;

import java.awt.Graphics;
import java.awt.Image;

import src.main.Main;
import src.main.Drivers.*;

/**
 * <h1>MainMenu Class</h1>
 * Time spent: 2.3 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Radin Ahari (comment)/Victor Sarca/Radin Ahari/Felix Zhao (code)
 */
public class MainMenu extends ScreenElement {
    /**
     * Updates the window
     * 
     * @param w window being updated
     * @param g graphics
     */
    public void update(Window w, Graphics g) {
        // Find scaling
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Sprite.getImageScale();

        // Draw background and logo
        drawImage(g, Sprite.getScaledBackground("TitleScreen"), (int) hww, (int) hwh);
        drawImage(g, Sprite.getScaledImage("logo"), (int) (2 * hww - scale * 16), (int) (2 * hwh - scale * 16));

        // Leave if paused
        if (isPaused())
            return;

        // Draw screen
        drawImage(g, Sprite.getScaledImage("timbertrek"), (int) (hww), (int) (hwh - 32 * scale));
        for (int i = 0; i < 5; i++) {
            drawImage(g, Sprite.getScaledImage("vine"), (int) (hww + (16 * i - 32) * scale),
                    (int) (hwh - 16 * scale));
        }
        drawImage(g, Sprite.getScaledImage("play"), (int) (hww), (int) (hwh));
        drawImage(g, Sprite.getScaledImage("quit"), (int) (hww), (int) (hwh + 16 * scale));

        // Check for input
        int[] mouse;
        while ((mouse = w.nextMouse()) != null) {
            if (isClicked(Sprite.getScaledImage("play"), (int) (hww), (int) (hwh), mouse)) {
                Main.changeScene(2);
            } else if (isClicked(Sprite.getScaledImage("quit"), (int) (hww), (int) (hwh + 16 * scale), mouse)) {
                Main.changeScene(-1);
            }
        }
    }

    /**
     * Draws an image
     * 
     * @param g     graphics
     * @param image image being drawn
     * @param x     x coordinate
     * @param y     y coordinate
     */
    private void drawImage(Graphics g, Image image, int x, int y) {
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        g.drawImage(image, x - width,
                y - height, null);
    }

    /**
     * checks if a button has been clicked or not
     * 
     * @param image image being clicked
     * @param x     x coordinate
     * @param y     y coordinate
     * @param mouse mouse position
     * @return returns whether the button has been clicked or not
     */
    private boolean isClicked(Image image, int x, int y, int[] mouse) {
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        return (mouse[0] > x - width && mouse[0] < x + width && mouse[1] > y - height
                && mouse[1] < y + height);
    }
}