package src.main.Drivers;
import java.awt.*;

import src.main.Scenes.Lesson;

/**
 * This is an interface implemented by all class which will draw to the screen
 *      - Victor
 */
public interface ScreenElement {
    /**
     * Update and render the screen element
     * @param w The parent window
     * @param g The java graphics interface
     *      - Victor
     */
    public void update(Window w, Graphics g);

    /**
     * Add this element and child ScreenElements to window w
     * @param l The window to render to
     */
    public void addToWindow(Window w);
    
    /**
     * Remove this element and child ScreenElements from window w
     * @param w The window to stop rendering to
     */
    public void removeFromWindow(Window w);
}
