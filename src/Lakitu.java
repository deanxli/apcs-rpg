 

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 151lid
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
 * @author Deanathan
 */
public class Lakitu extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/Lakitu.png";
    private static final int attack = 180;
    private static final int defense = 350;
    private static final int health = 30;
    private static final BufferedImage image = getImageForClass();

    public Lakitu(Location a) {
        super(a, image, attack, defense, health, "Lakitu", "Air");
    }

    public Lakitu(Location a, boolean p) {
        super(a, image, attack, defense, health, "Lakitu", p, "Air");
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
