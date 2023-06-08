package src.main.Scenes;

import java.awt.Graphics;
import java.awt.Image;

import src.main.Main;
import src.main.Drivers.*;

//////// TODOOOOOO
//////// MAKE IT CLEANER REMOVE THE SCALE VAIRABLE

public class MainMenu implements ScreenElement {
    public void update(Window w, Graphics g) {
        // Find scaling
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Math.min(hww / 64, hwh / 48);
        double screenFit = Math.max(hww / 64, hwh / 48);

        // Draw screen
        drawImage(g, Sprite.getScaledBackground("TitleScreen"), screenFit, (int) hww, (int) hwh);
        drawImage(g, Sprite.getScaledImage("timbertrek"), scale, (int) (hww), (int) (hwh - 32 * scale));
        for (int i = 0; i < 5; i++) {
            drawImage(g, Sprite.getScaledImage("vine"), scale, (int) (hww + (16 * i - 32) * scale),
                    (int) (hwh - 16 * scale));
        }
        drawImage(g, Sprite.getScaledImage("play"), scale, (int) (hww), (int) (hwh));
        drawImage(g, Sprite.getScaledImage("quit"), scale, (int) (hww), (int) (hwh + 16 * scale));

        // Check for input
        int[] mouse;
        while ((mouse = w.nextMouse()) != null) {
            if (isClicked(Sprite.getScaledImage("play"), scale, (int) (hww), (int) (hwh), mouse)) {
                Main.changeScene(2);
            } else if (isClicked(Sprite.getScaledImage("quit"), scale, (int) (hww), (int) (hwh + 16 * scale), mouse)) {
                Main.changeScene(1);
            }
        }
    }

    private void drawImage(Graphics g, Image image, double scale, int x, int y) {
        /*
         * int newWidth = (int) (scale * image.getWidth(null)) + 1;
         * int newHeight = (int) (scale * image.getHeight(null)) + 1;
         * g.drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
         * x - newWidth / 2,
         * y - newHeight / 2, null);
         */
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        g.drawImage(image, x - width / 2,
                y - height / 2, null);
    }

    private boolean isClicked(Image image, double scale, int x, int y, int[] mouse) {
        int newWidth = (int) (scale / 2 * image.getWidth(null));
        int newHeight = (int) (scale / 2 * image.getHeight(null));
        return (mouse[0] > x - newWidth && mouse[0] < x + newWidth && mouse[1] > y - newHeight
                && mouse[1] < y + newHeight);
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}