package src.main.Scenes;
import src.main.Drivers.*;
import src.main.Drivers.Window;

import java.awt.*;
import javax.swing.*;
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

    public void centerImage(Graphics g, Window w, Image image) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
    
        int x = (w.getWidth() - imageWidth) / 2;
        int y = (w.getHeight() - imageHeight) / 2;
    
        g.drawImage(image, x, y, null);
    }

    public void addToWindow(Window w) {
        w.addElement(this);
    }
    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }

    public void update(Window w, Graphics g) {
        if (posIndex < positions.length && positions[posIndex] > currentStringIndex) {
            centerImage(g, w, Sprite.getImage(images[posIndex]));
        }
        else {
            posIndex++;
        }
        if (currentStringIndex < texts.length && timer % DELAY < DELAY) {
            centerString(g, texts[currentStringIndex].substring(0, Math.min(currentIndex, texts[currentStringIndex].length())), 400, 250, new Font("Arial", Font.PLAIN, 16));
        }
        timer++;
        if (timer % DELAY == 0) {
            if (currentStringIndex < texts.length && currentIndex < texts[currentStringIndex].length() + 50) {
                currentIndex++;
            }
            else {
                currentIndex = 0;
                currentStringIndex++;
            }
            timer = 0;
        }
    }
}
