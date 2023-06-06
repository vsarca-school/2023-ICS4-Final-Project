package src.main.Drivers;

import java.awt.Graphics;

/**
 * Hnadles all player movement, plus rendering
 */
public class Player implements ScreenElement {
    Level l;
    String[] walkUp = {"player-12","player-13","player-14","player-15"};
    String[] walkDown = {"player-0","player-1","player-2","player-3"};
    String[] walkRight = {"player-4","player-5","player-6","player-7"};
    String[] walkLeft = {"player-8","player-9","player-10","player-11"};

    /**
     * - Victor
     * 
     * @param lv
     */
    public void joinLevel(Level lv) {
        l = lv;
    }

    /**
     * - Victor/Radin
     */
    public void update(Window w, Graphics g) {
        for(int i = 0; i<4;i++){
            g.drawImage(Sprite.getTile(walkUp[i]), w.getWidth()/2, w.getHeight()/2, null);
        }
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}
