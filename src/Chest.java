 

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
 * @author Dean
 */
public class Chest extends Character {

    private Item item;
    private int gold;
    private int open;

    public Chest(Item item, Location a) {
        super(a, getImageForClass("resources/closedchest.png"), true);
        super.assertLocation(a);
        this.item = item;
        open = 0;
    }

    public Chest(int n, Location a) {
        super(a, getImageForClass("resources/closedchest.png"), true);
        super.assertLocation(a);
        gold = n;
        open = 0;
    }

    public Chest(Item item, int gold, Location a) {
        super(a, getImageForClass("resources/closedchest.png"), true);
        super.assertLocation(a);
        this.item = item;
        this.gold = gold;
        open = 0;
    }

    private static BufferedImage getImageForClass(String fileURL) {
        try {
            return ((BufferedImage) (ImageIO.read(new File(fileURL))));
        } catch (IOException a) {
            System.out.println("Error");
        }

        return null;
    }

    public int getGold() {
        return gold;
    }

    public Item getItem() {
        return item;
    }

    public String invokeAction(Player a) {
        if (open < 2) {
            String answer = "chest";
            super.setImage(getImageForClass("resources/openchest.png"));
            open++;
            return answer;
        } else {
            return "wall";
        }
    }
}
