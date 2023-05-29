package src.main.Drivers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class Sprite {
    private static Map<String, BufferedImage> tiles = new HashMap<>();
    private static Map<String, BufferedImage> images = new HashMap<>();

    /**
     * Load all images into buffers, and intializes everything
     * For simplicity, all images are shoved into a map with names
     * - Victor
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
                for (int i=0; i<height; i+=16)
                {
                    for (int j=0; j<width; j+=16)
                    {
                        tiles.put(file.getName()+"-"+frame, spritemap.getSubimage(j, i, 16, 16));
                        frame++;
                    }
                }
            }
            // Load individual images
            folder = new File("src/main/Textures/Images");
            for (File file : folder.listFiles()) {
                spritemap = ImageIO.read(file);
                int frame = 0;
                int width = spritemap.getWidth();
                int height = spritemap.getHeight();
                for (int i=0; i<height; i+=16)
                {
                    for (int j=0; j<width; j+=16)
                    {
                        images.put(file.getName()+"-"+frame, spritemap.getSubimage(j, i, 16, 16));
                        frame++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the tile requested
     * @param sprite The key of the tile requested
     * @return The tile image
     */
    public static BufferedImage getTile(String sprite) {
        return tiles.get(sprite);
    }

    /**
     * Returns the image requested
     * @param sprite The key of the image requested
     * @return The image
     */
    public static BufferedImage getImage(String sprite) {
        return images.get(sprite);
    }
}
