 

import java.util.Random;

/**
 * @author Tom Added to by Dean See
 */
public class Battle {

    /**
     * has both parties attack each other
     *
     * @param p = the player
     * @param e = the enemy
     */
    public static int attack(Player p, Enemy e) {
        String pAElement = p.getInventory().getEquipment()[0].getElement();
        String pDElement = p.getInventory().getEquipment()[1].getElement();
        String eElement = e.getElement();

        int pAttack = (p.getAttack() / 2) + (int) ((p.getAttack() / 2) * (Math.random() * 2.3 + .5));
        int eAttack = (e.getAttack() / 2) + (int) ((e.getAttack() / 2) * (Math.random() * 2.1 + .5));

        // GOD a switch would be nice
        if (pAElement.equals("Fire")) {
            if (eElement.equals("Water")) {
                pAttack = (int) (pAttack * .70);
            }
            if (eElement.equals("Earth")) {
                pAttack = (int) (pAttack * 1.30);
            }
            if (eElement.equals("Air")) {
                pAttack = (int) (pAttack * .85);
            }
        }

        if (pAElement.equals("Water")) {
            if (eElement.equals("Earth")) {
                pAttack = (int) (pAttack * .70);
            }
            if (eElement.equals("Fire")) {
                pAttack = (int) (pAttack * 1.30);
            }
            if (eElement.equals("Air")) {
                pAttack = (int) (pAttack * .85);
            }
        }

        if (pAElement.equals("Earth")) {
            if (eElement.equals("Fire")) {
                pAttack = (int) (pAttack * .70);
            }
            if (eElement.equals("Water")) {
                pAttack = (int) (pAttack * 1.30);
            }
            if (eElement.equals("Air")) {
                pAttack = (int) (pAttack * .85);
            }
        }

        if (pAElement.equals("Air")) {
            Random gen = new Random();
            int chance = gen.nextInt(100);
            if (chance % 17 == 0 || chance % 37 == 0 || chance % 23 == 0) {
                pAttack = (int) (pAttack * 2.17);
            } else if (chance % 3 == 0 || chance % 5 == 0 || chance % 7 == 0) {
                pAttack = (int) (pAttack * 1.15);
            } else if (chance % 4 == 0 || chance % 11 == 0) {
                pAttack = (int) (pAttack * 1.05);
            } else {
                pAttack = (int) (pAttack * .78);
            }
        }

        if (pAElement.equals("None")) {
            if (!(eElement.equals("None"))) {
                pAttack = (int) (pAttack * .90);
            }
        }

        if (eElement.equals("None")) {
            if (!(pDElement.equals("None"))) {
                eAttack = (int) (eAttack * .90);
            }
        }

        if (eElement.equals("Fire")) {
            if (!(pDElement.equals("Earth"))) {
                eAttack = (int) (eAttack * 1.30);
            }
            if (pDElement.equals("Water")) {
                eAttack = (int) (eAttack * .70);
            }
            if (pDElement.equals("Air")) {
                eAttack = (int) (eAttack * .85);
            }
        }

        if (eElement.equals("Water")) {
            if (!(pDElement.equals("Fire"))) {
                eAttack = (int) (eAttack * 1.30);
            }
            if (pDElement.equals("Earth")) {
                eAttack = (int) (eAttack * .70);
            }
            if (pDElement.equals("Air")) {
                eAttack = (int) (eAttack * .85);
            }
        }

        if (eElement.equals("Earth")) {
            if (!(pDElement.equals("Water"))) {
                eAttack = (int) (eAttack * 1.30);
            }
            if (pDElement.equals("Fire")) {
                eAttack = (int) (eAttack * .70);
            }
            if (pDElement.equals("Air")) {
                eAttack = (int) (eAttack * .85);
            }
        }

        if (eElement.equals("Air")) {
            if (!(pDElement.equals("Air"))) {
                eAttack = (int) (eAttack * 1.15);
            }
        }

        int heal = (int) (Math.random() * 5);

        if (heal == 0 && e instanceof Boss) {
            ((Boss) e).heal();
        } else {
            if (Math.random() * 10 <= 9) {
                p.takeDamage(eAttack - p.getDefense());
            }
        }

        if (Math.random() * 10 <= 9) {
            e.takeDamage(pAttack - e.getDefense());
        }

        if (heal == 0 && e instanceof Boss) {
            return -37;
        }

        if (pAttack - e.getDefense() < 0) {
            return 0;
        }

        return pAttack - e.getDefense();
    }

    /**
     * The player tries to run away but there is a chance that they fail
     *
     * @param p = the player
     * @param e = the enemy
     */
    public static boolean runAway(Player p, Enemy e) {
        int eAttack = (e.getAttack() / 2) + (int) ((e.getAttack() / 2) * (Math.random() * 2 + .5));
        if ((Math.random() * 4) > 3) {
            p.takeDamage((eAttack - p.getDefense()) / 5 + (int) (Math.random() * (eAttack / 10)));
            return false;
        }
        return true;
    }

    public static void rewardPlayer(Player p, Enemy e) {

        if (e instanceof Boss) {        
      
            int onepower = p.getInventory().getEquipment()[0].getAttackBoost() +
            p.getInventory().getEquipment()[0].getDefenseBoost();
            
            int twopower = p.getInventory().getEquipment()[1].getAttackBoost() +
            p.getInventory().getEquipment()[1].getDefenseBoost();
            
            if (onepower > twopower)
                p.getInventory().getEquipment()[0] = new Equipment("Ninja Abs", 0, 1, "None");
            else
                p.getInventory().getEquipment()[1] = new Equipment("Bare-Hands", 1, 0, "None");

            
            Equipment[] loot = ((Boss) (e)).getLoot();

            for (int i = 0; i < loot.length; i++) {
                // SHOULD BE AN OPTION IN GUI TO SELL LOOT / ITEMS IN INVENT;
                p.getInventory().addItem(loot[i]);
            }
        }
    }
}