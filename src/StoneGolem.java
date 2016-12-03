 

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
public class StoneGolem extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/stonegolem.png";
    private static final int attack = 20;
    private static final int defense = 35;
    private static final int health = 44;
    private static final BufferedImage image = getImageForClass();

    public StoneGolem(Location a) {
        super(a, image, attack, defense, health, "Stone Golem", "Earth");
    }

    public StoneGolem(Location a, boolean p) {
        super(a, image, attack, defense, health, "Stone Golem", p, "Earth");
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