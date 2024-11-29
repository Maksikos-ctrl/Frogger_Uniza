/* 
 * Tento súbor obsahuje logiku hráča (žaby).
 * Zodpovedá za pohyb, kreslenie a kolízie s inými objektmi.
 */

package entities;

import java.awt.*;

public class Frog {
    private int x, y;
    private static final int SIZE = 30;

    public Frog() {
        // Počiatočná pozícia žaby
        x = 400;
        y = 550;
    }

    /**
     * Aktualizuje polohu hráča.
    */
    public void update() {
        // Logika pohybu bude pridaná neskôr
    }

    /**
     * Nakreslí žabu na obrazovku.
    * 
    * @param g Grafický objekt na kreslenie.
    */
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, SIZE, SIZE);
    }

    /**
     * Posunie žabu v danom smere.
    * 
    * @param dx Posun na osi X.
    * @param dy Posun na osi Y.
    */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    /**
     * Kontroluje, či sa žaba zrazila s prekážkou.
    * 
    * @param obstacle Prekážka na kontrolu kolízie.
    * @return true, ak sa objekty prekrývajú, inak false.
    */
    public boolean intersects(Obstacle obstacle) {
        return getBounds().intersects(obstacle.getBounds());
    }
}
