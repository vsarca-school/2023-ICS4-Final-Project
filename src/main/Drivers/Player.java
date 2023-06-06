package src.main.Drivers;

import java.awt.Graphics;

/**
 * Hnadles all player movement, plus rendering
 */
public class Player implements ScreenElement {
    Level l;

    /**
     * - Victor
     * 
     * @param lv
     */
    public void joinLevel(Level lv) {
        l = lv;
    }

    /**
     * - Victor/Radin
     */
    public void update(Window w, Graphics g) {
        g.drawImage(Sprite.getTile("player-0"), w.getWidth()/2, w.getHeight()/2, null);
        g.drawRect(20, 20, 20, 20);
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}
