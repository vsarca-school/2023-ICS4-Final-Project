package src.main.Drivers;

import java.awt.*;

/**
 * This is an interface implemented by all class which will draw to the screen
 * - Victor
 */
public abstract class ScreenElement {
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
    public void addToWindow(Window w)
    {
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

    public final void pause() {
        // Final behaviour: paused = true
        paused = true;
    }

    public final void unpause() {
        // Final behaviour: paused = false
        paused = false;
    }

    public final boolean ispaused() {
        // Final behaviour: return paused
        return paused;
    }
}
