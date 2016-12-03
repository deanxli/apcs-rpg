 

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
public class Shark extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/shark.png";
    private static final int attack = 203;
    private static final int defense = 180;
    private static final int health = 170;
    private static final int gold = 6;
    private static final BufferedImage image = getImageForClass();

    public Shark(Location a) {
        super(a, image, attack, defense, health, "Shark", "Water");
    }

    public Shark(Location a, boolean p) {
        super(a, image, attack, defense, health, "Shark", p, "Water");
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