package src.main.Scenes;

import java.awt.Graphics;
import java.awt.*;

import src.main.Main;
import src.main.Drivers.*;
import src.main.Drivers.Window;

public class Options implements ScreenElement {
    private int previousScene;

    public void update(Window w, Graphics g) {
        // Draw screen
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Math.sqrt(hww * hwh) / 5;
        g.drawImage(Sprite.getImage("paused").getScaledInstance((int) scale*4 + 1, (int) scale + 1, Image.SCALE_SMOOTH), (int)(hww - 2*scale), (int)(hwh - 2.5*scale), null);
        for (int i = 0; i < 5; i++) {
            g.drawImage(Sprite.getTile("vine-0").getScaledInstance((int) scale + 1, (int) scale + 1, Image.SCALE_SMOOTH), (int)(hww + (i-2.5)*scale), (int)(hwh - 1.5*scale), null);
        }
        g.drawImage(Sprite.getImage("continue").getScaledInstance((int) scale*3 + 1, (int) scale + 1, Image.SCALE_SMOOTH), (int)(hww - 1.5*scale), (int)(hwh - 0.5*scale), null);
        g.drawImage(Sprite.getImage("menuquit").getScaledInstance((int) scale*4 + 1, (int) scale + 1, Image.SCALE_SMOOTH), (int)(hww - 2*scale), (int)(hwh + 0.5*scale), null);

        // Check for input
        int[] mouse;
        while ((mouse = w.nextMouse()) != null)
        {
            g.drawRect(mouse[0], mouse[1], 2, 2);
            if (mouse[0] > hww - 1.5*scale && mouse[0] < hww + scale*23/16 && mouse[1] > hwh - scale*7/16 && mouse[1] < hwh + scale*3/8)
            {
                // Continue clicked
                Main.changeScene(previousScene);
            }
            if (mouse[0] > hww - 2*scale && mouse[0] < hww + scale*31/16 && mouse[1] > hwh + scale*9/16 && mouse[1] < hwh + scale*11/8)
            {
                // Quit clicked
                Main.changeScene(0);
            }
        }
    }

    public void previousScene(int previousScene)
    {
        this.previousScene = previousScene;
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
}