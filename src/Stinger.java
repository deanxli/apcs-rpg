 

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jasonli
 */
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
 * @author LI DING DING
 */
public class Stinger extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/stinger.png";
    private static final int attack = 160;
    private static final int defense = 250;
    private static final int health = 1;
    private static final int gold = 6;
    private static final BufferedImage image = getImageForClass();

    public Stinger(Location a) {
        super(a, image, attack, defense, health, "Stinger", "Water");
    }

    public Stinger(Location a, boolean p) {
        super(a, image, attack, defense, health, "Stinger", p, "Water");
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
