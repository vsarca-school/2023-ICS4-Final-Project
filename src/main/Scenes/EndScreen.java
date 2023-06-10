package src.main.Scenes;

import java.awt.*;
import java.io.*;

import src.main.Main;
import src.main.Drivers.*;
import src.main.Drivers.Window;

/**
 * <h1>EndScreen Class</h1>
 * Time spent: 1 hour
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Radin Ahari(comment)/Felix Zhao(code)
 */

public class EndScreen extends ScreenElement {

    private double scale;
    private Font font;

    /**
     * Centers the image
     * 
     * @param g     graphics
     * @param image the image we want to center
     * @param x     the x coordinate of the image
     * @param y     the y coordinatee of the image
     */
    public void centerImage(Graphics g, Image image, int x, int y) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        int a = x - imageWidth / 2;
        int b = y - imageHeight / 2;

        g.drawImage(image, a, b, null);
    }

    /**
     * Centers a box
     * 
     * @param g graphics
     * @param c the colour
     * @param x x coordinate
     * @param y y coordinate
     * @param w width of box
     * @param h height of box
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
     * Centers a string
     * 
     * @param g          Graphics
     * @param c          Text Colour
     * @param text       Given text
     * @param x          x coordinate
     * @param y          y coordinate
     * @param maxWidth   width of the string
     * @param lineHeight height of the string
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
     * Draws a big title
     * 
     * @param g    graphics
     * @param w    width
     * @param str  string created
     * @param c    colour of text
     * @param font font
     */
    public void heading(Graphics g, Window w, String str, Color c, Font font) {
        int size = maxFontSize(g, str, w.getWidth() * 15 / 16, w.getHeight() / 2, font);
        Font temp = font.deriveFont(Font.PLAIN, size);
        centerString(g, c, str, w.getWidth() / 2, w.getHeight() / 3, w.getWidth(), size, temp);
    }

    /**
     * Maximum font size
     * 
     * @param g         graphics
     * @param text      text being tested
     * @param maxWidth  Width of the string
     * @param maxHeight height of the string
     * @param font      font
     * @return the optimal font size
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
     * adds current element to window
     * 
     * @param w the window being added to
     */
    public void addToWindow(Window w) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/Fonts/VCR_OSD_MONO_1.001.ttf"));
        } catch (Exception e) {
        }
        w.addElement(this);
    }

    /**
     * Updates the screen
     * 
     * @param w the window being updated
     * @param g graphics
     */
    public void update(Window w, Graphics g) {
        double scale = Sprite.getImageScale();
        centerImage(g, Sprite.getScaledBackground("TitleScreen"), w.getWidth() / 2, w.getHeight() / 2);
        centerImage(g, Sprite.getScaledImage("logo"), (int) (w.getWidth() - scale * 16), (int) (w.getHeight() - scale * 16));
        heading(g, w, "Thanks for Playing!", Color.WHITE, font);
        int size = maxFontSize(g, "Made by Grob Studios.", w.getWidth() / 2, w.getHeight() / 2, font);
        Font temp = font.deriveFont(Font.PLAIN, size);
        centerString(g, Color.WHITE, "Made by Grob Studios.", (int) (w.getWidth() / 2 - scale * 8),
                w.getHeight() * 2 / 3, w.getWidth(), size, temp);
    }
}