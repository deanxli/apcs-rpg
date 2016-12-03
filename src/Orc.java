 

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
 * @author TOMISAWSUM
 */
public class Orc extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/Orc.png";
    private static final int attack = 40;
    private static final int defense = 35;
    private static final int health = 40;
    private static final BufferedImage image = getImageForClass();

    public Orc(Location a) {
        super(a, image, attack, defense, health, "Orc", "Earth");
    }

    public Orc(Location a, boolean p) {
        super(a, image, attack, defense, health, "Orc", p, "Earth");
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