 

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
public class SeaSerpent extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/seaserpent.png";
    private static final int attack = 205;
    private static final int defense = 235;
    private static final int health = 240;
    private static final BufferedImage image = getImageForClass();

    public SeaSerpent(Location a) {
        super(a, image, attack, defense, health, "Sea Serpent", "Water");
    }

    public SeaSerpent(Location a, boolean p) {
        super(a, image, attack, defense, health, "Sea Serpent", p, "Water");
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