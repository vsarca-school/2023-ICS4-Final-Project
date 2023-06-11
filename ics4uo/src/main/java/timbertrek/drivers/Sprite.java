package timbertrek.drivers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import java.util.stream.Collectors;
import javax.imageio.ImageIO;

/**
 * Sprite Class
 * Time spent: 3.2 hours
 *
 * @author Victor Sarca, Felix Zhao (changed file loading)
 * @version 6/7/2023
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
    public static void init() throws URISyntaxException {
        BufferedImage spritemap;
        try {
            // Load tilemaps
            for (String file : getTileFiles()) {
                String path = "textures/tilemaps/" + file;
                spritemap = ImageIO.read(getFileFromResourceAsStream(path));
                int frame = 0;
                int width = spritemap.getWidth();
                int height = spritemap.getHeight();
                for (int i = 0; i < height; i += 16) {
                    for (int j = 0; j < width; j += 16) {
                        tiles.put(file.replaceFirst("[.][^.]+$", "") + "-" + frame,
                                spritemap.getSubimage(j, i, 16, 16));
                        frame++;
                    }
                }
            }
            // Load individual images
            for (String file  : getImageFiles()) {
                String path = "textures/images/" + file;
                images.put(file.replaceFirst("[.][^.]+$", ""), ImageIO.read(getFileFromResourceAsStream(path)));
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
            for (String file : getBackgroundFiles()) {
                String path = "textures/backgrounds/" + file;
                backgrounds.put(file.replaceFirst("[.][^.]+$", ""), ImageIO.read(getFileFromResourceAsStream(path)));
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

    private static List<String> getTileFiles(){
        return Arrays.asList (
                "campfire.png",
                "dirt.png",
                "droplet.png",
                "grass.png",
                "paw.png",
                "player.png",
                "rock.png",
                "sign.png",
                "tent.png",
                "tree.png",
                "wall.png",
                "water.png",
                "wolf.png");
    }
    private static List<String> getImageFiles(){
        return Arrays.asList (
                "a.png",
                "b.png",
                "berries.png",
                "bigrock.png",
                "board.png",
                "bow.png",
                "c.png",
                "continue.png",
                "d.png",
                "dandelion.png",
                "dipper.png",
                "drill.png",
                "entrance.png",
                "fire.png",
                "fish.png",
                "logo.png",
                "menuquit.png",
                "mushroom.png",
                "next.png",
                "paused.png",
                "play.png",
                "quit.png",
                "shirt.png",
                "shovel.png",
                "spider.png",
                "timbertrek.png",
                "vine.png",
                "waterfall.png");
    }

    private static List<String> getBackgroundFiles(){
        return Arrays.asList (
                "apples.png",
                "cave.png",
                "filter.png",
                "map.png",
                "raymond.png",
                "site.png",
                "titlescreen.png",
                "water.png");
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = Sprite.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
