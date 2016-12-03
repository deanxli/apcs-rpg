 

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nate
 */
public class Door extends Location {

    Door other;
    Map m;
    Point loc;
    boolean open;
    Enemy key;

    public Door(Map m, Point p) {
        super(null, "door");
        this.m = m;
        m.setLocation(p.y, p.x, this);
        loc = p;
        open = true;
    }

    public Door(Map m, Point p, Enemy e) {
        super(null, "door");
        this.m = m;
        loc = p;
        m.setLocation(p.y, p.x, this);
        key = e;
        open = false;
    }

    public Door getOther() {
        return other;
    }

    public Point getLoc() {
        return loc;
    }

    public Map getMap() {
        return m;
    }

    public Enemy getKey() {
        return key;
    }

    public boolean isOpen() {
        if (!open) {
            open = key.isDead();
        }
        return open;
    }

    public void setOther(Door other) {
        this.other = other;
    }

    public void lock(Enemy unlock) {
        this.key = unlock;
    }
}
/**
 * Allons-y
 */
