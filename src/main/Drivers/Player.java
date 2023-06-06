package src.main.Drivers;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Hnadles all player movement, plus rendering
 */
public class Player implements ScreenElement {
    private double scale;
    private Level l;
    private int countDir = 0;
    private int countA = 0;
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
        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        scale = Math.sqrt(hww * hwh) / 5;
        hww -= scale/2;
        hwh -= scale/2;
        // Render player
        while(countDir == 0){
            if(countA !=3){
                g.drawImage(Sprite.getTile(walkUp[countA]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) hww,(int) hwh, null);
            }   
            else{
                g.drawImage(Sprite.getTile(walkUp[countA]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) hww,(int) hwh, null);
                countA = 0;
            }
        }

        while(countDir == 1){
            if(countA !=3){
                g.drawImage(Sprite.getTile(walkDown[countA]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) hww,(int) hwh, null);
            }   
            else{
                g.drawImage(Sprite.getTile(walkDown[countA]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) hww,(int) hwh, null);
                countA = 0;
            }
        }

        while(countDir == 2){
            if(countA !=3){
                g.drawImage(Sprite.getTile(walkLeft[countA]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) hww,(int) hwh, null);
            }   
            else{
                g.drawImage(Sprite.getTile(walkLeft[countA]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) hww,(int) hwh, null);
                countA = 0;
            }
        }

        while(countDir == 3){
            if(countA !=3){
                g.drawImage(Sprite.getTile(walkRight[countA]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) hww,(int) hwh, null);
            }   
            else{
                g.drawImage(Sprite.getTile(walkRight[countA]).getScaledInstance((int)scale+1, (int)scale+1, Image.SCALE_SMOOTH), (int) hww,(int) hwh, null);
                countA = 0;
            }
        }
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}
