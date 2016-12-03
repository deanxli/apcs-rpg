 

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.image.BufferedImage;

/**
 * @author Dean Li The equipment that you can buy
 */
public class Equipment implements Item {

    private String name;
    private int attackboost;
    private int defenseboost;
    private String element;

    /**
     * If the equipment is a weapon, it will be attack boost only, if it's
     * armor, attack boost and defense boost.
     *
     * @param image the image
     * @param name the name
     * @param a the attackboost
     * @param d the defense boost of t
     * @param g the gold value of the equipment
     */
    public Equipment(String name,
            int a, int d, String element) {
        this.name = name;
        attackboost = a;
        defenseboost = d;
        this.element = element;
    }

    /**
     * The name of the weapon
     *
     * @return the name of the weapon
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns how much attack is boosted by
     *
     * @return attackboost
     */
    public int getAttackBoost() {
        return attackboost;
    }

    /**
     * Returns how much defense is boosted by
     *
     * @return defenseboost
     */
    public int getDefenseBoost() {
        return defenseboost;
    }

    /**
     * Returns the elemental alignment of the item
     */
    public String getElement() {
        return element;
    }

    /**
     * The sell value of the items
     */
    public int sellValue() {
        return (attackboost * attackboost + defenseboost * defenseboost) / 13;
    }

    @Override
    public String toString() {
        String info = "";
        if (defenseboost == 0) {
            info = getName() + " +" + attackboost + " A";
        } else if (attackboost == 0) {
            info = getName() + " +" + defenseboost + " D";
        } else {
            info = getName() + " +" + attackboost + " A"
                    + " +" + defenseboost + " D";
        }

        return info;
    }

    @Override
    public int buyValue() {
        return (int) ((sellValue() * 1.10) + 50);
    }
    
    public String stringD()
    {
        String info = "";
        info += "equipment " + name + " " + attackboost + " " + defenseboost + 
                " " + element;
        
        return info;
    }
 
}