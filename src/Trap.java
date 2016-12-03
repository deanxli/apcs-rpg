 

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jasonli
 */
public class Trap extends Character {

    public int times = 0;

    public Trap(Location a) {
        super(a, getImageForClass("resources/leaftrap.png"), true);
        if ((int) (Math.random() * 3) == 0) {
            super.setImage(getImageForClass("resources/closedchest.png"));
        }
        super.assertLocation(a);
    }

    public String invokeAction(Player a) {
        times++;

        if (times == 2) {
            super.setImage(getImageForClass("resources/hole.png"));
            return "trap";
        } else if (times == 4) {
            this.getPosition().removeCharacter();
            return "traptwo";
        }

        return "what";
    }

    private static BufferedImage getImageForClass(String url) {
        try {
            return (BufferedImage) (ImageIO.read(new File(url)));
        } catch (IOException a) {
            System.out.println("Error");
        }

        return null;
    }
}
