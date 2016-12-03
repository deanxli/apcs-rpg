 

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jasonli This one's Nate
 */
import javax.swing.JOptionPane;

public class Play {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        JOptionPane.showMessageDialog(null, "First...there was nothing.");
        JOptionPane.showMessageDialog(null, "From this nothing, Svetty emerged."
                + "\nHe created code, the language of the world. ");
        JOptionPane.showMessageDialog(null, "From this code he created the"
                + " four elements, held delicately in balance.");
        JOptionPane.showMessageDialog(null, "Over time however, he began "
                + "to become annoyed by the students in his class.\nHe locked"
                + " away the four elements and hid himself.");
        JOptionPane.showMessageDialog(null, "For a long time the world of period"
                + " six was in chaos. Test averages were in shambles."
                + "\nStudents wanted to go outside. Unlocked computers led "
                + "to chaos.");
        JOptionPane.showMessageDialog(null, "From this chaos emerged a hero."
                + "\nYOU.\nGo brave hero, and rage, rage against the machine.");
        JOptionPane.showMessageDialog(null, "Explore the world and unlock the"
                + " secrets of code.");
        Game g = new Game();
        g.run();
    }
}
