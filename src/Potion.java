 

import java.awt.image.BufferedImage;

/**
 * @author Dean Li
 */
public class Potion implements Item {

    private int heal;
    private String name;

    /**
     * Constructs a potion with the amount it will heal
     *
     * @param name
     * @param heal
     */
    public Potion(String name, int heal) {
        this.name = name;
        this.heal = heal;
    }

    /**
     * Returns the name of the potion Probably a generic color
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns how much the potion will heal
     *
     * @return the int value for health healed
     */
    public int getHeal() {
        return heal;
    }

    public String toString() {
        return name + " +" + heal + "hp";
    }

    public int sellValue() {
        return heal;
    }

    public int buyValue() {
        return (int) (sellValue() * 1.25);
    }

    public BufferedImage getImage() {
        return null;
    }

    public String stringD()
    {
        String info = "";
        info += "potion " + name + " " + heal;
        return info;
    }
}
