 

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nate
 */
public class Merchant extends Character {

    private Item[] items;
    private String name;

    public Merchant(Location a, String name, Item[] items) {
        super(a, getCharacterImage(), true);
        super.assertLocation(a);
        this.items = items;
        this.name = name;
    }

    public static BufferedImage getCharacterImage() {
        BufferedImage img = null;
        try {
            return (BufferedImage) (ImageIO.read(new File("resources/merchant.png")));
        } catch (IOException ex) {
            System.out.println("No Merchant Pic");
        }
        return img;
    }

    public Item[] getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    @Override
    public String invokeAction(Player a) {

        return "merchant";
    }
}
/**
 * Allons-y
 */
