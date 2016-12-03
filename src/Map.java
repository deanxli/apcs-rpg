 

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Dean Li maps and stuff
 */
public class Map {

    private int cheatcount = 0;
    private Location[][] map;
    private Character storedCharacter = null;

    /**
     * Generic constructor for a map
     */
    public Map() {
        map = new Location[12][12];
        // default size
    }

    /**
     * Takes in a Location array
     */
    public Map(Location[][] a) {
        map = a;
    }

    /**
     * A map of given height and width
     *
     * @param height
     * @param width
     */
    public Map(int height, int width, String b) {
        map = new Location[height][width];
        backgroundBuild(b, map);
    }

    /**
     * FIND ME THE PLAYER
     */
    public Point findPlayer() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].hasUnit()) {
                    Character b = map[i][j].getCharacter();
                    if (b instanceof Player) {
                        return new Point(j, i);
                    }
                }
            }
        }

        return null;
    }

    public Location findDoor() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] instanceof Door) {
                    return map[i][j];
                }
            }
        }

        return null;
    }

    /**
     * Returns the location at the given x and y coordinates
     *
     * @param y
     * @param x
     * @return the location in question
     */
    public Location getLocation(int y, int x) {
        if (checkLoc(y, x)) {
            return map[y][x];
        }

        return null;
    }

    /**
     * Gets and sets a location
     */
    public void setLocation(int y, int x, Location b) {
        if (checkLoc(y, x)) {
            map[y][x] = b;
        }

    }

    /**
     * Whether the location is valid
     *
     * @param the location's coordinates
     * @return whether it exists
     */
    public boolean checkLoc(int y, int x) {
        int width = map.length;
        int height = map[0].length;

        if (y < 0 || y >= height || x < 0 || x >= width) {
            return false;
        }

        return true;
    }

    public boolean hasUnits(Character a) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].getCharacter() != null) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns the location 2d array
     *
     * @return the location[][];
     */
    public Location[][] getMap() {
        return map;
    }

    /**
     * Background builder - takes 1 image and applies it to every location in
     * the map
     */
    public static void backgroundBuild(String b, Location[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == null) {
                    map[i][j] = new Location(b);
                } else {
                    map[i][j].setImage(b);
                }
            }
        }
    }

    /**
     * Gets the location in the given direction
     *
     * @return the above location
     * @param a if a = 0, north if a = 1, east if a = 2, south if a = 3, west
     */
    public Location getLocInDir(int a, int y, int x) {
        int givenDir = a;

        switch (givenDir) {
            case KeyEvent.VK_UP:
                if (checkLoc(y - 1, x)) {
                    return getLocation(y - 1, x);
                }
                return getLocation(y, x);
            case KeyEvent.VK_RIGHT:
                if (checkLoc(y, x + 1)) {
                    return getLocation(y, x + 1);
                }
                return getLocation(y, x);
            case KeyEvent.VK_DOWN:
                if (checkLoc(y + 1, x)) {
                    return getLocation(y + 1, x);
                }
                return getLocation(y, x);
            case KeyEvent.VK_LEFT:
                if (checkLoc(y, x - 1)) {
                    return getLocation(y, x - 1);
                }
                return getLocation(y, x);
        }

        return getLocation(y, x);
        // CHANGE THIS IN GUI: WILL SWITCH MAPS
    }

    /**
     * Handles the keycode outputs
     */
    public String movePlayer(int keycode) {
        if (keycode == KeyEvent.VK_I) {
            return "inventory";
        }
        if (keycode == KeyEvent.VK_R) {
            return "reload";
        }
        if (keycode == KeyEvent.VK_SEMICOLON) {
            if (cheatcount < 3) {
                cheatcount++;
                return "easteregg";
            } else {
                return "badplayer";
            }
        }


        Point pp = findPlayer();
        int x = pp.x;
        int y = pp.y;
        int givenCode = keycode;
        Location moveto;
        String spot = "";

        switch (givenCode) {     
            case KeyEvent.VK_SLASH:
                return "save";
            case KeyEvent.VK_BACK_SLASH:
                return "load";
            case KeyEvent.VK_UP:
                moveto = getLocInDir(KeyEvent.VK_UP, y, x);
                storedCharacter = moveto.getCharacter();
                if (!(moveto.getCharacter() instanceof Player)) {
                    spot = map[y][x].getCharacter().changePosition(moveto);
                }

                if (moveto instanceof Door) {
                    return "door";
                } else {
                    return spot;
                }

            case KeyEvent.VK_RIGHT:
                moveto = getLocInDir(KeyEvent.VK_RIGHT, y, x);
                storedCharacter = moveto.getCharacter();
                if (!(moveto.getCharacter() instanceof Player)) {
                    spot = map[y][x].getCharacter().changePosition(moveto);
                }

                if (moveto instanceof Door) {
                    return "door";
                } else {
                    return spot;
                }

            case KeyEvent.VK_DOWN:
                moveto = getLocInDir(KeyEvent.VK_DOWN, y, x);
                storedCharacter = moveto.getCharacter();
                if (!(moveto.getCharacter() instanceof Player)) {
                    spot = map[y][x].getCharacter().changePosition(moveto);
                }

                if (moveto instanceof Door) {
                    return "door";
                } else {
                    return spot;
                }

            case KeyEvent.VK_LEFT:
                moveto = getLocInDir(KeyEvent.VK_LEFT, y, x);
                storedCharacter = moveto.getCharacter();
                if (!(moveto.getCharacter() instanceof Player)) {
                    spot = map[y][x].getCharacter().changePosition(moveto);
                }

                if (moveto instanceof Door) {
                    return "door";
                } else {
                    return spot;
                }

        }

        return "nah";
    }

    /**
     * Returns the stored character
     */
    public Character getStored() {
        return storedCharacter;
    }

    public void respawnEnemies(String element) {
        // CREATES ENEMIES
        int spot = 0;
        Enemy[] spots = new Enemy[2];
        if (element.equalsIgnoreCase("Earth")) {
            spots[0] = new AngryHobo(null, false);
            spots[1] = new Goblin(null, false);
        } else if (element.equalsIgnoreCase("Fire")) {
            spots[0] = new Spider(null, false);
            spots[1] = new Salamander(null, false);
        } else if (element.equalsIgnoreCase("Water")) {
            spots[0] = new Shark(null, false);
            spots[1] = new Stinger(null, false);
        } else {
            spots[0] = new Lakitu(null, false);
            spots[1] = new Fairy(null, false);
        }

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int y = (int) (Math.random() * 22);
                int x = (int) (Math.random() * 22);

                if (this.getLocation(y, x).isEmpty()) {
                    if (spot == 0) {
                        Enemy hold = spots[0].clone(this.getLocation(y, x));
                        spot = 1;
                    } else {
                        Enemy hold = spots[1].clone(this.getLocation(y, x));
                        spot = 0;
                    }
                } else {
                    j++;
                    i++;
                }

            }
        }

        return;
    }

    public void resetWalls(String element) {
        String ground = "default";
        String waller = "default";
        if (element.equalsIgnoreCase("earth")) {
            ground = "grass";
            waller = "tree";
        } else if (element.equalsIgnoreCase("fire")) {
            ground = "rock";
            waller = "lava";
        } else if (element.equalsIgnoreCase("water")) {
            ground = "water";
            waller = "water";
        } else if (element.equalsIgnoreCase("air")) {
            ground = "cloud";
            waller = "sky";
        }

        // CREATES WALLS
        BufferedImage back = getImageForClass("resources/" + ground + ".png");
        BufferedImage wall = getImageForClass("resources/" + waller + ".png");

        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 22; j++) {
                if (this.getLocation(j, i).getCharacter() instanceof Wall) {
                    this.getLocation(j, i).removeCharacter();
                }
            }
        }

        for (int i = 1; i <= 13; i++) {
            for (int j = 1; j <= 13; j++) {
                int y = (int) (Math.random()
                        * 16 + Math.random() * i);
                int x = (int) (Math.random()
                        * 16 + Math.random() * j);
                if (this.checkLoc(y, x)) {
                    if (this.getLocation(y, x).isEmpty()) {
                        Wall temp = new Wall(this.getLocation(y, x), wall);
                    } else {
                        j++;
                    }
                }
            }
        }

    }

    /**
     * A string representation of the map
     */
    @Override
    public String toString() {
        String info = "";

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].hasUnit()) {
                    info += map[i][j].
                            getCharacter().getClass().getName() + "\t";
                } else {
                    info += "null\t";
                }
            }

            info += "\n";
        }

        return info;
    }

    private static BufferedImage getImageForClass(String fileURL) {
        try {
            return (BufferedImage) (ImageIO.read(new File(fileURL)));
        } catch (IOException a) {
            System.out.println("Error");
        }

        return null;
    }
}