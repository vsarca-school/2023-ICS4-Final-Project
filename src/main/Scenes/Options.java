package src.main.Scenes;

import java.awt.Graphics;
import java.awt.*;

import src.main.Main;
import src.main.Drivers.*;
import src.main.Drivers.Window;

//////// TODOOOOOO
//////// MAKE IT CLEANER REMOVE THE SCALE VAIRABLE

// TODO: MAKE IT SEE THROUGH BY ADDING "PAUSE" METHOD TO SCREEN ELEMENTS

public class Options extends ScreenElement {
    private int previousScene;

    public void update(Window w, Graphics g) {
        // Find scaling
        // Find scaling
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Sprite.getImageScale();
        //double screenFit = Sprite.getBgScale();

        // Draw screen
        //drawImage(g, Sprite.getScaledBackground("TitleScreen"), screenFit, (int) hww, (int) hwh); SEE THROUGH
        drawImage(g, Sprite.getScaledImage("paused"), scale, (int) (hww), (int) (hwh - 32 * scale));
        for (int i = 0; i < 5; i++) {
            drawImage(g, Sprite.getScaledImage("vine"), scale, (int) (hww + (16 * i - 32) * scale),
                    (int) (hwh - 16 * scale));
        }
        drawImage(g, Sprite.getScaledImage("continue"), scale, (int) (hww), (int) (hwh));
        drawImage(g, Sprite.getScaledImage("menuquit"), scale, (int) (hww), (int) (hwh + 16 * scale));

        // Check for input
        int[] mouse;
        while ((mouse = w.nextMouse()) != null) {
            if (isClicked(Sprite.getScaledImage("continue"), scale, (int) (hww), (int) (hwh), mouse)) {
                Main.changeScene(previousScene);
            } else if (isClicked(Sprite.getScaledImage("menuquit"), scale, (int) (hww), (int) (hwh + 16 * scale),
                    mouse)) {
                Main.changeScene(0);
            }
        }
    }

    private void drawImage(Graphics g, Image image, double scale, int x, int y) {
        /*
         * int newWidth = (int) (scale * image.getWidth(null));
         * int newHeight = (int) (scale * image.getHeight(null));
         * g.drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
         * x - newWidth / 2,
         * y - newHeight / 2, null);
         */

        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        g.drawImage(image, x - width, y - height, null);
    }

    private boolean isClicked(Image image, double scale, int x, int y, int[] mouse) {
        int newWidth = (int) (scale / 2 * image.getWidth(null));
        int newHeight = (int) (scale / 2 * image.getHeight(null));
        return (mouse[0] > x - newWidth && mouse[0] < x + newWidth && mouse[1] > y - newHeight
                && mouse[1] < y + newHeight);
    }

    public void previousScene(int previousScene) {
        this.previousScene = previousScene;
    }
}