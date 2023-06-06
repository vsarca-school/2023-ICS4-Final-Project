package src.main.Scenes;

import java.awt.Graphics;
import java.awt.*;
import java.awt.event.KeyEvent;

import src.main.Drivers.*;
import src.main.Drivers.Window;

public class Options implements ScreenElement {

    public void update(Window w, Graphics g) {
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Math.sqrt(hww * hwh) / 5;
        centerImage(g, w, Sprite.getImage("paused").getScaledInstance((int) scale*4 + 1, (int) scale + 1, Image.SCALE_SMOOTH), w.getWidth()/2, 100);
        centerImage(g, w, Sprite.getImage("continue").getScaledInstance((int) scale*3 + 1, (int) scale + 1, Image.SCALE_SMOOTH), w.getWidth()/2, 200);
        centerImage(g, w, Sprite.getImage("quit").getScaledInstance((int) scale*4 + 1, (int) scale + 1, Image.SCALE_SMOOTH), w.getWidth()/2, 300);
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }

    public void centerImage(Graphics g, Window w, Image image, int x, int y) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
    
        int a = x - imageWidth / 2;
        int b = y - imageHeight / 2;

        g.drawImage(image, a, b, null);
    }
}