package src.main.Scenes;

import java.awt.Graphics;

import src.main.Main;
import src.main.Drivers.*;

public class LessonScene implements ScreenElement {
    private Lesson l;

    public LessonScene() {
        l = Lesson.fromFile("src/main/Lessons/Lesson-1.lsn");
    }

    public void update(Window w, Graphics g) {
    }

    public void addToWindow(Window w) {
        if (l == null) Main.changeScene(5);

        l.addToWindow(w);
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        l.removeFromWindow(w);
        l = l.nextLesson();
        w.removeElement(this);
    }
}
