package src.main.Drivers;

import java.awt.*;

/**
 * <h1>ScreenElement Abstract Class</h1>
 * Time spent: 2.5 hours
 * 
 * @version 1.1
 * @version 6/6/2023
 * @author Victor Sarca
 */
public abstract class ScreenElement {
    /** whether the game is paused or not */
    private boolean paused = false;

    /**
     * Update and render the screen element
     * 
     * @param w The parent window
     * @param g The java graphics interface
     *          - Victor
     */
    public void update(Window w, Graphics g) {
        // Default behaviour: draw nothing
    }

    /**
     * Add this element and child ScreenElements to window w
     * 
     * @param l The window to render to
     */
    public void addToWindow(Window w) {
        // Default behaviour: add only this
        w.addElement(this);
    }

    /**
     * Remove this element and child ScreenElements from window w
     * 
     * @param w The window to stop rendering to
     */
    public void removeFromWindow(Window w) {
        // Default behaviour: remove only this
        w.removeElement(this);
    }

    /**
     * Toggles if the element is paused
     */
    public final void pause() {
        // Final behaviour: toggle pause
        paused = !paused;
    }

    /**
     * Checks if the element is paused
     * 
     * @return if the element is paused
     */
    public final boolean isPaused() {
        // Final behaviour: return paused
        return paused;
    }
}
