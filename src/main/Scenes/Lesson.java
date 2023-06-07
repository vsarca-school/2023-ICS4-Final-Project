package src.main.Scenes;
import src.main.Drivers.*;
import src.main.Drivers.Window;

import java.awt.*;
import java.io.*;

public class Lesson implements Serializable, ScreenElement {
    private String[] texts;
    private int[] positions;
    private String[] images;
    private int currentIndex = 0;
    private int currentStringIndex = 0;
    private int posIndex = 0;
    private int timer;
    private final int DELAY = 3;

    public Lesson(String[] texts, int[] slides, String[] images) {
        this.texts = texts;
        this.positions = slides;
        this.images = images;
    }
    
    public static Lesson fromFile(String file) {
        Lesson lesson = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            lesson = (Lesson) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    public void centerString(Graphics g, String text, int x, int y, Font font) {
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        int startX = x - (textWidth / 2);
        int startY = y + (textHeight / 2) - metrics.getDescent();
        g.drawString(text, startX, startY);
    }

    public void centerImage(Graphics g, Window w, Image image, int x, int y) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
    
        int a = x - imageWidth / 2;
        int b = y - imageHeight / 2;

        g.drawImage(image, a, b, null);
    }

    public void centerRectangle(Graphics g, Color c, int x, int y, int w, int h) {
        int startX = x - (w / 2);
        int startY = y - (h / 2);
        g.setColor(c);
        g.drawRect(startX, startY, w, h);
        g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 100));
        g.fillRect(startX + 1, startY + 1, w - 1, h - 1);
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }
    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }

    public void centerString(Graphics g, String text, int x, int y, int maxWidth, int lineHeight, Font font) {
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
    
        String[] lines = text.split("\n");
        int lineY = y - ((lines.length * lineHeight) / 2) + metrics.getAscent();
    
        for (String line : lines) {
            int lineX = x - (metrics.stringWidth(line) / 2);
            g.drawString(line, lineX, lineY);
            lineY += lineHeight;
        }
    }
    
    public void update(Window w, Graphics g) {
        if (posIndex < positions.length && positions[posIndex] > currentStringIndex) {
            centerImage(g, w, Sprite.getImage(images[posIndex]), w.getWidth() / 2, w.getHeight() / 2);
        } else {
            posIndex++;
        }
    
        centerRectangle(g, Color.BLACK, w.getWidth() / 2, w.getHeight() * 4 / 5, w.getWidth() * 4 / 5, w.getHeight() / 5);
    
        if (currentStringIndex < texts.length && timer % DELAY < DELAY) {
            String currentText = texts[currentStringIndex];
            int maxWidth = w.getWidth() * 3 / 4;
            int lineHeight = 20;
            centerString(g, currentText.substring(0, Math.min(currentIndex, currentText.length())), w.getWidth() / 2, w.getHeight() * 4 / 5, maxWidth, lineHeight, new Font("Arial", Font.PLAIN, 16));
        }
        timer++;
        if (timer % DELAY == 0) {
            if (currentStringIndex < texts.length && currentIndex < texts[currentStringIndex].length() + 50) {
                currentIndex++;
            } else {
                currentIndex = 0;
                currentStringIndex++;
            }
            timer = 0;
        }
    }
}
