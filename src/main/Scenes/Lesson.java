package src.main.Scenes;
import src.main.Drivers.*;
import src.main.Drivers.Window;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Lesson implements Serializable, ScreenElement {
    private JFrame frame;
    private String[] texts;
    private int[] positions;
    private String[] images;
    private int currentIndex = 0;
    private int currentStringIndex = 0;
    private int slideIndex = 0;

    public Lesson(String[] strs, int[] slides, String[] filePaths) {
        texts = strs;
        positions = slides;
        images = filePaths;
    }

    // Custom JPanel for drawing
    class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (currentStringIndex < texts.length) {
                centerString(g, texts[currentStringIndex].substring(0, currentIndex), 400, 250, new Font("Arial", Font.PLAIN, 16));
            }
        }
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

    public void addToWindow(Window w) {
        w.addElement(this);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 1024);

        // Create a custom DrawingPanel and add it to the frame
        DrawingPanel drawingPanel = new DrawingPanel();
        frame.add(drawingPanel);

        frame.setVisible(true);

        // Start the typewriter animation on a separate thread
        SwingWorker<Void, Void> animationWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (currentStringIndex < texts.length) {
                    //TODO add image implementation to the lessons
                    if (slideIndex < positions.length && currentIndex == positions[slideIndex]) {
                        //draw image images[slideIndex];
                        slideIndex++;
                    }
                    if (currentIndex < texts[currentStringIndex].length()) {
                        currentIndex++;
                        SwingUtilities.invokeLater(() -> drawingPanel.repaint()); // Trigger a repaint of the panel on the EDT
                        Thread.sleep(100); // Delay between characters
                    } else {
                        Thread.sleep(500); // Pause after each word
                        currentIndex = 0; // Reset currentIndex
                        currentStringIndex++; // Move to the next string
                    }
                }
                return null;
            }
        };
        animationWorker.execute();
    }

    public void removeFromWindow(Window w) {
        w.removeElement(this);
    }
    @Override
    public void update(Window w, Graphics g) {
    }
}
