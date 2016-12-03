 

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Nate
 */
public class GUI extends JFrame {

    private MapPanel mp;
    private InventoryPanel ip;
    private MerchantPanel merp;
    private BattlePanel bp;
    private JPanel jp;

    public GUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jp = new JPanel();
        this.add(jp);
    }

    public int dispMap(Map m, Point ulc) {
        remove(jp);
        mp = new MapPanel(m, ulc);
        jp = mp;
        add(jp);
        mp.setPreferredSize(new Dimension(480, 480));
        pack();
        mp.paintComponents(mp.getGraphics());
        GUI.MapKeys mk = new GUI.MapKeys();
        mp.addKeyListener(mk);
        mp.grabFocus();
        return mk.getCode();
    }

    public int updateMap(ArrayList<Point> points) {
        mp.update(mp.getGraphics(), points);
        GUI.MapKeys mk = new GUI.MapKeys();
        mp.addKeyListener(mk);
        mp.grabFocus();
        return mk.getCode();
    }

    public int easyUpdateMap(ArrayList<Point> points) {
        mp.update(mp.getGraphics(), points);
        return 0;
    }

    public int easyDispMap(Map m, Point ulc) {
        remove(jp);
        mp = new MapPanel(m, ulc);
        jp = mp;
        add(jp);
        mp.setPreferredSize(new Dimension(480, 480));
        pack();
        mp.paintComponents(mp.getGraphics());
        return 0;
    }

    public void dispInventory(Player p) {
        remove(jp);
        ip = new InventoryPanel(p);
        jp = ip;
        ip.setPreferredSize(new Dimension(480, 269));
        add(jp);
        pack();
        ip.waits1();
    }

    public void dispMerchant(Player p, Merchant m) {
        remove(jp);
        merp = new MerchantPanel(m, p);
        jp = merp;
        jp.setPreferredSize(new Dimension(565, 260));
        add(jp);
        pack();
        merp.waits1();
    }

    public void dispBattle(Player p, Enemy e) {
        remove(jp);
        bp = new BattlePanel(p, e);
        jp = bp;
        jp.setPreferredSize(new Dimension(540, 267));
        add(jp);
        pack();
        bp.waits1();
    }

    public void dispText(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    private class MapKeys extends KeyAdapter {

        public int x;

        public void keyPressed(KeyEvent e) {
            putCode(e.getKeyCode());
        }

        synchronized void putCode(int n) {
            x = n;
            notifyAll();
        }

        synchronized int getCode() {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
            return x;
        }
    }
}
/**
 * Allons-y
 */
