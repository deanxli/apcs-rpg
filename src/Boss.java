 

import java.awt.image.BufferedImage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jasonli
 */
public class Boss extends Enemy {

    // he's SOOOO ANGRY
    private Equipment[] loot;
    private String mess;
    private String mess2;
    private int healfactor;

    public Boss(Location a, BufferedImage b,
            int attack, int defense, int health, Equipment[] loot, String name,
            String element, String mess, String mess2, int hf) {
        super(a, b, attack, defense, health, name, element);
        this.mess = mess;
        this.mess2 = mess2;
        healfactor = hf;
        this.loot = loot;

    }

    /**
     * Returns additional loot that you win from bosses
     */
    public Equipment[] getLoot() {
        return loot;
    }

    /**
     * First attack message
     *
     * @return
     */
    public String getMess() {
        int chance = (int) (Math.random() * 2);

        if (chance == 0) {
            return mess;
        }
        if (chance == 1) {
            return mess2;
        }

        return null;
    }

    /**
     * The amount the boss can heal
     *
     * @return
     */
    public int getHealFactor() {
        return healfactor;
    }

    public void heal() {
        super.addHealth(healfactor);
    }
}
