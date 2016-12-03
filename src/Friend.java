 

import java.awt.image.BufferedImage;

/**
 * @author Dean Li Extends the character class: can't fight them, but can open
 * dialogue
 */
public class Friend extends Character {

    private String dialogue;
    private String dialoguetwo;
    private String dialoguethree;
    private String hiddendialogue = "Easter egg found!\nPress ; for 100 gold.";
    private int hiddencount;
    private int count;

    public Friend(Location a, BufferedImage b, String c) {
        super(a, b, true);
        super.assertLocation(a);
        dialogue = c;
        count = Integer.MIN_VALUE;
    }

    public Friend(Location a, BufferedImage b, String c, String d) {
        super(a, b, true);
        super.assertLocation(a);
        dialogue = c;
        dialoguetwo = d;
        count = 0;
    }

    public Friend(Location a, BufferedImage b, String c, String d, String e) {
        super(a, b, true);
        super.assertLocation(a);
        dialogue = c;
        dialoguetwo = d;
        dialoguethree = e;
        count = 0;
    }

    public String invokeAction(Player a) {
        return "Friend";
    }

    public String getDialogue() {
        hiddencount++;

        if (hiddencount == 20) {
            return hiddendialogue;
        }
        if (count <= 0 || dialoguetwo == null) {
            count++;
            return dialogue;
        }

        if (count == 1) {
            if (dialoguethree == null) {
                count--;
            } else {
                count++;
            }

            return dialoguetwo;
        }

        if (count == 2) {
            count = 0;
            return dialoguethree;
        }

        return null;
    }

    /**
     * Returns the location of the character
     *
     * @Location (likely two coordinates)
     */
    @Override
    public Location getPosition() {
        return super.getPosition();
    }

    /**
     * Returns an image for the map to display
     */
    public BufferedImage getImage() {
        return super.getImage();
    }
}