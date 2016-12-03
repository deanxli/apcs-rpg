 

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.image.BufferedImage;

/**
 * @author Dean Li The general implementing of items
 */
public interface Item {

    /**
     * Returns the name of the item
     */
    public String getName();

    public int sellValue();

    public int buyValue();
}