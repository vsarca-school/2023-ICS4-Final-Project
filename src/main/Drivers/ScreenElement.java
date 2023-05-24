package src.main.Drivers;
import java.awt.*;

/**
 * This is an interface implemented by all class which will draw to the screen
 *      - Victor
 */
public interface ScreenElement {
    /**
     * Update and render the screen element
     * @param g The java graphics interface
     */
    public void update(Graphics g);
}
