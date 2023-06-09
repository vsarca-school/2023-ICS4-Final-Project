package src.main.Drivers;

import java.awt.*;
import java.io.*;

import src.main.Main;

public class Question extends ScreenElement implements Serializable {

    private String question;
    private String[] answers;
    private int correct;
    private int sx, sy;

    private double scale;
    private Font font;

    public Question(String question, String[] answers, int correct, int x, int y) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
        this.sx = x;
        this.sy = y;
    }

    public boolean isAt(int x, int y) {
        return x == sx && y == sy;
    }

    public static Question fromFile(String file) {
        Question q = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            q = (Question) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return q;
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

    public int maxFontSizeAll(Graphics g, int maxWidth, int maxHeight, Font font) {
        char letter = 65;
        int min = Integer.MAX_VALUE;
        for (String str : answers) {
            String tmp = (char) (letter) + ": " + str;
            String[] lines = tmp.split("\n");
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
            min = Math.min(min, optimalSize);
        }
        return min;
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

        double hww = w.getWidth() / 2.0;
        double hwh = w.getHeight() / 2.0;
        scale = Sprite.getTileScale();

        Color c = new Color(53, 45, 82, 255);
        centerBox(g, c, (int) hww, (int) hwh, (int) (hww * 8 / 5), (int) (hwh * 8 / 5));

        int size = maxFontSize(g, question, (int) (hww * 7 / 5), (int) hwh, font);
        Font temp = font.deriveFont(Font.PLAIN, size);
        centerString(g, Color.WHITE, question, (int) hww, (int) (hwh * 2 / 5), (int) hww * 2, size, temp);

        char letter = 65;
        int i = 0;

        for (String str : answers) {
            String tmp = (char) (letter) + ": " + str;
            size = maxFontSizeAll(g, (int) (hww * 6 / 5), (int) (hwh / 10), font);
            temp = font.deriveFont(Font.PLAIN, size);
            centerString(g, Color.WHITE, tmp, (int) hww, (int) (hwh * 7 / 10 + i), (int) hww * 2, size, temp);
            i += size * 1.25;
            letter++;
        }

        centerImage(g, Sprite.getScaledImage("A"), (int) (hww * 2 / 5), (int) (hwh * 7 / 5));
        centerImage(g, Sprite.getScaledImage("B"), (int) (hww * 4 / 5), (int) (hwh * 7 / 5));
        centerImage(g, Sprite.getScaledImage("C"), (int) (hww * 6 / 5), (int) (hwh * 7 / 5));
        centerImage(g, Sprite.getScaledImage("D"), (int) (hww * 8 / 5), (int) (hwh * 7 / 5));

        int[] mouse;
        while ((mouse = w.nextMouse()) != null) {
            if (isClicked(Sprite.getScaledImage("A"), (int) (hww * 2 / 5), (int) (hwh * 7 / 5), mouse)) {
                if (correct == 1) {
                    // answer
                    System.out.println("correct");
                }
            }
            if (isClicked(Sprite.getScaledImage("B"), (int) (hww * 4 / 5), (int) (hwh * 7 / 5), mouse)) {
                if (correct == 2) {
                    // answer
                    System.out.println("correct");
                }
            }
            if (isClicked(Sprite.getScaledImage("C"), (int) (hww * 6 / 5), (int) (hwh * 7 / 5), mouse)) {
                if (correct == 3) {
                    // answer
                    System.out.println("correct");
                }
            }
            if (isClicked(Sprite.getScaledImage("D"), (int) (hww * 8 / 5), (int) (hwh * 7 / 5), mouse)) {
                if (correct == 4) {
                    // answer
                    System.out.println("correct");
                }
            }
        }
    }
}
