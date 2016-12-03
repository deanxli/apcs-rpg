 

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dean
 */
public class Spider extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/spider.png";
    private static final int attack = 70;
    private static final int defense = 130;
    private static final int health = 70;
    private static final BufferedImage image = getImageForClass();

    public Spider(Location a) {
        super(a, image, attack, defense, health, "Spider", "Fire");
    }

    public Spider(Location a, boolean p) {
        super(a, image, attack, defense, health, "Spider", p, "Fire");
    }

    private static BufferedImage getImageForClass() {
        try {
            return (BufferedImage) (ImageIO.read(new File(fileURL)));
        } catch (IOException a) {
            System.out.println("Error");
        }

        return null;
    }
}