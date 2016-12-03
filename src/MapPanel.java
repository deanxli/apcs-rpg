 

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Nate W
 */
public class MapPanel extends JPanel {

    private Map m;
    private Point ulc;

    public MapPanel(Map m, Point ulc) {
        super();
        this.m = m;
        this.ulc = ulc;
    }

    public void move(Point p) {
        ulc = p;
    }

    public void paintComponents(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = ulc.x; i < ulc.x + 12; i++) {
            for (int j = ulc.y; j < ulc.y + 12; j++) {
                Location loc = m.getLocation(j, i);
                g2.drawImage(loc.getImage(), (i - ulc.x) * 40, (j - ulc.y) * 40, null);
            }
        }
        for (int i = ulc.x; i < ulc.x + 12; i++) {
            for (int j = ulc.y; j < ulc.y + 12; j++) {
                if (m.getLocation(j, i).hasUnit()) {
                    g2.drawImage(m.getLocation(j, i).getCharacter().getImage(),
                            (i - ulc.x) * 40, (j - ulc.y) * 40, null);
                }
            }
        }
    }

    public void update(Graphics g, ArrayList<Point> points) {
        Graphics2D g2 = (Graphics2D) g;
        for (Point p : points) {
            if (m.checkLoc(p.y, p.x)) {
                if (m.getMap()[p.y][p.x].hasUnit()) {
                    BufferedImage a = m.getMap()[p.y][p.x].getCharacter().getImage();
                    BufferedImage b = m.getMap()[p.y][p.x].getImage();
                    BufferedImage c = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
                    Graphics g3 = c.getGraphics();
                    g3.drawImage(b, 0, 0, null);
                    g3.drawImage(a, 0, 0, null);
                    g2.drawImage(c, (p.x - ulc.x) * 40, (p.y - ulc.y) * 40, null);
                } else {
                    g2.drawImage(m.getMap()[p.y][p.x].getImage(), (p.x - ulc.x) * 40, (p.y - ulc.y) * 40, null);
                }
            }
        }
    }
}
/**
 * Allons-y
 */
