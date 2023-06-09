package src.main.Scenes;

import java.awt.Graphics;

import src.main.Main;
import src.main.Drivers.*;

/**
 * <h1>LessonScene Class</h1>
 * Time spent: 1.7 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Radin Ahari (comment)/Victor Sarca (code)
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
        if (l == null) {
            Main.changeScene(5);
            return;
        }

        l.addToWindow(w);
        w.addElement(this);
    }

    /**
     * Removes from window
     * 
     * @param w window being removed from
     */
    public void removeFromWindow(Window w) {
        if (l == null)
            return;
        l.removeFromWindow(w);
        l = l.nextLesson();
        w.removeElement(this);
    }
}
