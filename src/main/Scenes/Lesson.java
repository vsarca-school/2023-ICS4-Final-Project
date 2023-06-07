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
    private int animTimer;
    private final int DELAY = 3;
    private double scale;
    private Font font;

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

    public void centerImage(Graphics g, Image image, int x, int y) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        int a = x - imageWidth / 2;
        int b = y - imageHeight / 2;

        g.drawImage(image, a, b, null);
    }

    public void centerBox(Graphics g, Color c, int x, int y, int w, int h) {
        int startX = x - (w / 2);
        int startY = y - (h / 2);
        g.setColor(c);
        g.fillRect(startX, startY, w, h);
        g.setColor(Color.WHITE);
        g.fillRect(startX + (int) scale / 8, startY + (int) scale / 8, w - (int) scale / 4, h - (int) scale / 4);
        g.setColor(c);
        g.fillRect(startX + (int) scale * 3 / 16, startY + (int) scale * 3 / 16, w - (int) scale * 3 / 8,
                h - (int) scale * 3 / 8);
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

    public void addToWindow(Window w) {
        w.addElement(this);
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }

    public void update(Window w, Graphics g) {
        if (currentStringIndex < texts.length) {
            double hww = w.getWidth() / 2.0;
            double hwh = w.getHeight() / 2.0;
            scale = Math.sqrt(hww * hwh) / 5;

            if (posIndex < positions.length && positions[posIndex] > currentStringIndex) {
                centerImage(g, Sprite.getImage(images[posIndex]), w.getWidth() / 2, w.getHeight() / 2);
            } else {
                posIndex++;
            }

            Color c = new Color(53, 45, 82, 255);
            centerBox(g, c, w.getWidth() / 2, w.getHeight() * 4 / 5, (int) scale * 8 + 1, (int) scale * 3 / 2 + 1);

            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/Fonts/VCR_OSD_MONO_1.001.ttf"));
            } catch (Exception e) {
            }

            if (timer % DELAY < DELAY) {
                // TODO fix scaling of font, not efficient but works
                String currentText = texts[currentStringIndex];
                int size = maxFontSize(g, currentText, (int) scale * 8 + 1 - (int) scale * 3 / 8 - 5,
                        (int) scale * 3 / 2 + 1 - (int) scale * 3 / 8 - 5, font);
                Font temp = font.deriveFont(Font.PLAIN, size);
                centerString(g, Color.WHITE, currentText.substring(0, Math.min(currentIndex, currentText.length())),
                        w.getWidth() / 2, w.getHeight() * 31 / 40, (int) scale * 8 + 1, size, temp);
            }

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

            int anim = 0;
            if (animTimer <= 60) {
                anim = w.getHeight() * 33 / 40;
            } else {
                anim = w.getHeight() * 133 / 160;
            }
            int[] xPoints = { w.getWidth() / 2 - (int) scale / 8, w.getWidth() / 2,
                    w.getWidth() / 2 + (int) scale / 8 };
            int[] yPoints = { anim, anim + (int) scale / 8, anim };
            g.setColor(Color.WHITE);
            g.fillPolygon(xPoints, yPoints, 3);

            if (animTimer % 120 == 0) {
                animTimer = 0;
            }

            while (w.nextMouse() != null) {
                currentIndex = 0;
                currentStringIndex++;
                timer = 0;
                animTimer = 0;
            }
        } else {
            centerImage(g, Sprite.getImage("next").getScaledInstance((int)scale*19 / 8, (int)scale*13 / 16, Image.SCALE_SMOOTH), w.getWidth()/2, w.getHeight()/2);
        }
    }
}
