 

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 151lid
 */
public class Game {

    private Map displayMap;
    private Map homeMap;
    private Map earthForest;
    private Map fireForest;
    private Map waterForest;
    private Map airForest;
    private Map START_MAP;
    private Map earthTemple;
    private Map fireTemple;
    private Map waterTemple;
    private Map airTemple;
    private Point startLoc;
    private Door homeToEarth;
    private Door earthToHome;
    private Door homeToFire;
    private Door fireToHome;
    private Door homeToWater;
    private Door waterToHome;
    private Door homeToAir;
    private Door airToHome;
    private Door forestToTemple;
    private Door templeToForest;
    private Door fireToTemple;
    private Door templeToFire;
    private Door waterToTemple;
    private Door templeToWater;
    private Door airToTemple;
    private Door templeToAir;
    private Player savedPlay;
    private Player dean;
    private File playerFile; 
    private Scanner reader;
    private FileWriter output;

    public Game() throws FileNotFoundException {
        initVars();
        createDoors();
        createMerchants();
    }

    private void initVars() throws FileNotFoundException {

        BufferedImage playerpic = getImageForClass("resources/player.png");
        BufferedImage tempBack = getImageForClass("resources/grass.png");

        homeMap = new Map(22, 22, "grass");
        earthForest = World.makeForest("Earth");
        fireForest = World.makeForest("Fire");
        waterForest = World.makeForest("Water");
        airForest = World.makeForest("Air");
        earthTemple = World.makeTemple("Earth");
        fireTemple = World.makeTemple("Fire");
        waterTemple = World.makeTemple("Water");
        airTemple = World.makeTemple("Air");

        START_MAP = homeMap;
        
        playerFile = new File("resources/Phonebook.txt");
        reader = new Scanner(playerFile);
    }

    private void createDoors() {
        earthForest.getLocation(20, 20).removeCharacter();
        fireForest.getLocation(20, 20).removeCharacter();
        waterForest.getLocation(20, 20).removeCharacter();
        airForest.getLocation(15, 18).removeCharacter();
        airForest.getLocation(17, 17).removeCharacter();
        airForest.getLocation(19, 16).removeCharacter();
        airForest.getLocation(14, 14).removeCharacter();

        Enemy stonegolem = new StoneGolem(earthForest.getLocation(20, 20));
        Enemy skeleton = new Skeleton(fireForest.getLocation(20, 20));
        Enemy seaserpent = new SeaSerpent(waterForest.getLocation(20, 20));
        Enemy paladin = new Paladin(airForest.getLocation(15, 18));
        Enemy paladin1 = new Paladin(airForest.getLocation(17, 17));
        Enemy paladin2 = new Paladin(airForest.getLocation(19, 16));
        Enemy paladin3 = new Paladin(airForest.getLocation(14, 14));

        earthTemple.getLocation(20, 20).removeCharacter();
        fireTemple.getLocation(20, 20).removeCharacter();
        waterTemple.getLocation(20, 20).removeCharacter();
        airTemple.getLocation(20, 20).removeCharacter();

        Boss edragon = new Boss(earthTemple.getLocation(20, 20),
                getImageForClass("resources/EarthDragon.png"), 80, 120, 150,
                new Equipment[]{new Equipment("Tree Bark Shield", 10, 55, "Earth")}, "Dragon", "Earth", " takes a mighty swipe at you ", " breathes ... well, Earth on you ", 30);
        Boss fdragon = new Boss(fireTemple.getLocation(20, 20),
                getImageForClass("resources/FireDragon.png"), 220, 200, 300,
                new Equipment[]{new Equipment("Fire Flail", 95, 15, "Fire")}, "Dragon", "Fire", " flattens you underneath its tail ", " spits lava on you ", 50);
        Boss wdragon = new Boss(waterTemple.getLocation(20, 20),
                getImageForClass("resources/WaterDragon.png"), 300, 280, 137,
                new Equipment[]{new Equipment("Water Tunic", 25, 130, "Water")}, "Dragon", "Water", " knocks you underwater and laughs ", " creates a tsunami ", 75);
        Boss svett = new Boss(airTemple.getLocation(20, 20),
                getImageForClass("resources/svetty.png"), 500, 500, 500,
                new Equipment[]{new Equipment("Bottle of Bubbly", 300, 240, "Water")}, "Svetty "
                + "the Creator", "None", " traps you in a while(true) loop ", ""
                + " points out syntax errors in your code ", 200);

        earthTemple.getLocation(0, 5).removeCharacter();
        fireTemple.getLocation(0, 5).removeCharacter();
        waterTemple.getLocation(0, 5).removeCharacter();
        airTemple.getLocation(0, 5).removeCharacter();

        homeToEarth = new Door(homeMap, new Point(0, 6));
        homeToFire = new Door(homeMap, new Point(0, 16), edragon);
        homeToAir = new Door(homeMap, new Point(21, 16), wdragon);
        homeToWater = new Door(homeMap, new Point(21, 6), fdragon);
        earthToHome = new Door(earthForest, new Point(1, 1));
        fireToHome = new Door(fireForest, new Point(1, 1));
        waterToHome = new Door(waterForest, new Point(1, 1));
        airToHome = new Door(airForest, new Point(1, 1));

        forestToTemple = new Door(earthForest, new Point(21, 21), stonegolem);
        fireToTemple = new Door(fireForest, new Point(21, 21), skeleton);
        waterToTemple = new Door(waterForest, new Point(21, 21), seaserpent);
        airToTemple = new Door(airForest, new Point(21, 21), paladin3);

        templeToForest = new Door(earthTemple, new Point(0, 5));
        templeToFire = new Door(fireTemple, new Point(0, 5));
        templeToWater = new Door(waterTemple, new Point(0, 5));
        templeToAir = new Door(airTemple, new Point(0, 5));

        BufferedImage earth = getImageForClass("resources/earthking.png");
        BufferedImage fire = getImageForClass("resources/fireking.png");
        BufferedImage water = getImageForClass("resources/waterking.png");
        BufferedImage air = getImageForClass("resources/airking.png");

        Wall earthwall = new Wall(homeMap.getLocation(6, 1), earth);
        Wall /**
                 * LOL
                 */
                firewall = new Wall(homeMap.getLocation(16, 1), fire);
        Wall waterwall = new Wall(homeMap.getLocation(6, 20), water);
        Wall airwall = new Wall(homeMap.getLocation(16, 20), air);

        BufferedImage pichouse1 = getImageForClass("resources/house1.png");
        BufferedImage pichouse2 = getImageForClass("resources/house2.png");
        BufferedImage pichouse3 = getImageForClass("resources/house3.png");
        BufferedImage pichouse4 = getImageForClass("resources/house4.png");
        BufferedImage mark = getImageForClass("resources/mara.png");

        Wall house1 = new Wall(homeMap.getLocation(15, 7), pichouse1);
        Wall house2 = new Wall(homeMap.getLocation(15, 8), pichouse2);
        Wall house3 = new Wall(homeMap.getLocation(16, 7), pichouse3);
        Wall house4 = new Wall(homeMap.getLocation(16, 8), pichouse4);
        Friend mara = new Friend(homeMap.getLocation(16, 9), mark,
                "I've heard some rumors about monsters lurking "
                + "in forests and temples being hidden.", "Cameooooooo.");

        BufferedImage dean = getImageForClass("resources/dean.png");

        Wall house12 = new Wall(homeMap.getLocation(12, 12), pichouse1);
        Wall house23 = new Wall(homeMap.getLocation(12, 13), pichouse2);
        Wall house34 = new Wall(homeMap.getLocation(13, 12), pichouse3);
        Wall house45 = new Wall(homeMap.getLocation(13, 13), pichouse4);
        Friend deandean = new Friend(homeMap.getLocation(14, 12), dean,
                "You're probably gonna have to do something about those angry"
                + " hobos out there in that first forest.", "I'm a former explorer "
                + "myself, and I'd beware about some of those treasure chests...");

        Wall house5 = new Wall(homeMap.getLocation(4, 4), pichouse1);
        Wall house6 = new Wall(homeMap.getLocation(4, 5), pichouse2);
        Wall house7 = new Wall(homeMap.getLocation(5, 4), pichouse3);
        Wall house8 = new Wall(homeMap.getLocation(5, 5), pichouse4);
        BufferedImage carol = getImageForClass("resources/carol.png");
        Friend carolyn = new Friend(homeMap.getLocation(6, 3), carol, "Press / to Save "
                + "", "Press '\' to load a saved character. ");

        BufferedImage nate = getImageForClass("resources/nathan.png");
        Friend nathan = new Friend(homeMap.getLocation(19, 5), nate, "Hold on,"
                + " I'm on my laptop.", "Play the other version of the game kuz"
                + "minez da bestest");

        BufferedImage malis = getImageForClass("resources/malis.png");
        Wall house123 = new Wall(homeMap.getLocation(5, 12), pichouse1);
        Wall house232 = new Wall(homeMap.getLocation(5, 13), pichouse2);
        Wall house342 = new Wall(homeMap.getLocation(6, 12), pichouse3);
        Wall house451 = new Wall(homeMap.getLocation(6, 13), pichouse4);
        Friend michael = new Friend(homeMap.getLocation(6, 14), malis,
                "What?", "You don't understand him, but you feel like you've gained"
                + "\nsome new insight on the world of code. Did your attack just go up?!",
                "No. No it did not.");

        BufferedImage friend = getImageForClass("resources/friend.png");
        BufferedImage andy = getImageForClass("resources/andy.png");

        Friend tootoriole = new Friend(homeMap.getLocation(8, 8), andy,
                "Hey Andy! What do I do?",
                "Well, you've figured out how to interact with characters!"
                + " But you've got a lot to learn.\n"
                + "Some quick tips! \nPress R to reload the screen and I for Inventory",
                "Find the town mayor on the right!");
        Friend mayor = new Friend(homeMap.getLocation(8, 13), friend,
                "There are four elements in the game:\n"
                + "Earth, Fire, Water, Air.", "You cannot advance to the next"
                + " temple until you beat the previous one.", "Each element "
                + "has a weakness and a strength.\nEach temple will reveal"
                + "its secrets!");
        Friend eforestguide = new Friend(homeMap.getLocation(8, 1), friend,
                "Earth is the best!\n"
                + "Earth is way better than water", "Buy earth gear!", "Earth "
                + "has extremely high defense!");
        Friend fforestguide = new Friend(homeMap.getLocation(18, 1), friend,
                "Let's get heated up!\n"
                + "Fire burns down earth", "Buy fire gear!", "Fire "
                + "has incredible attack!");
        Friend aforestguide = new Friend(homeMap.getLocation(18, 20), friend,
                "Air is the element of champions.\n"
                + "Air has an advantage over every other element", "It's pretty"
                + " expensive", "Air's attack values are up to chance.");
        Friend wforestguide = new Friend(homeMap.getLocation(8, 20), friend,
                "Water's the \"coolest\"\n"
                + "Water quenchs fire", "Buy water gear!", "Water "
                + "is balanced - and likes the number three.");

        earthForest.getLocation(2, 2).removeCharacter();
        fireForest.getLocation(2, 2).removeCharacter();
        waterForest.getLocation(2, 2).removeCharacter();
        airForest.getLocation(2, 2).removeCharacter();

        Friend earthguide = new Friend(earthForest.getLocation(2, 2), friend,
                "Welcome to the Earth Forest!", "Earth is weak against fire, "
                + "strong against water.", "Find the chest in this level!");
        Friend fireguide = new Friend(fireForest.getLocation(2, 2), friend,
                "Welcome to the Fire Forest!", "Fire is weak against water, "
                + "strong against earth.", "THe fire maze is ever-shifting");
        Friend waterguide = new Friend(waterForest.getLocation(2, 2), friend,
                "Welcome to the Water Forest!\nHome of Mirages", "Water is "
                + "strong against fire, weak against earth.", ""
                + "The walls are invisible:");
        Friend airguide = new Friend(airForest.getLocation(2, 2), friend,
                "Welcome to the Air Forest! Air is special", "Air is a fickle "
                + "element. Air is only negated by itself.\nYour attack numbers"
                + " are entirely up to its choice",
                "Air can be deceiving.");

        earthForest.setLocation(1, 1, earthToHome);
        fireForest.setLocation(1, 1, fireToHome);
        waterForest.setLocation(1, 1, waterToHome);
        airForest.setLocation(1, 1, airToHome);

        earthToHome.setOther(homeToEarth);
        homeToEarth.setOther(earthToHome);
        fireToHome.setOther(homeToFire);
        homeToFire.setOther(fireToHome);
        waterToHome.setOther(homeToWater);
        homeToWater.setOther(waterToHome);
        airToHome.setOther(homeToAir);
        homeToAir.setOther(airToHome);

        forestToTemple.setOther(templeToForest);
        fireToTemple.setOther(templeToFire);
        waterToTemple.setOther(templeToWater);
        airToTemple.setOther(templeToAir);
        templeToForest.setOther(forestToTemple);
        templeToFire.setOther(fireToTemple);
        templeToWater.setOther(waterToTemple);
        templeToAir.setOther(airToTemple);
    }

    private void createMerchants() {
        Item[] potions = new Item[]{
            new Potion("Small Potion", 10),
            new Potion("Medium Potion", 20),
            new Potion("Large Potion", 50),
            new Potion("X-Large Potion", 100),
            new Potion("XX-Large Potion", 200),
            new Potion("7-Eleven Big Gulp", 300),
            new Potion("Ginormous Potion", 500),
            new Potion("Gabriel Belmont", 1000)};
        Item[] weapons = new Item[]{new Equipment("Crossbow", 35, 0, "None"),
            new Equipment("Steel Edge", 75, 0, "None"),
            new Equipment("Ironborn Pike", 100, 0, "None"),
            new Equipment("Flail", 125, 0, "None"),
            new Equipment("Morning Star", 150, 0, "None"),
            new Equipment("Seven-in-One", 175, 0, "Air"),
            new Equipment("Cloth Armor", 0, 20, "None"),
            new Equipment("Steel Kiteshield", 0, 100, "None"),
            new Equipment("Mithril Tunic", 0, 180, "None")};
        Item[] earth = new Item[]{new Equipment("Rusty Dagger", 20, 5, "Earth"),
            new Equipment("Stone Longsword", 50, 0, "Earth"),
            new Equipment("Wooden Staff", 75, 30, "Earth"),
            new Equipment("Earthen Mace", 125, 20, "Earth"),
            new Equipment("Forest Katana", 155, 0, "Earth"),
            new Equipment("Elven Bow", 165, 50, "Earth"),
            new Equipment("Clay Breastplate", 0, 20, "Earth"),
            new Equipment("Forest Jacket", 15, 150, "Earth"),
            new Equipment("Earth Tunic", 0, 210, "Earth")};
        Item[] water = new Item[]{new Equipment("Rain Staff", 23, 13, "Water"),
            new Equipment("Snow Problem", 63, 3, "Water"),
            new Equipment("Misty Greatsword", 93, 0, "Water"),
            new Equipment("Ice Sickle", 153, 23, "Water"),
            new Equipment("Frozen Nunchaku", 173, 0, "Water"),
            new Equipment("Misty Bow", 203, 10, "None"),
            new Equipment("Wet Tunic", 0, 50, "Water"),
            new Equipment("Aquamarine Armor", 10, 125, "Water"),
            new Equipment("Frost Shield", 50, 200, "Water")};
        Item[] fire = new Item[]{new Equipment("Flaming Mace", 25, 0, "Fire"),
            new Equipment("Molten Flail", 55, 0, "Fire"),
            new Equipment("Flaming Whip", 125, 0, "Fire"),
            new Equipment("Hot Talons", 153, 0, "Fire"),
            new Equipment("Fire Poi", 190, 0, "Fire"),
            new Equipment("Ring of Fire", 250, 0, "Fire"),
            new Equipment("Lava-Forged Gloves", 30, 70, "Fire"),
            new Equipment("Vulcan Chainmail", 70, 80, "Fire"),
            new Equipment("Red-Hot Armor", 90, 130, "Fire")};
        Item[] air = new Item[]{new Equipment("Pendant of Air", 245, 0, "Air"),
            new Equipment("Air Pike", 220, 40, "Air"),
            new Equipment("Cloud Bow", 250, 20, "Air"),
            new Equipment("Cloud Staff", 240, 35, "Air"),
            new Equipment("Light Armor", 30, 200, "Air"),
            new Equipment("Adamantium Exo-Skeleton", 0, 500, "Air")};

        Merchant pseller = new Merchant(homeMap.getLocation(3, 8), "Potions", potions);
        Merchant wseller = new Merchant(homeMap.getLocation(3, 13), "Weapons", weapons);
        Merchant earthweap = new Merchant(homeMap.getLocation(4, 1), "Earth", earth);
        Merchant fireweap = new Merchant(homeMap.getLocation(14, 1), "Fire", fire);
        Merchant waterweap = new Merchant(homeMap.getLocation(4, 20), "Water", water);
        Merchant airweap = new Merchant(homeMap.getLocation(14, 20), "Air", air);
    }

    public void loadPlayer() throws FileNotFoundException
    {
        reader = new Scanner(playerFile);
        int[] data = new int[7];
        for (int i = 0; i < 7; i++)
        {
            data[i] = Integer.parseInt(reader.nextLine());
        }
        
        Inventory invent = new Inventory();
        for (int i = 0; i < 10; i++)
        {
            String[] parts = (reader.nextLine().split("\\s"));
            if (i < parts.length && parts[i].equals("equipment"))
            {
                Equipment hold = new Equipment(parts[1] + " " + parts[2], Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[4]), parts[5]);
                invent.addItem(hold);
            }
            else if (i < parts.length && parts[i].equals("potion"))
            {
                Potion hold = new Potion(parts[1] + " " + parts[2], Integer.parseInt(parts[3]));
                invent.addItem(hold);
            }
        }
        
        String[] eparts = (reader.nextLine().split("\\s"));
        Equipment a9 = new Equipment(eparts[1] + " " + eparts[2], Integer.parseInt(eparts[3]),
                    Integer.parseInt(eparts[4]), eparts[5]);
        
            invent.equipItem(a9);
        
        String[] eparts2 = (reader.nextLine().split("\\s"));
        Equipment b9 = new Equipment(eparts2[1] + " " + eparts2[2], Integer.parseInt(eparts2[3]),
                    Integer.parseInt(eparts2[4]), eparts2[5]);
        
            invent.equipItem(b9);
            
        Location START_LOC = START_MAP.getLocation(2, 3);
        START_LOC.removeCharacter();
        BufferedImage playerpic = getImageForClass("resources/playertwo.png");    
            
        savedPlay = new Player(data[0], data[1], data[2], data[3],
                data[4], data[5], data[6], START_LOC, playerpic, invent);
        
        START_LOC.removeCharacter();
        
    }
    
    public void save() throws IOException
    {
        PrintWriter writer = new PrintWriter(playerFile);
        writer.print("");
        writer.close();
                
        output = new FileWriter(playerFile, true);
        output.write(dean.getHealth() + "\n");
        output.write(dean.getAttack() + "\n");
        output.write(dean.getDefense() + "\n");
        output.write(dean.getExp() + "\n");
        output.write(dean.getLevel() + "\n");
        output.write(dean.getGold() + "\n");
        output.write(dean.getCurrentHealth() + "\n");
        output.write(dean.getInventory().toString() + "\n");
        
        output.close();
    }
    
    
    public void run() throws IOException {

        displayMap = START_MAP;
        
            //public Player(int health, int attack, int defense, int exp,
            //int level, int gold, int currenthealth, Location position,
            //BufferedImage image, Inventory invent)

        earthForest.getLocation(15, 6).removeCharacter();
        earthForest.getLocation(3, 19).removeCharacter();

        Chest treasure = new Chest(80, earthForest.getLocation(15, 6));
        Chest item = new Chest(new Equipment("Smoking Dagger", 12, 0, "Fire"),
                20, earthForest.getLocation(3, 19));

        Location START_LOC = START_MAP.getLocation(2, 3);
        START_LOC.removeCharacter();
        BufferedImage playerpic = getImageForClass("resources/playertwo.png");

        dean = new Player(START_LOC, playerpic);

        START_LOC.removeCharacter();
        dean = new Player(START_LOC, playerpic);

//        System.out.println(dean.getPosition());
        startLoc = displayMap.findPlayer();
        Point ulc = new Point(10 * (startLoc.x / 11), 10 * (startLoc.y / 11));
        GUI gui = new GUI();
        gui.setVisible(true);
        int n = gui.dispMap(displayMap, ulc);

        while (true) {

            ArrayList<Point> points = new ArrayList<Point>();

            startLoc = displayMap.findPlayer();
            Point umc = new Point(10 * (startLoc.x / 11), 10 * (startLoc.y / 11));
//            System.out.println("x: " + startLoc.x + "y: " + startLoc.y);
            points.add(startLoc);

            String answer = displayMap.movePlayer(n);
//            System.out.println(answer);
            if (answer.equals("door")) {
                Point temp = displayMap.findPlayer();
                Door tempd = (Door) (displayMap.getLocation(temp.y, temp.x));
                if ((tempd.isOpen())) {
                    if (JOptionPane.showConfirmDialog(gui, "Do you want to "
                            + "enter?", "Door", JOptionPane.YES_NO_OPTION)
                            == JOptionPane.YES_OPTION) {
                        Door other = tempd.getOther();
                        tempd.removeCharacter();
                        if (other.getMap() == fireForest) {
                            fireForest.resetWalls("fire");
                            earthTemple.respawnEnemies("Earth");
                        }
                        

                        displayMap = other.getMap();

                        int newx = other.getLoc().x;
                        int newy = other.getLoc().y;
                        displayMap.getLocation(newy, newx).removeCharacter();
                        dean = dean.clone(displayMap.getLocation(newy, newx));

                        ulc = new Point(10 * (other.getLoc().x / 11),
                                10 * (other.getLoc().y / 11));
                        n = gui.easyDispMap(displayMap, ulc);

                        Point newLoc = displayMap.findPlayer();
                        points.add(newLoc);
                        n = gui.easyUpdateMap(points);
//                        System.out.println("New map point:" + displayMap.findPlayer());
                    }
                } else {
                    JOptionPane.showMessageDialog(gui, "Door Locked"
                            + "\nYou must kill the " + tempd.getKey().toString()
                            + " first to get the key.");
                }

            } else if (answer.equalsIgnoreCase("Enemy")) {
                Enemy a = (Enemy) (displayMap.getStored());

                gui.dispBattle(dean, a);
                if (dean.getCurrentHealth() <= 0) {
                    earthForest.respawnEnemies("earth");
                    earthForest.resetWalls("earth");
                    fireForest.respawnEnemies("fire");
                    fireForest.resetWalls("fire");
                    waterForest.respawnEnemies("water");
                    waterForest.resetWalls("water");
                    airForest.respawnEnemies("air");
                    airForest.resetWalls("air");
                    earthTemple.respawnEnemies("earth");
                    waterTemple.respawnEnemies("water");

                    Point temp = displayMap.findPlayer();
                    displayMap.getLocation(temp.y, temp.x).removeCharacter();

                    displayMap = START_MAP;

                    dean.setCurrentHealth(dean.getHealth());
                    dean = dean.death(START_LOC);
                    ((Character) dean).assertLocation(START_LOC);

                    ulc = new Point(0, 0);
                }

                n = gui.easyDispMap(displayMap, ulc);

            } 
            else if (answer.equalsIgnoreCase("save"))
            {
                save();
                loadPlayer();
                
                JOptionPane.showMessageDialog(gui, "Player saved.");
            }
            
            else if (answer.equalsIgnoreCase("load"))
            {
                 Object[] options = {"Yes, definitely",
                    "No thanks"};
                 int j = JOptionPane.showOptionDialog(gui, "You are loading"
                         + " a previously saved player.\n",
                "Load Player "
                 + "Continue?",
                 JOptionPane.YES_NO_OPTION,
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 options,
                 options[1]);
                 
                 if (savedPlay == null)
                 {
                     JOptionPane.showMessageDialog(gui, "You have to save first!");
                 }
                 else if (dean == savedPlay && dean.getPosition() == START_LOC)
                 {
                     
                 }
                 else if (j == 0)
                 {
                    Point temp = displayMap.findPlayer();
                    displayMap.getLocation(temp.y, temp.x).removeCharacter();
                    
                    displayMap = START_MAP;

                    dean = savedPlay;
                    START_LOC.removeCharacter();
                    ((Character)dean).assertLocation(START_LOC);

                    ulc = new Point(0, 0);
                
                    n = gui.easyDispMap(displayMap, ulc);
                 }
            }
            
            
           
            else if (answer.equalsIgnoreCase("Inventory")) {
                gui.dispInventory(dean);
                n = gui.easyDispMap(displayMap, ulc);
            } else if (answer.equalsIgnoreCase("Friend")) {
                Player p = (Player) (displayMap.getLocation(displayMap.findPlayer().y,
                        displayMap.findPlayer().x).getCharacter());

                gui.dispText(((Friend) displayMap.getStored()).getDialogue());
            } else if (answer.equalsIgnoreCase("Merchant")) {
                Player p = (Player) (displayMap.getLocation(displayMap.findPlayer().y,
                        displayMap.findPlayer().x).getCharacter());

                gui.dispMerchant(p, (Merchant) displayMap.getStored());
                n = gui.easyDispMap(displayMap, ulc);
            } else if (answer.equalsIgnoreCase("Reload")) {
                n = gui.easyDispMap(displayMap, ulc);
                n = gui.easyUpdateMap(points);
            } else if (answer.equalsIgnoreCase("Trap")) {
                if (dean.getCurrentHealth() >= 3) {
                    dean.takeDamage(2);
                    gui.dispText("It's a trap! You take two damage, while the"
                            + " trap sinks back into the ground.");
                }

                gui.easyDispMap(displayMap, ulc);
            } else if (answer.equalsIgnoreCase("TrapTwo")) {
                if (dean.getCurrentHealth() >= 3) {
                    dean.takeDamage(1);
                    gui.dispText("With much effort and scrapes,"
                            + " you cover up the hole.");
                }

                gui.easyDispMap(displayMap, ulc);
            } else if (answer.equalsIgnoreCase("easteregg")) {
                dean.addGold(100);
                gui.dispText("You've recieved 100 gold!");
            } else if (answer.equalsIgnoreCase("BadPlayer")) {
                gui.dispText("You shouldn't be doing that!");
            } else if (answer.equalsIgnoreCase("Chest")) {
                Player p = (Player) (displayMap.getLocation(displayMap.findPlayer().y,
                        displayMap.findPlayer().x).getCharacter());
                Chest here = (Chest) (displayMap.getStored());

                String s = "You get:";
                if (here.getItem() != null) {
                    s += "\na " + here.getItem().getName();
                    p.getInventory().addItem(here.getItem());
                }

                s += "\n" + here.getGold() + " gold";
                p.addGold(here.getGold());
                JOptionPane.showMessageDialog(gui, s);
                n = gui.easyDispMap(displayMap, ulc);
            }

            Point newLoc = displayMap.findPlayer();
            points.add(newLoc);
//            System.out.println(newLoc);
            ulc = new Point(10 * (newLoc.x / 11), 10 * (newLoc.y / 11));

            if (!(ulc.equals(umc))) {
                n = gui.easyDispMap(displayMap, ulc);
            } else {
                n = gui.updateMap(points);
            }

        }
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