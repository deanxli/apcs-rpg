 

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
public class Paladin extends Enemy {

    private static final String fileURL = "resources/paladin.png";
    private static final int attack = 320;
    private static final int defense = 350;
    private static final int health = 300;
    private static final BufferedImage image = getImageForClass();

    public Paladin(Location a) {
        super(a, image, attack, defense, health, "Paladin", "Air");
    }

    public Paladin(Location a, boolean p) {
        super(a, image, attack, defense, health, "Paladin", p, "Air");
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