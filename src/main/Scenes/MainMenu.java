package src.main.Scenes;

import java.awt.Graphics;
import java.awt.Image;

import src.main.Drivers.*;

public class MainMenu implements ScreenElement {
    public void update(Window w, Graphics g) {
        // Find scaling
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Math.min(hww / 64, hwh / 48);
        double screenFit = Math.max(hww / 64, hwh / 48);

        // Draw screen
        drawImage(g, Sprite.getImage("TitleScreen"), screenFit, (int)hww, (int)hwh);
        drawImage(g, Sprite.getImage("timbertrek"), scale, (int) (hww), (int) (hwh - 32 * scale));
        for (int i = 0; i < 5; i++) {
            drawImage(g, Sprite.getTile("vine-0"), scale, (int) (hww + (16 * i - 32) * scale),
                    (int) (hwh - 16 * scale));
        }
        drawImage(g, Sprite.getImage("play"), scale, (int) (hww), (int) (hwh));
        drawImage(g, Sprite.getImage("quit"), scale, (int) (hww), (int) (hwh + 16 * scale));

        // Check for input
        int[] mouse;
        while ((mouse = w.nextMouse()) != null) {
            if (mouse[0] > 0)
                ;
        }
    }

    private void drawImage(Graphics g, Image image, double scale, int x, int y) {
        int newWidth = (int) (scale * image.getWidth(null)) + 1;
        int newHeight = (int) (scale * image.getHeight(null)) + 1;
        g.drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), x - newWidth / 2,
                y - newHeight / 2, null);
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