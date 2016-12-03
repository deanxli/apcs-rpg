 

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
 * @author TOMISAWSUM
 */
public class Fairy extends Enemy {
    // he's SOOOO ANGRY

    private static final String fileURL = "resources/fairy.png";
    private static final int attack = 295;
    private static final int defense = 165;
    private static final int health = 100;
    private static final int gold = 6;
    private static final BufferedImage image = getImageForClass();

    public Fairy(Location a) {
        super(a, image, attack, defense, health, "Fairy", "Air");
    }

    public Fairy(Location a, boolean p) {
        super(a, image, attack, defense, health, "Fairy", p, "Air");
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