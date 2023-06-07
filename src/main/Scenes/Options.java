package src.main.Scenes;

import java.awt.Graphics;
import java.awt.*;

import src.main.Main;
import src.main.Drivers.*;
import src.main.Drivers.Window;

public class Options implements ScreenElement {
    private int previousScene;

    public void update(Window w, Graphics g) {
        // Find scaling
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Math.min(hww / 64, hwh / 48);

        // Draw screen
        drawImage(g, Sprite.getImage("paused"), scale, (int) (hww), (int) (hwh - 32 * scale));
        for (int i = 0; i < 5; i++) {
            drawImage(g, Sprite.getTile("vine-0"), scale, (int) (hww + (16 * i - 32) * scale),
                    (int) (hwh - 16 * scale));
        }
        drawImage(g, Sprite.getImage("continue"), scale, (int) (hww), (int) (hwh));
        drawImage(g, Sprite.getImage("menuquit"), scale, (int) (hww), (int) (hwh + 16 * scale));

        // Check for input
        int[] mouse;
        while ((mouse = w.nextMouse()) != null) {
            g.drawOval(mouse[0]-1, mouse[1]-1, 3, 3);
            if (isClicked(Sprite.getImage("continue"), scale, (int) (hww), (int) (hwh), mouse))
            {
                Main.changeScene(previousScene);
            }
            else if (isClicked(Sprite.getImage("menuquit"), scale, (int) (hww), (int) (hwh + 16 * scale), mouse))
            {
                Main.changeScene(0);
            }
            /*if (mouse[0] > hww - 1.5 * scale && mouse[0] < hww + scale * 23 / 16 && mouse[1] > hwh - scale * 7 / 16
                    && mouse[1] < hwh + scale * 3 / 8) {
                // Continue clicked
                Main.changeScene(previousScene);
            }
            if (mouse[0] > hww - 2 * scale && mouse[0] < hww + scale * 31 / 16 && mouse[1] > hwh + scale * 9 / 16
                    && mouse[1] < hwh + scale * 11 / 8) {
                // Quit clicked
                Main.changeScene(0);
            }*/
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
        return (mouse[0] > x - newWidth && mouse[0] < x + newWidth && mouse[1] > y - newHeight && mouse[1] < y + newHeight);
    }

    public void previousScene(int previousScene) {
        this.previousScene = previousScene;
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}