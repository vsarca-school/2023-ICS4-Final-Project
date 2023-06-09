package src.main.Drivers;

import java.awt.*;
import java.io.*;

import src.main.Main;

/**
 * <h1>Lesson Class</h1>
 * Time spent: 6.5 hours
 * 
 * @version 1.3
 * @version 6/8/2023
 * @author Victor Sarca, Felix Zhao
 */
public class Lesson extends ScreenElement implements Serializable {
    private String title;
    private String[] texts;
    private int[] positions;
    private String background;
    private String[] images;
    private String nextLesson;

    private int currentIndex = 0;
    private int currentStringIndex = -1;
    private int posIndex = 0;
    private int timer = 0;
    private int animTimer = 0;
    private static final int DELAY = 3;
    private double scale;
    private Font font;

    /**
     * Felix Zhao - Lesson constructor
     * 
     * @param title      heading of the lesson
     * @param texts      all text used for the lesson
     * @param slides     the positions used for the images
     * @param background background image
     * @param images     the images used for
     * @param nextLesson the next lesson the current lesson tranisitions to
     */
    public Lesson(String title, String[] texts, int[] slides, String background, String[] images, String nextLesson) {
        this.title = title;
        this.texts = texts;
        this.positions = slides;
        this.background = background;
        this.images = images;
        this.nextLesson = nextLesson;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/Fonts/VCR_OSD_MONO_1.001.ttf"));
        } catch (Exception e) {
        }
    }

    /**
     * Vicotr Sarca - Loads lesson from file
     * 
     * @param file the file path to load the lesson
     * @return lesson from the file
     */
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

    /**
     * Felix Zhao - Next Lesson
     * 
     * @return the next lesson from the current one
     */
    public Lesson nextLesson() {
        if (nextLesson != null)
            return Lesson.fromFile(nextLesson);
        return null;
    }

    /**
     * Felix Zhao - Draws a centered image based off of the coordinates
     * 
     * @param g     graphics window
     * @param image image drawn
     * @param x     coordinate
     * @param y     coordinate
     */
    public void centerImage(Graphics g, Image image, int x, int y) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        int a = x - imageWidth / 2;
        int b = y - imageHeight / 2;

        g.drawImage(image, a, b, null);
    }

    /**
     * Felix Zhao - Draws a box with outlines centered around x and y coordinates
     * 
     * @param g graphics window
     * @param c color of box
     * @param x coordinate
     * @param y coordinate
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
     * Felix Zhao - Centers a string based off of x and y coordinates, update:
     * multiple lines implemented
     * 
     * @param g          graphics window
     * @param c          colo of the text
     * @param text       to display
     * @param x          coordinate
     * @param y          coordinate
     * @param maxWidth   max width of the text
     * @param lineHeight height of a single line
     * @param font       font of the text
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
     * Felix Zhao - Draws a large title text
     * 
     * @param g    graphics window
     * @param w    window object
     * @param c    color of text
     * @param font size of font
     */
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

    /**
     * Felix Zhao - finds the max font size based off of a given width and height,
     * binary search
     * 
     * @param g         graphics window
     * @param text      text used to test
     * @param maxWidth
     * @param maxHeight
     * @param font      font used to check
     * @return the maximum font size
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
     * Victor Sarca - checks if an image is clicked
     * 
     * @param image image to check
     * @param x     coordinate
     * @param y     coordinate
     * @param mouse positions to check
     * @return if the image was clicked
     */
    public boolean isClicked(Image image, int x, int y, int[] mouse) {
        int width = image.getWidth(null) / 2;
        int height = image.getHeight(null) / 2;
        return (mouse[0] > x - width && mouse[0] < x + width && mouse[1] > y - height && mouse[1] < y + height);
    }

    /**
     * Victor Sarca - adds the object to the window class
     * 
     * @param w window
     */
    public void addToWindow(Window w) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/Fonts/VCR_OSD_MONO_1.001.ttf"));
        } catch (Exception e) {
        }
        w.addElement(this);
    }

    /**
     * Felix Zhao - the update loop for the lesson class, handles all of the logic
     * 
     * @param g graphics window
     * @param w window object
     */
    public void update(Window w, Graphics g) {
        boolean paused = isPaused();

        centerImage(g, Sprite.getScaledBackground(background), w.getWidth() / 2, w.getHeight() / 2);

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

            if (posIndex < positions.length && positions[posIndex] > currentStringIndex) {
                if (images[posIndex].equals("droplet")) {
                    if (animTimer <= 40) {
                        centerImage(g, Sprite.getScaledTile("droplet-0"), (int) hww, (int) hwh * 6 / 5);
                    } else if (animTimer > 40 && animTimer <= 80) {
                        centerImage(g, Sprite.getScaledTile("droplet-1"), (int) hww, (int) hwh * 6 / 5);
                    } else {
                        centerImage(g, Sprite.getScaledTile("droplet-2"), (int) hww, (int) hwh * 6 / 5);
                    }
                } else {
                    centerImage(g, Sprite.getScaledImage(images[posIndex]), (int) hww, (int) hwh * 4 / 5);
                }
            } else if (!paused) {
                posIndex++;
            }

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

            if (paused)
                return;

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
            double hww = w.getWidth() / 2.0;
            double hwh = w.getHeight() / 2.0;
            centerImage(g, Sprite.getScaledImage("next"), (int) hww, (int) hwh);

            if (paused)
                return;

            int[] mouse;
            while ((mouse = w.nextMouse()) != null) {
                if (isClicked(Sprite.getScaledImage("next"), (int) hww,
                        (int) hwh, mouse)) {
                    Main.changeScene(3);
                }
            }
        }
    }
}
