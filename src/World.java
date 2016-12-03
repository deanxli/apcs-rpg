 

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 151lid
 */
public class World {

    public static Map makeForest(String element) {
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

        BufferedImage wall = getImageForClass("resources/" + waller + ".png");

        Map cano = new Map(22, 22, ground);
        for (int i = 1; i <= 13; i++) {
            for (int j = 1; j <= 13; j++) {
                int y = (int) (Math.random()
                        * 16 + Math.random() * i);
                int x = (int) (Math.random()
                        * 16 + Math.random() * j);
                if (cano.checkLoc(y, x)) {
                    if (cano.getLocation(y, x).isEmpty()) {
                        Wall temp = new Wall(cano.getLocation(y, x), wall);
                    } else {
                        j++;
                        i++;
                    }
                }
            }
        }
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

        for (int i = 1; i <= 14; i++) {
            for (int j = 1; j <= 14; j++) {
                int y = (int) (Math.random() * 16 + Math.random() * i);
                int x = (int) (Math.random() * 16 + Math.random() * j);
                if (cano.checkLoc(y, x)) {
                    if (cano.getLocation(y, x).isEmpty()) {
                        if (spot == 0) {
                            Enemy hold = spots[0].clone(cano.getLocation(y, x));
                            spot = 1;
                        } else {
                            Enemy hold = spots[1].clone(cano.getLocation(y, x));
                            spot = 0;
                        }
                    } else {
                        j++;
                        i++;
                    }
                }

            }
        }

        return cano;
    }

    public static Map makeTemple(String element) {

        int diff = 0;

        String ground = "default";
        String waller = "default";
        if (element.equalsIgnoreCase("earth")) {
            ground = "foresttemple";
            waller = "tree";
            diff = 2;
        } else if (element.equalsIgnoreCase("fire")) {
            ground = "rTemple";
            waller = "Wall";
            diff = 4;
        } else if (element.equalsIgnoreCase("water")) {
            ground = "wTemple";
            waller = "Wall";
            diff = 6;
        } else if (element.equalsIgnoreCase("air")) {
            ground = "aTemple";
            waller = "Wall";
            diff = 8;
        }

        BufferedImage wall = getImageForClass("resources/" + waller + ".png");
        BufferedImage stone = getImageForClass("resources/stone.png");

        Map map = new Map(22, 22, ground);

        for (int j = 0; j <= 7; j++) {
            int y = (int) (Math.random() * 18);
            int x = (int) (Math.random() * 22);

            for (int i = y; i < y + 4; i++) {
                Location loc = map.getMap()[i][x];
                if (!loc.hasUnit() && !(loc instanceof Door)) {
                    Wall temp = new Wall(map.getLocation(i, x), wall);
                }
            }


        }

        for (int j = 0; j <= 7; j++) {
            int y = (int) (Math.random() * 22);
            int x = (int) (Math.random() * 18);

            for (int i = x; i < x + 4; i++) {
                Location loc = map.getMap()[y][i];
                if (!loc.hasUnit() && !(loc instanceof Door)) {
                    Wall temp = new Wall(map.getLocation(y, i), wall);
                }
            }
        }

        int spot = 0;
        Enemy[] spots = new Enemy[2];
        if (element.equalsIgnoreCase("Earth")) {
            spots[0] = new StoneGolem(null, false);
            spots[1] = new Orc(null, false);
        } else if (element.equalsIgnoreCase("Fire")) {
            spots[0] = new Spider(null, false);
            spots[1] = new Skeleton(null, false);
        } else if (element.equalsIgnoreCase("Water")) {
            spots[0] = new SeaSerpent(null, false);
            spots[1] = new Stinger(null, false);
        } else {
            spots[0] = new Paladin(null, false);
            spots[1] = new Fairy(null, false);
        }

        for (int k = 0; k < spots.length; k++) {
            for (int j = 0; j <= 15; j++) {
                Enemy enemy = spots[k];
                int y = (int) (Math.random() * 22);
                int x = (int) (Math.random() * 22);
                Location loc = map.getMap()[y][x];
                if (!loc.hasUnit() && !(loc instanceof Door)) {
                    Enemy hold = enemy.clone(map.getLocation(y, x));
                }
            }
        }

        int switcher = 0;

        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 8; j++) {
                int y = (int) (Math.random()
                        * 19 + Math.random() * i);
                int x = (int) (Math.random()
                        * 16 + Math.random() * j);

                if (map.checkLoc(y, x)) {
                    {
                        if (map.getLocation(y, x).isEmpty()) {
                            if (switcher == 0 || switcher == 1) {
                                Trap temp = new Trap(map.getLocation(y, x));
                                switcher++;
                            } else {
                                Chest temp = new Chest(new Potion("Health Potion",
                                        diff * 7), diff * 10, map.getLocation(y, x));
                                switcher = 0;
                            }
                        } else {
                            j++;
                            i++;
                        }
                    }
                }
            }
        }

        return map;
    }

//    public static Map makeSky()
//    {
//        BufferedImage sky = getImageForClass("resources/Sky.jpg");
//        BufferedImage cloud = getImageForClass("resources/CloudOnSky.png");
//        Map skies = new Map(22, 22, cloud);
//        for (int i = 1; i <= 9; i++)
//            for (int j = 1; j <= 9; j++)
//            {
//                int y = (int)(Math.random()
//                        * 13 + Math.random() * i);
//                int x = (int)(Math.random()
//                        * 13 + Math.random() * j);
//                if (skies.getLocation(y, x).isEmpty())
//                {
//                    Wall temp = new Wall(skies.getLocation(y, x), sky);
//                }
//            }
//        
//        for (int i = 1; i <= 7; i++)
//            for (int j = 1; j <= 7; j++)
//            {
//                int y = (int)(Math.random() * 15 + Math.random() * i);
//                int x = (int)(Math.random() * 15 + Math.random() * j);
//                if (skies.getLocation(y, x).isEmpty())
//                {
//                   Skeleton temp = new Skeleton(skies.getLocation(y, x));
//                }
//            }
//        
//        return skies;
//    }
//    public static Map createTemp() {
//          
//        
//        BufferedImage forest= getImageForClass("resources/Forest.png");
//        BufferedImage wall = getImageForClass("resources/Wall.png");
//        
//        Map earthtemp = new Map(22, 22, forest);
//        
//        for (int i = 0; i < 22; i++) {
//            for (int j = 0; j < 22; j++) {
//                
//               if (i == 4 || i == 13) {
//                    if (j != 9 && j != 10 && j != 12 && j != 13) {
//                        
//                        if (earthtemp.getLocation(i, j).isEmpty())
//                        {
//                        Wall temp = new Wall(earthtemp.getLocation(i, j), wall);
//                        }
//
//                    }
//                }
//                if (i == 8 || i == 17) {
//                    if (j != 0 && j != 1 && j != 20 && j != 21) {
//                        
//                        if (earthtemp.getLocation(i, j).isEmpty())
//                        {
//                        Wall temp = new Wall(earthtemp.getLocation(i, j), wall);
//                        }
//                    }
//                }
//                if (j == 11 && i != 21) {
//                    
//                    if (earthtemp.getLocation(i, j).isEmpty())
//                        {
//                        Wall temp = new Wall(earthtemp.getLocation(i, j), wall);
//                        }
//                }
//            }
//        }
//        
//        return earthtemp;
//    }
    private static BufferedImage getImageForClass(String fileURL) {
        try {
            return (BufferedImage) (ImageIO.read(new File(fileURL)));
        } catch (IOException a) {
            System.out.println("Error");
        }

        return null;
    }
}
