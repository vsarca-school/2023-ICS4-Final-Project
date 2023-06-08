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
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Sprite.getImageScale();

        // Draw screen
        drawImage(g, Sprite.getScaledImage("paused"), (int) (hww), (int) (hwh - 32 * scale));
        for (int i = 0; i < 5; i++) {
            drawImage(g, Sprite.getScaledImage("vine"), (int) (hww + (16 * i - 32) * scale),
                    (int) (hwh - 16 * scale));
        }
        drawImage(g, Sprite.getScaledImage("continue"), (int) (hww), (int) (hwh));
        drawImage(g, Sprite.getScaledImage("menuquit"), (int) (hww), (int) (hwh + 16 * scale));

        // Check for input
        int[] mouse;
        while ((mouse = w.nextMouse()) != null) {
            if (isClicked(Sprite.getScaledImage("continue"), (int) (hww), (int) (hwh), mouse)) {
                Main.changeScene(previousScene);
            } else if (isClicked(Sprite.getScaledImage("menuquit"), (int) (hww), (int) (hwh + 16 * scale),
                    mouse)) {
                Main.changeScene(0);
            }
        }
    }

    private void drawImage(Graphics g, Image image, int x, int y) {
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        g.drawImage(image, x - width, y - height, null);
    }

    private boolean isClicked(Image image, int x, int y, int[] mouse) {
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        return (mouse[0] > x - width && mouse[0] < x + width && mouse[1] > y - height
                && mouse[1] < y + height);
    }

    public void previousScene(int previousScene) {
        this.previousScene = previousScene;
    }
}