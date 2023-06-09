package src.main.Drivers;

import java.io.File;
import java.io.Serializable;

import src.main.Main;

import java.awt.*;
import java.io.*;

public class Question extends ScreenElement implements Serializable {

    private String title;
    private String[] texts;
    private int[] positions;
    private String nextQuestion;

    private int currentIndex = 0;
    private int currentStringIndex = -1;
    private int posIndex = 0;
    private int timer = 0;
    private int animTimer = 0;
    private static final int DELAY = 3;
    private double scale;
    private Font font;

    public Question(String title, String[] texts) {
        this.title = title;
        this.texts = texts;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/Fonts/VCR_OSD_MONO_1.001.ttf"));
        } catch (Exception e) {
        }
    }

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

    public void heading(Graphics g, Window w, Color c, Font font) {
        int size = maxFontSize(g, title, w.getWidth() * 15 / 16, w.getHeight() / 2, font);
        Font temp = font.deriveFont(Font.PLAIN, size);
    
        // Calculate the dimensions of the rectangle
        FontMetrics fm = g.getFontMetrics(temp);
        int textWidth = fm.stringWidth(title);
        int textHeight = fm.getHeight();
        int rectWidth = textWidth + 10; // 5 pixels on each side
        int rectHeight = textHeight + 10; // 5 pixels on each side
    
        // Calculate the position of the rectangle
        int rectX = (w.getWidth() - rectWidth) / 2;
        int rectY = w.getHeight() / 3 - rectHeight / 2;
    
        // Draw the white rectangle as the background
        g.setColor(Color.WHITE);
        g.fillRect(rectX, rectY, rectWidth, rectHeight);
    
        // Draw the centered text
        centerString(g, c, title, w.getWidth() / 2, w.getHeight() / 3, w.getWidth(), size, temp);
    }
    

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

    public boolean isClicked(Image image, int x, int y, int[] mouse) {
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        return (mouse[0] > x - width && mouse[0] < x + width && mouse[1] > y - height && mouse[1] < y + height);
    }

    public void addToWindow(Window w) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/Fonts/VCR_OSD_MONO_1.001.ttf"));
        } catch (Exception e) {
        }
        w.addElement(this);
    }

    public void update(Window w, Graphics g) {
        boolean paused = isPaused();

        if (currentStringIndex < 0) {
            heading(g, w, Color.BLACK, font);

            if (paused)
                return;

            timer++;
            if (timer > 120) {
                timer = 0;
                currentStringIndex++;
            }
        } else if (currentStringIndex >= 0 && currentStringIndex < texts.length) {
            double hww = w.getWidth() / 2.0;
            double hwh = w.getHeight() / 2.0;
            scale = Sprite.getTileScale();

            Color c = new Color(53, 45, 82, 255);
            centerBox(g, c, w.getWidth() / 2, w.getHeight() * 4 / 5, (int) scale * 128 + 1, (int) scale * 48 / 2 + 1);

            if (timer % DELAY < DELAY) {
                // TODO fix scaling of font, not efficient but works
                String currentText = texts[currentStringIndex];
                int size = maxFontSize(g, currentText, (int) scale * 128 + 1 - (int) scale * 6 - 5,
                        (int) scale * 24 + 1 - (int) scale * 6 - 5, font);
                Font temp = font.deriveFont(Font.PLAIN, size);
                centerString(g, Color.WHITE, currentText.substring(0, Math.min(currentIndex, currentText.length())),
                        w.getWidth() / 2, w.getHeight() * 31 / 40, (int) scale * 128 + 1, size, temp);
            }

            if (!paused) {
                timer++;
                if (timer % DELAY == 0) {
                    if (currentIndex < texts[currentStringIndex].length() + 50) {
                        currentIndex++;
                    } else {
                        currentIndex = 0;
                        currentStringIndex++;
                    }
                    timer = 0;
                }
                animTimer++;
            }

            int anim = 0;
            if (animTimer <= 60) {
                anim = w.getHeight() * 33 / 40;
            } else {
                anim = w.getHeight() * 133 / 160;
            }
            int[] xPoints = { w.getWidth() / 2 - (int) scale * 2, w.getWidth() / 2,
                    w.getWidth() / 2 + (int) scale * 2 };
            int[] yPoints = { anim, anim + (int) scale * 2, anim };
            g.setColor(Color.WHITE);
            g.fillPolygon(xPoints, yPoints, 3);

            if (paused) return;

            if (animTimer % 120 == 0) {
                animTimer = 0;
            }

            while (w.nextMouse() != null) {
                currentIndex = 0;
                currentStringIndex++;
                timer = 0;
                animTimer = 0;
            }
        }
    }
}
