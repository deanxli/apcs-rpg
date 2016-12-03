 

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * Working on the game from home
 */
import java.awt.image.BufferedImage;

/**
 * Abstract class Character
 *
 * @author Dean Li A character abstract class that will be easier for the map to
 * call certain methods on, displaying the correct image at the correct
 * location;
 */
public abstract class Character {

    private Location position;
    private BufferedImage image;
    private boolean friendly;

    public Character(Location a, BufferedImage b, boolean c) {
        this.position = a;
        this.image = b;
        this.friendly = c;
    }

    /**
     * Returns the location of the character
     *
     * @Location (likely two coordinates)
     */
    public Location getPosition() {
        return position;
    }

    /**
     * A method to change the position
     */
    public String changePosition(Location a) {
        if (assertLocation(a).equalsIgnoreCase("yes")) {
            this.getPosition().removeCharacter();
            position = a;
            return "yes";
        }

        return assertLocation(a);
    }

    /**
     * Returns an image for the map to display
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Allows you to change an image
     */
    public void setImage(BufferedImage a) {
        image = a;
    }

    /**
     * Tells whether the character is friendly or not
     *
     * @return
     */
    public boolean getStatus() {
        return friendly;
    }

    /**
     * What to do when the character is stepped on
     */
    public abstract String invokeAction(Player a);

    /**
     * Makes sure the position has the character
     */
    public String assertLocation(Location a) {
        return a.addCharacter(this);
    }
}