package src.main.Drivers;

import java.awt.Graphics;

public class Wolf extends ScreenElement {
    int x, y;
    double px, py;
    /**
     * Update and render the screen element
     * 
     * @param w The parent window
     * @param g The java graphics interface
     *          - Victor
     */
    public void update(Window w, Graphics g) {
        render(w, g);
    }

    public void updatePlayerPos(double x, double y) {
        px = x;
        py = y;
    }

    protected void render(Window w, Graphics g) {
        int x = 10;
        int y = 10;
        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Sprite.getTileScale() * 16;
        hww -= scale / 2;
        hwh -= scale / 2;

        g.drawImage(Sprite.getScaledTile("wolf-0"), (int) (hww + (x - px)*scale*16), (int) (hwh + (y - py)*scale*16), null);
    }
}
