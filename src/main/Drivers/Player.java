package src.main.Drivers;

import java.awt.Graphics;

/**
 * Hnadles all player movement, plus rendering
 */
public class Player implements ScreenElement {
    Level l;

    /**
     *      - Victor
     * @param lv
     */
    public void joinLevel(Level lv)
    {
        l = lv;
    }

    /**
     *      - Victor
     */
    public void update(Window w, Graphics g) {
        
    }
    
    public void addToWindow(Window w);
    public void removeFromWindow(Window w);
}
