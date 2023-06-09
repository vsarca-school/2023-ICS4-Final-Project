package src.main.Scenes;

import java.awt.Graphics;
import java.util.Random;

import src.main.Main;
import src.main.Drivers.*;

public class ActionLevel extends ScreenElement {
    private Player p;
    private Level l;
    private int playerHealth = 110;
    private int wolfAmount = (int)(Math.random()*5)+1;
    private int[] wolfHealth = new int[wolfAmount];

    public ActionLevel() {
        p = new ActionPlayer(100);
        l = Level.fromFile("src/main/Levels/Level-1.lvl");
    }

    public void addToWindow(Window w) {
        if (l == null) Main.changeScene(5);

        l.addToWindow(w);
        p.addToWindow(w);
        p.joinLevel(l);
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        l.removeFromWindow(w);
        l = l.nextLevel();
        p.removeFromWindow(w);
        w.removeElement(this);
    }

    public void generateHealth(int[] health){
        for(int i = 0; i < health.length; i++){
            health[i] = (int)(Math.random()*80+30);
        }
    }

    public void damageWolf(int wolfNum, int[] health){
        health[wolfNum] -= (int)(Math.random()*30+1);
    }

    public void damagePlayer(int health, int amount){
        health  -= Math.random()*10*amount;
    }

    public int getPlayerHealth(){
        return playerHealth;
    }

    public int getWolfAmount(){
        return wolfAmount;
    }

    public int[] getWolfHealth(){
        return wolfHealth;
    }
}   