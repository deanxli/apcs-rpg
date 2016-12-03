 

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
 * @author 151lid
 */
public class Skeleton extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/skeleton.png";
    private static final int attack = 210;
    private static final int defense = 75;
    private static final int health = 210;
    private static final BufferedImage image = getImageForClass();

    public Skeleton(Location a) {
        super(a, image, attack, defense, health, "Skeleton", "Fire");
    }

    public Skeleton(Location a, boolean p) {
        super(a, image, attack, defense, health, "Skeleton", p, "Fire");
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