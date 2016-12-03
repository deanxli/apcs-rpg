 

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * @author Dean Li The main character of the game
 */
public class Player extends Character {

    private String name;
    private int health;
    private int currenthealth;
    private int attack;
    private int defense;
    private int exp;
    private int level;
    private int gold;
    private Inventory inventory;

    public Player(Location position, BufferedImage image) {
        super(position, image, false);
        super.assertLocation(position);
        this.health = 17;
        this.attack = 5;
        this.defense = 5;
        this.exp = 0;
        this.level = 1;
        this.gold = 50;
        inventory = new Inventory();
        inventory.equipItem(new Equipment("Thick Shirt", 0, 5, "None"));
        inventory.equipItem(new Equipment("Wooden Stick", 5, 0, "None"));
        inventory.addItem(new Potion("Small Potion", 10));
        inventory.addItem(new Potion("Small Potion", 10));
        currenthealth = health;
    }

    public Player clone(Location a) {
        return new Player(this.health, this.attack, this.defense, this.exp,
                this.level, this.gold, this.currenthealth, a, super.getImage(), this.getInventory());

    }

    public Player death(Location a) {
        if (this.gold - 50 < 0) {
            this.gold = 0;
        } else {
            this.gold -= 50;
        }

        if (this.exp - (15) < 0) {
            this.exp = 0;
        } else {
            this.exp -= (int)(15 * level / 2);
        }

        if (this.attack - 1 < 1) {
            this.attack = 1;
        } else {
            this.attack--;
        }

        if (this.defense - 1 < 1) {
            this.defense = 1;
        } else {
            this.defense--;
        }

        return new Player(this.health, this.attack, this.defense, this.exp,
                this.level, this.gold, this.currenthealth, a, super.getImage(), this.getInventory());

    }

    public Player(Player a, Location b) {
        super(b, a.getImage(), false);
        super.assertLocation(b);
        health = a.getHealth();
        attack = a.getAttack();
        defense = a.getDefense();
        exp = a.getExp();
        level = a.getLevel();
        gold = a.getGold();
        inventory = a.getInventory();
        currenthealth = a.getCurrentHealth();
    }

    public Player(int health, int attack, int defense, int exp,
            int level, int gold, int currenthealth, Location position,
            BufferedImage image, Inventory invent) {
        super(position, image, false);
        super.assertLocation(position);
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.exp = exp;
        this.level = level;
        this.gold = gold;
        this.inventory = invent;
        this.currenthealth = currenthealth;
    }

    public void LevelUp() {
        health += ((Math.random() * 6) + 1) + level * 1.5 + 4;
        attack += (int) (Math.random() * 3) + level * 1.5 + 4;
        defense += (int) (Math.random() * 3) + level * 1.5 + 4;
        level++;
        currenthealth = health;
    }

    /**
     * Returns the location of the character
     *
     * @Location (likely two coordinates)
     */
    public Location getPosition() {
        return super.getPosition();
    }

    public String getAttackMessage() {
        int random = (int) (Math.random() * 10);

        if (random == 0) {
            return "slash ";
        }
        if (random == 1) {
            return "hack ";
        }
        if (random == 2) {
            return "maul ";
        }
        if (random == 3) {
            return "flatten ";
        }
        if (random == 4) {
            return "vaporize ";
        }
        if (random == 5) {
            return "deeply paper-cut ";
        }
        if (random == 6) {
            return "slap the crap out of ";
        }
        if (random == 7) {
            return "call on the number 17 to destroy ";
        }
        if (random == 8) {
            return "curse out ";
        }
        return "go all Gabriel-Belmont on ";

    }

    /**
     * Moves to a new Location
     */
    public String changeLocation(Location a) {
        if (super.assertLocation(a).equals("yes")) {
            return super.changePosition(a);
        }

        return "no";
    }

    /**
     * Returns whether a level-up is achieved
     */
    public boolean addExp(int exp) {
        this.exp += exp;
        if (this.exp >= (int) (20 * level + Math.pow(level, 2) + (Math.pow(1.5, level))) + 
        (int) (25 * (level - 1) + Math.pow((level - 1), 2) + (Math.pow(1.5, (level - 1))))) {
            LevelUp();
            return true;
        }

        return false;
    }

    /**
     * Returns an image for the map to display
     *
     * @Image the image of the main character
     */
    public BufferedImage getImage() {
        return super.getImage();
    }

    public void heal(int n) {
        currenthealth += n;
        if (currenthealth > health) {
            currenthealth = health;
        }
    }

    /**
     * Buys equipment
     *
     * @param a the equipment that is bought
     */
    public boolean buyItem(Item a) {
        if (a.buyValue() <= gold) {
            if (inventory.addItem(a)) {
                gold -= a.buyValue();
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * The attack of the player
     *
     * @return attack value
     */
    public int getAttack() {
        int a = attack;
        a += inventory.getEquipment()[0].getAttackBoost();
        a += inventory.getEquipment()[1].getAttackBoost();
        return a;
    }

    /**
     * The defense of the player
     *
     * @return defense value
     */
    public int getDefense() {
        int d = defense;
        d += inventory.getEquipment()[0].getDefenseBoost();
        d += inventory.getEquipment()[1].getDefenseBoost();
        return d;
    }

    /**
     * The exp of the player
     *
     * @return exp value
     */
    public int getExp() {
        return exp;
    }

    /**
     * Returns the name of the player
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * The gold of the player
     *
     * @return gold value
     */
    public int getGold() {
        return gold;
    }

    /**
     * Adds gold for the player
     */
    public void addGold(int a) {
        gold += a;
    }

    /**
     * The health of the player
     *
     * @return health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * The level of the player
     *
     * @return level value
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Gets current health of the player
     */
    public int getCurrentHealth() {
        return currenthealth;
    }

    /**
     * The attack of the player
     *
     * @return attack value
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the player's maximum health
     *
     * @param a the maximum health
     * @return
     */
    public void setMaxHealth(int a) {
        health = a;
    }

    /**
     * Sets the player's current health
     *
     * @param a the player's current health
     * @return
     */
    public void setCurrentHealth(int a) {
        currenthealth = a;
    }

    @Override
    /**
     * This does nothing
     */
    public String invokeAction(Player a) {
        return null;
    }

    /**
     * Player takes damage
     */
    public void takeDamage(int a) {
        if (a < 0) {
            return;
        }
        if (currenthealth - a <= 0) {
            currenthealth = 0;
            return;
        }

        currenthealth -= a;
    }

    public boolean sellItem(Item a) {
        if (inventory.removeItem(a)) {
            gold += a.sellValue();
            return true;
        }
        return false;
    }

    /**
     * Temporary crappy toString
     *
     * @return
     */
    public String toString() {
        String info =
                "Name: " + name + "\nHealth: " + currenthealth;
        info += "\nAttack: " + attack + "\nDefense: " + defense;

        return info;
    }
}