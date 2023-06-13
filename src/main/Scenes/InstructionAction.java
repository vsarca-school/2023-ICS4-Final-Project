package src.main.Scenes;

import java.awt.*;

import src.main.Main;
import src.main.Drivers.ScreenElement;
import src.main.Drivers.Sprite;
import src.main.Drivers.Window;

/**
 * InstructionAction Class
 * Time spent: 1 hour
 * 
 * @version 1.1
 * @version 6/10/2023
 * @author Felix Zhao, Victor Sarca
 */
public class InstructionAction extends ScreenElement {

    private double scale;
    private Font font;
    private ActionLevel parent;

    /**
     * Creates a new InstructionMaze object
     * @param z
     */
    public InstructionAction(ActionLevel a) {
        parent = a;
    }

    /**
     * Centers image
     * 
     * @param g     graphics
     * @param image image being centered
     * @param x     x coordinate
     * @param y     y coordinate
     */
    public void centerImage(Graphics g, Image image, int x, int y) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        int a = x - imageWidth / 2;
        int b = y - imageHeight / 2;

        g.drawImage(image, a, b, null);
    }

    /**
     * Centers box
     * 
     * @param g graphics
     * @param c box colour
     * @param x x coordinate
     * @param y y coordinate
     * @param w box width
     * @param h box height
     */

    public void centerBox(Graphics g, Color c, int x, int y, int w, int h) {
        int startX = x - (w / 2);
        int startY = y - (h / 2);
        g.setColor(c);
        g.fillRect(startX, startY, w, h);
        g.setColor(Color.WHITE);
        g.fillRect(startX + (int) scale * 2, startY + (int) scale * 2, w - (int) scale * 4, h - (int) scale * 4);
        g.setColor(c);
        g.fillRect(startX + (int) scale * 3, startY + (int) scale * 3, w - (int) scale * 6,
                h - (int) scale * 6);
    }

    /**
     * Centers string
     * 
     * @param g          graphics
     * @param c          string colour
     * @param text       text centered
     * @param x          x coordinate
     * @param y          y coordinate
     * @param maxWidth   width
     * @param lineHeight height
     * @param font       font
     */
    public void centerString(Graphics g, Color c, String text, int x, int y, int maxWidth, int lineHeight, Font font) {
        String[] lines = text.split("\n");
        g.setFont(font);
        g.setColor(c);

        FontMetrics metrics = g.getFontMetrics(font);

        int lineY = y - ((lines.length * lineHeight) / 2) + metrics.getAscent();

        for (String line : lines) {
            int lineX = x - (metrics.stringWidth(line) / 2);
            g.drawString(line, lineX, lineY);
            lineY += lineHeight;
        }
    }

    /**
     * Max size for all questions
     * 
     * @param g         graphics
     * @param text      text being tested
     * @param maxWidth  maximum width
     * @param maxHeight maximum height
     * @param font      font
     * @return returns the optimal text size
     */
    public int maxFontSize(Graphics g, String text, int maxWidth, int maxHeight, Font font) {
        String[] lines = text.split("\n");
        int optimalSize = Integer.MAX_VALUE;

        for (String line : lines) {
            int minSize = 1;
            int maxSize = maxHeight;
            int size = 0;

            while (minSize <= maxSize) {
                int midSize = (minSize + maxSize) / 2;
                Font testFont = font.deriveFont(Font.PLAIN, midSize);
                FontMetrics metrics = g.getFontMetrics(testFont);
                int textWidth = metrics.stringWidth(line);

                if (textWidth <= maxWidth) {
                    size = midSize;
                    minSize = midSize + 1;
                } else {
                    maxSize = midSize - 1;
                }
            }
            optimalSize = Math.min(optimalSize, size);
        }
        return optimalSize;
    }

    /**
     * checks if a button is clicked
     * 
     * @param image image being tested
     * @param x     x coordinate
     * @param y     y coordinate
     * @param mouse mouse positions
     * @return returns whether its been clicked
     */
    public boolean isClicked(Image image, int x, int y, int[] mouse) {
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        return (mouse[0] > x - width && mouse[0] < x + width && mouse[1] > y - height && mouse[1] < y + height);
    }

    /**
     * adds to window
     * 
     * @param w window
     */
    public void addToWindow(Window w) {
        try {
            //font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/Fonts/VCR_OSD_MONO_1.001.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, Main.loadFile("src/main/Fonts/VCR_OSD_MONO_1.001.ttf"));
        } catch (Exception e) {
        }
        w.addElement(this);
    }

    /**
     * updates window
     * 
     * @param w window
     * @param g graphics
     */
    public void update(Window w, Graphics g) {
        boolean paused = isPaused();

        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        scale = Sprite.getTileScale();

        // centerImage(g, Sprite.getScaledBackground("TitleScreen"), (int) hww, (int) hwh);

        Color c = new Color(53, 45, 82, 255);
        centerBox(g, c, (int) hww, (int) hwh, (int) (hww * 8 / 5), (int) (hwh * 8 / 5));

        int size = maxFontSize(g, "Instructions", (int) hww, (int) hwh, font);
        Font temp = font.deriveFont(Font.PLAIN, size);
        centerString(g, Color.WHITE, "Instructions", (int) hww, (int) (hwh * 2 / 5), (int) hww * 2, size, temp);

        String str = "To move in the action level, use \n" + //
                "w,a,s,d or the arrow keys. Unlike the maze\n" + //
                "level, you can stop when you release\n" + //
                "the movement keys. The goal of the action\n" + //
                "level is to either find a campfire or tent.\n" + //
                "There are wolves which may pose a threat to you,\n" + //
                "which you can damage by clicking in their\n" + //
                "direction. However, it is better to\n" + //
                "avoid the wolves since they can hurt you\n";

        size = maxFontSize(g, str, (int) (hww * 7 / 5), (int) hwh*2, font);
        temp = font.deriveFont(Font.PLAIN, size);
        centerString(g, Color.WHITE, str, (int) hww, (int) hwh, (int) (hww * 8 / 5), size, temp);
        centerImage(g, Sprite.getScaledTile("campfire-0"), (int)hww*11/15, (int)hwh*3/2);
        centerImage(g, Sprite.getScaledTile("wolf-0"), (int)hww*19/15, (int)hwh*3/2);

        size = maxFontSize(g, "[click to continue]", (int) (hww * 8 / 15), (int) hwh*2, font);
        temp = font.deriveFont(Font.PLAIN, size);
        centerString(g, Color.WHITE, "[click to continue]", (int) hww, (int) (hwh * 5 / 3), (int) (hww * 3 / 2), size, temp);
        if (isPaused()) return;
        if (w.nextMouse() != null) {
            parent.removeInstructions();
            removeFromWindow(w);
        }
    }
}
