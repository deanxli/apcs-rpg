 

import java.awt.image.BufferedImage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 151lid
 */
public class Wall extends Character {

    private int easteregg = 0;

    public Wall(Location a, BufferedImage b) {
        super(a, b, true);
        super.assertLocation(a);
    }

    public Wall(Location a, BufferedImage b, boolean c) {
        super(a, b, true);
    }

    @Override
    public String invokeAction(Player a) {
        easteregg++;
        if (easteregg == 148) {
            return "easteregg";
        }

        return "wall";
    }
}
