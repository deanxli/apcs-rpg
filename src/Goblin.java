 

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
 * @author Deanathan / TomTom
 */
public class Goblin extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/Goblin.png";
    private static final int attack = 9;
    private static final int defense = 6;
    private static final int health = 15;
    private static final BufferedImage image = getImageForClass();

    public Goblin(Location a) {
        super(a, image, attack, defense, health, "Goblin", "Earth");
    }

    public Goblin(Location a, boolean p) {
        super(a, image, attack, defense, health, "Goblin", p, "Earth");
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