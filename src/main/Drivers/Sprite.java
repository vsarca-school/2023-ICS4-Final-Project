package src.main.Drivers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

/**
 * <h1>Sprite Class</h1>
 * Time spent: 3.2 hours
 * 
 * @version 1.3
 * @version 6/7/2023
 * @author Victor Sarca
 */
public class Sprite {
    private static Map<String, BufferedImage> tiles = new HashMap<>();
    private static Map<String, BufferedImage> images = new HashMap<>();
    // private static Map<String, BufferedImage> lessonImages = new HashMap<>();
    private static Map<String, BufferedImage> backgrounds = new HashMap<>();
    private static Map<String, BufferedImage> scaledTiles = new HashMap<>();
    private static Map<String, BufferedImage> scaledImages = new HashMap<>();
    // private static Map<String, BufferedImage> scaledLessonImages = new
    // HashMap<>();
    private static Map<String, BufferedImage> scaledBackgrounds = new HashMap<>();
    private static double tileScale, imageScale, lessonImageScale, bgScale;

    /**
     * Victor Sarca - initiates the tilemaps
     */
    public static void init() {
        BufferedImage spritemap;
        try {
            // Load tilemaps
            File folder = new File("src/main/Textures/Tilemaps");
            for (File file : folder.listFiles()) {
                spritemap = ImageIO.read(file);
                int frame = 0;
                int width = spritemap.getWidth();
                int height = spritemap.getHeight();
                for (int i = 0; i < height; i += 16) {
                    for (int j = 0; j < width; j += 16) {
                        tiles.put(file.getName().replaceFirst("[.][^.]+$", "") + "-" + frame,
                                spritemap.getSubimage(j, i, 16, 16));
                        frame++;
                    }
                }
            }
            // Load individual images
            folder = new File("src/main/Textures/Images");
            for (File file : folder.listFiles()) {
                images.put(file.getName().replaceFirst("[.][^.]+$", ""), ImageIO.read(file));
            }
            /*
             * / Load individual lesson images
             * folder = new File("src/main/Textures/LessonImages");
             * for (File file : folder.listFiles()) {
             * lessonImages.put(file.getName().replaceFirst("[.][^.]+$", ""),
             * ImageIO.read(file));
             * }
             */
            // Load individual backgrounds
            folder = new File("src/main/Textures/Backgrounds");
            for (File file : folder.listFiles()) {
                backgrounds.put(file.getName().replaceFirst("[.][^.]+$", ""), ImageIO.read(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Victor Sarca - updates the scale only when the user changes window size
     * 
     * @param windowWidth  current width
     * @param windowHeight current height
     */
    public static void updateScale(int windowWidth, int windowHeight) {
        tileScale = Math.sqrt(windowWidth * windowHeight) / 160.0;
        imageScale = Math.min(windowWidth / 128.0, windowHeight / 96.0);
        bgScale = Math.max(windowWidth / 128.0, windowHeight / 96.0);
        // lessonImageScale = bgScale / 2.0;

        BufferedImage temp;
        Image tkImage;
        int width, height;
        for (Map.Entry<String, BufferedImage> e : tiles.entrySet()) {
            temp = e.getValue();
            width = (int) (tileScale * temp.getWidth()) + 1;
            height = (int) (tileScale * temp.getHeight()) + 1;
            tkImage = temp.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = temp.getGraphics();
            g.drawImage(tkImage, 0, 0, null);
            g.dispose();

            scaledTiles.put(e.getKey(), temp);
        }
        for (Map.Entry<String, BufferedImage> e : images.entrySet()) {
            temp = e.getValue();
            width = (int) (imageScale * temp.getWidth()) + 1;
            height = (int) (imageScale * temp.getHeight()) + 1;
            tkImage = temp.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = temp.getGraphics();
            g.drawImage(tkImage, 0, 0, null);
            g.dispose();

            scaledImages.put(e.getKey(), temp);
        }
        /*
         * for (Map.Entry<String, BufferedImage> e : lessonImages.entrySet()) {
         * temp = e.getValue();
         * width = (int) (lessonImageScale * temp.getWidth()) + 1;
         * height = (int) (lessonImageScale * temp.getHeight()) + 1;
         * tkImage = temp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
         * 
         * temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
         * Graphics g = temp.getGraphics();
         * g.drawImage(tkImage, 0, 0, null);
         * g.dispose();
         * 
         * scaledLessonImages.put(e.getKey(), temp);
         * }
         */
        for (Map.Entry<String, BufferedImage> e : backgrounds.entrySet()) {
            temp = e.getValue();
            width = (int) (bgScale * temp.getWidth()) + 1;
            height = (int) (bgScale * temp.getHeight()) + 1;
            tkImage = temp.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = temp.getGraphics();
            g.drawImage(tkImage, 0, 0, null);
            g.dispose();

            scaledBackgrounds.put(e.getKey(), temp);
        }
    }

    /**
     * Radin Ahari - returns tile scale
     * 
     * @return tile scale
     */
    public static double getTileScale() {
        return tileScale;
    }

    /**
     * Radin Ahari - returns image scale
     * 
     * @return image scale
     */
    public static double getImageScale() {
        return imageScale;
    }

    /**
     * Felix Zhao - returns lesson image scale
     * 
     * @return image scale
     */
    public static double getLessonImageScale() {
        return lessonImageScale;
    }

    /**
     * Felix Zhao - returns background image scale
     * 
     * @return image scale
     */
    public static double getBgScale() {
        return bgScale;
    }

    /**
     * Victor Sarca - prints all tiles and images, debugging
     */
    public static void print() {
        System.out.println("Tiles:");
        for (String s : tiles.keySet())
            System.out.print(s + " ");
        System.out.println();
        System.out.println("Images:");
        for (String s : images.keySet())
            System.out.print(s + " ");
        System.out.println();
        System.out.println("Backgrounds:");
        for (String s : backgrounds.keySet())
            System.out.print(s + " ");
        System.out.println();
    }

    /**
     * Victor Sarca - Returns the tile requested
     * 
     * @param sprite The key of the tile requested
     * @return The tile image
     */
    public static BufferedImage getScaledTile(String sprite) {
        return scaledTiles.get(sprite);
    }

    /**
     * Victor Sarca - Returns the image requested
     * 
     * @param sprite The key of the image requested
     * @return The image
     */
    public static BufferedImage getScaledImage(String sprite) {
        return scaledImages.get(sprite);
    }

    /**
     * Victor Sarca - Returns the background requested
     * 
     * @param sprite The key of the image requested
     * @return The image
     *         /
     *         public static BufferedImage getScaledLessonImage(String sprite) {
     *         return scaledLessonImages.get(sprite);
     *         }
     */

    /**
     * Victor Sarca - Returns the background requested
     * 
     * @param sprite The key of the image requested
     * @return The image
     */
    public static BufferedImage getScaledBackground(String sprite) {
        return scaledBackgrounds.get(sprite);
    }
}
