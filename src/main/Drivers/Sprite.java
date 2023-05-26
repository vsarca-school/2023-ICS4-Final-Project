package src.main.Drivers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class Sprite {
    private static Map<String, BufferedImage> sprites = new HashMap<>();

    /**
     * Load all images into buffers, and intializes everything
     * For simplicity, all images are shoved into a map with names
     * - Victor
     */
    public static void init() {
        BufferedImage spritemap;
        try {
            File folder = new File("src/main/Textures");
            for (File file : folder.listFiles()) {
                spritemap = ImageIO.read(file);
                int frame = 0;
                int width = spritemap.getWidth();
                int height = spritemap.getHeight();
                for (int i=0; i<height; i+=16)
                {
                    for (int j=0; j<width; j+=16)
                    {
                        sprites.put(file.getName()+"-"+frame, spritemap.getSubimage(j, i, 16, 16));
                        frame++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the sprite requested
     * @param sprite The key of the sprite requested
     * @return The sprite image
     */
    public static BufferedImage getSprite(String sprite) {
        return sprites.get(sprite);
    }
}
