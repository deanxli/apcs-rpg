 

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
public class Salamander extends Enemy {
    // he's cold hearted snake...look intoo his eyes
    // uh oh
    // he's been telling lies.

    private static final String fileURL = "resources/salamander.png";
    private static final int attack = 130;
    private static final int defense = 30;
    private static final int health = 45;
    private static final BufferedImage image = getImageForClass();

    public Salamander(Location a) {
        super(a, image, attack, defense, health, "Salamander", "Fire");
    }

    public Salamander(Location a, boolean p) {
        super(a, image, attack, defense, health, "Salamander", p, "Fire");
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