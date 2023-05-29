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
     * - Victor
     */
    public void update(Window w, Graphics g) {

    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}
