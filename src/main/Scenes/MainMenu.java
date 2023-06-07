package src.main.Scenes;

import java.awt.Graphics;
import java.awt.Image;

import src.main.Drivers.*;

public class MainMenu implements ScreenElement {
    public void update(Window w, Graphics g) {
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Math.sqrt(hww * hwh) / 5;
        g.drawImage(Sprite.getImage("TitleScreen").getScaledInstance((int) scale * 18 + 1, (int) (scale * 9 + 1),
                Image.SCALE_SMOOTH), (int) (hww - 9 * scale), (int) (hwh - 5 * scale), null);
        g.drawImage(Sprite.getImage("timbertrek").getScaledInstance((int) scale * 9 + 1, (int) scale + 1,
                Image.SCALE_SMOOTH), (int) (hww - 4.65 * scale), (int) (hwh - 2.5 * scale), null);
        for (int i = 0; i < 5; i++) {
            g.drawImage(
                    Sprite.getTile("vine-0").getScaledInstance((int) scale + 1, (int) scale + 1, Image.SCALE_SMOOTH),
                    (int) (hww + (i - 2.5) * scale), (int) (hwh - 1.5 * scale), null);
        }
        g.drawImage(Sprite.getImage("play").getScaledInstance((int) scale * 2 + 1, (int) scale + 1, Image.SCALE_SMOOTH),
                (int) (hww - 1 * scale), (int) (hwh - 0.5 * scale), null);
        g.drawImage(Sprite.getImage("quit").getScaledInstance((int) scale * 2 + 1, (int) scale + 1, Image.SCALE_SMOOTH),
                (int) (hww - 1 * scale), (int) (hwh + 0.6 * scale), null);

        // Get bounding boxes for buttons

        // Check for input
        int[] mouse;
        while ((mouse = w.nextMouse()) != null) {
            if (mouse[0] > 0)
                ;
        }
    }

    private void drawImage(Graphics g, Image image, double scale, int x, int y) {
        int newWidth = (int) (scale * image.getWidth(null));
        int newHeight = (int) (scale * image.getHeight(null));
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