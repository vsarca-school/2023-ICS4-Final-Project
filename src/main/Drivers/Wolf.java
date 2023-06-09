package src.main.Drivers;

import java.awt.Graphics;

public class Wolf extends ScreenElement {
    int x, y;
    double px, py;

    public Wolf(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Update and render the screen element
     * 
     * @param w The parent window
     * @param g The java graphics interface
     *          - Victor
     */
    public void update(Window w, Graphics g) {
        if (!isPaused()) {
            // LOGIC HERE
        }

        render(w, g);
    }

    public void updatePlayerPos(double x, double y) {
        px = x;
        py = y;
    }

    protected void render(Window w, Graphics g) {
        // Calculate scaling and centering
        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        double scale = Sprite.getTileScale() * 16;
        hww -= scale / 2;
        hwh -= scale / 2;

        g.drawImage(Sprite.getScaledTile("wolf-0"), (int) (hww + (x - px)*scale), (int) (hwh + (y - py)*scale), null);
    }
}
