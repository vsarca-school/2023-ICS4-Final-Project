package src.main.Scenes;

import java.awt.Graphics;

import src.main.Main;
import src.main.Drivers.*;

/**
 * <h1>Maze Class</h1>
 * Time spent: 2.1 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Radin Ahari (comment)/Victor Sarca (code)
 */
public class Maze extends ScreenElement {
    private Player p;
    private Level l;

    /**
     * Maze constructor
     */
    public Maze() {
        p = new Player();
        l = Level.fromFile("src/main/Levels/Maze-1.lvl");
    }

    /**
     * Removes a sign if the question is correct
     * 
     * @param correct whether the question is correct or not
     * @param x       x coordinate of sign
     * @param y       y coordinate of sign
     */
    public void questionCorrect(boolean correct, int x, int y) {
        if (correct) {
            l.delete(x, y);
        } else {
            p.joinLevel(l);
        }
        p.endQuestion();
    }

    public void restart() {
        p.joinLevel(l);
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
        p.addToWindow(w);
        p.joinLevel(l);
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
        l = l.nextLevel();
        p.removeFromWindow(w);
        w.removeElement(this);
    }
}