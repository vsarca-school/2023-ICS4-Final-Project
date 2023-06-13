package src.main.Scenes;

import java.awt.Color;
import java.awt.Graphics;

import src.main.Main;
import src.main.Drivers.*;

/**
 * Maze Class
 * Time spent: 2.1 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Radin Ahari (comment)/Victor Sarca (code)
 */
public class Maze extends ScreenElement {
    Sound s = new Sound("src/main/Sounds/Maze.wav");
    private Player p;
    private Level l;
    private int restart = 0;
    private boolean instructions = true;
    private InstructionMaze instruction;

    /**
     * Maze constructor
     */
    public Maze() {
        p = new Player();
        l = Level.fromFile("src/main/Levels/Maze-1.lvl");
        instruction = new InstructionMaze(this);
        instructions = true;
        p.freeze();
    }

    /**
     * Notified when instructions are removed
     */
    public void removeInstructions() {
        p.unfreeze();
        instructions = false;
    }

    /**
     * Updates and draws the maze scene
     * @param w the window
     * @param g the Graphics object
     */
    public void update(Window w, Graphics g) {
        if (restart > 0) {
            restart--;
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, w.getWidth(), w.getHeight());
        }
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

    /**
     * Restarts the maze
     */
    public void restart() {
        if (isPaused()) return;
        p.joinLevel(l);
        restart = 10;
    }

    /**
     * Adds to window
     * 
     * @param w window being added to
     */
    public void addToWindow(Window w) {
        s.loop(-1, -10);
        if (l == null) {
            Main.changeScene(5);
            return;
        }

        l.addToWindow(w);
        p.addToWindow(w);
        p.joinLevel(l);
        w.addElement(this);
        if (instructions) instruction.addToWindow(w);
    }

    /**
     * Removes from window
     * 
     * @param w window being removed from
     */
    public void removeFromWindow(Window w) {
        s.stopAll();
        if (l == null)
            return;
        l.removeFromWindow(w);
        l = l.nextLevel();
        p.removeFromWindow(w);
        w.removeElement(this);
    }
}