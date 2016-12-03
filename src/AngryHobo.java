 

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
public class AngryHobo extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/angryhobo.png";
    private static final int attack = 7;
    private static final int defense = 3;
    private static final int health = 12;
    private static final BufferedImage image = getImageForClass();

    public AngryHobo(Location a) {
        super(a, image, attack, defense, health, "Angry Hobo", "None");
    }

    public AngryHobo(Location a, boolean p) {
        super(a, image, attack, defense, health, "Angry Hobo", p, "None");
    }

    private static BufferedImage getImageForClass() {
        try {
            return (BufferedImage) (ImageIO.read(new File(fileURL)));
        } catch (IOException a) {
            System.out.println("Error");
        }
// getClass().getResource("/images/Report.png")
        return null;
    }
}