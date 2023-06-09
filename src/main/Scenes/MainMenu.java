package src.main.Scenes;

import java.awt.Graphics;
import java.awt.Image;

import src.main.Main;
import src.main.Drivers.*;

//////// TODOOOOOO
//////// MAKE IT CLEANER REMOVE THE SCALE VAIRABLE

public class MainMenu extends ScreenElement {
    public void update(Window w, Graphics g) {
        // Find scaling
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Sprite.getImageScale();

        // Draw background
        drawImage(g, Sprite.getScaledBackground("TitleScreen"), (int) hww, (int) hwh);

        // Leave if paused
        if (isPaused()) return;

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
                System.out.println("Play");
                Main.changeScene(2);
            } else if (isClicked(Sprite.getScaledImage("quit"), (int) (hww), (int) (hwh + 16 * scale), mouse)) {
                System.out.println("QUIt");
                Main.changeScene(-1);
            }
        }
    }

    private void drawImage(Graphics g, Image image, int x, int y) {
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        g.drawImage(image, x - width,
                y - height, null);
    }

    private boolean isClicked(Image image, int x, int y, int[] mouse) {
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        return (mouse[0] > x - width && mouse[0] < x + width && mouse[1] > y - height
                && mouse[1] < y + height);
    }
}