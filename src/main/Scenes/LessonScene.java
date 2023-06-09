package src.main.Scenes;

import java.awt.Graphics;

import src.main.Main;
import src.main.Drivers.*;

/**
 * Wrapper around Lesson class to handle lesson loading and moving to the end
 * screen
 *      - Victor
 */
public class LessonScene extends ScreenElement {
    private Lesson l;
    /**
     * LessonScene constructor
     */
    public LessonScene() {
        l = Lesson.fromFile("src/main/Lessons/Lesson-1.lsn");
    }

        /**
     * Adds to window
     * 
     * @param w window being added to
     */

    public void addToWindow(Window w) {
        if (l == null)
            Main.changeScene(5);

        l.addToWindow(w);
        w.addElement(this);
    }

    /**
     * Removes from window
     * 
     * @param w window being removed from
     */

    public void removeFromWindow(Window w) {
        l.removeFromWindow(w);
        l = l.nextLesson();
        w.removeElement(this);
    }
}
