 

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nate
 * @author Dean Li
 */
public class Location {

    private Character charspot;
    private String image;

    /**
     * Default location
     *
     * @param b
     */
    public Location() {
        charspot = null;
        image = null;
    }

    /**
     * Location builder with only an image
     *
     * @param b the image
     */
    public Location(String image) {
        charspot = null;
        this.image = image;
    }

    /**
     * Location building with just a character
     *
     * @a The character to put in the location
     */
    public Location(Character a) {
        charspot = a;
        image = null;
    }

    /**
     * Builds the location with a character and an image
     *
     * @param a the character at the spot
     * @param b the image at the spot
     */
    public Location(Character a, String b) {
        charspot = a;
        image = b;
    }

    /**
     *
     * @param u
     */
    public String addCharacter(Character a) {

        if (charspot == null) {
            charspot = a;
            return "yes";
        } else {
            return charspot.invokeAction((Player) a);
        }
    }

    /**
     * Sets the image later
     *
     * @param b
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns the character at the spot
     *
     * @return
     */
    public Character getCharacter() {
        return charspot;
    }

    /**
     * Removes a character
     *
     * @return
     */
    public Character removeCharacter() {
        Character a = charspot;
        charspot = null;
        return a;
    }

    public void setCharacter(Character a) {
        charspot = a;
    }

    /**
     * Returns if the charspot is empty
     *
     * @return
     */
    public boolean isEmpty() {
        return charspot == null;
    }

    /**
     * Returns if a character is present
     *
     * @return boolean opposite of isEmpty
     */
    public boolean hasUnit() {
        return !isEmpty();
    }

    /**
     * Returns a location copy of the location
     *
     * @return
     */
    public Location copy() {
        Location copy = this;
        return copy;
    }

    /**
     * Returns the map image at the spot
     */
    public BufferedImage getImage() {
        try {
            BufferedImage img;
            return (BufferedImage) (ImageIO.read(new File("resources/" + image + ".png")));
        } catch (IOException ex) {
            System.out.println("No Loc Image");
        }
        return null;
    }

    @Override
    public String toString() {
        String here = "Does this have an image: " + (getImage() != null)
                + "\nDoes this have a character? " + hasUnit();

        return here;
    }
}