 

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.image.BufferedImage;

/**
 * @author Dean Li Extends the character class: can fight them, no opening of
 * dialogue
 */
public class Enemy extends Character {

    private int attack;
    private int defense;
    private int health;
    private int gold;
    private int exp;
    private String name;
    private String element;

    /**
     * Constructs the BASICS of the enemy Location and Image
     *
     * @param a The location of the enemy
     * @param b The image of the enemy
     */
    public Enemy(Location a, BufferedImage b,
            int attack, int defense, int health, String name, String element) {
        super(a, b, false);
        super.assertLocation(a);
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.name = name;
        updateGold();
        updateExp();
        this.element = element;
    }

    public Enemy(Location a, BufferedImage b,
            int attack, int defense, int health, String name, boolean p,
            String element) {
        super(a, b, false);
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.name = name;
        this.element = element;
        updateGold();
        updateExp();
    }

    public Enemy clone(Location a) {
        return new Enemy(a, super.getImage(), getAttack(),
                getDefense(), getHealth(), getName(), getElement());
    }

    /**
     * Changes the attack of the enemy
     *
     * @param attack
     */
    public void changeAttack(int attack) {
        this.attack = attack;
        updateExp();
        updateGold();
    }

    /**
     * Changes the defense of the enemy
     *
     * @param defense
     */
    public void changeDefense(int defense) {
        this.defense = defense;
        updateExp();
        updateGold();
    }

    /**
     * Changes the health of the enemy
     *
     * @param health
     */
    public void changeHealth(int health) {
        this.health = health;
        updateExp();
        updateGold();
    }

    /**
     * Adds health for enemy
     */
    public void addHealth(int a) {
        health += a;
    }

    /**
     * Changes the gold that the enemy carries
     *
     * @param gold
     */
    public void changeGold(int gold) {
        this.gold = gold;
    }

    /**
     * Changes the amount of EXP the enemy will give
     *
     * @param exp
     */
    public void changeExp(int exp) {
        this.exp = exp;
    }

    /**
     * Updates the EXP that the enemy gives
     */
    private void updateExp() {
        exp = ((int) ((Math.random() * 12 + 1) + Math.abs(health
                + defense - attack)) / 4);
    }

    /**
     * Updates the GOLD that the enemy gives
     */
    private void updateGold() {
        gold = ((int) ((Math.random() * 20 + 1) + Math.abs(health
                + defense - attack)) / 4);
    }

    /**
     * Begins the battle sequence Will call Tom's method. Preferably will be a
     * static method
     */
    /**
     * Returns the attack of the enemy
     *
     * @return the attack of the enemy
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Returns the defense of the enemy
     *
     * @return the defense number
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Returns the elemental alignment of the enemy
     */
    public String getElement() {
        return element;
    }

    /**
     * Returns the health of the enemy
     *
     * @return the health of the enemy
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the name of the enemy
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the amount of gold of the enemy
     *
     * @return gold # cash money
     */
    public int getGold() {
        return gold;
    }

    /**
     * Returns the exp awarded by the
     *
     * @return the experience
     */
    public int getExp() {
        return exp;
    }

    /**
     * Does the action that the character should when the enemy walks into them
     */
    public String invokeAction(Player a) {
        return "Enemy";

        // THIS IS CHANGED LATER
    }

    public Enemy getEnemy() {
        return (Enemy) (this);
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
    @Override
    public BufferedImage getImage() {
        return super.getImage();
    }

    /**
     * Has the enemy take damage
     */
    public void takeDamage(int a) {
        if (a < 0) {
            return;
        } else if (health - a <= 0) {
            health = 0;
            return;
        }

        health -= a;
    }

    /**
     * tells if the enemy is dead
     *
     * @return true if dead
     */
    public boolean isDead() {
        if (health == 0) {
            return true;
        }

        return false;
    }

    /**
     * #read the method name
     */
    public Character removeThisCharacter() {
        return this.getPosition().removeCharacter();
    }

    public String toString() {
        if (element.equalsIgnoreCase(("None"))) {
            return name;
        }

        return element + " " + name;
    }
}