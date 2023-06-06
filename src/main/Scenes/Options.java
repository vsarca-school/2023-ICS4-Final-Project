package src.main.Scenes;

import java.awt.Graphics;
import java.awt.*;
import java.awt.event.KeyEvent;

import src.main.Drivers.*;

public class Options implements ScreenElement {
    private int x, y;

    public void update(Window w, Graphics g) {
        centerImage();
    }

    public void centerImage(Graphics g, Window w, Image image, int x, int y) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
    
        int a = (w.getWidth() - imageWidth) / 2;
        int b = (w.getHeight() - imageHeight) / 2;
    
        g.drawImage(image, a, b, null);
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }
    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}