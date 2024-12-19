/* 
 * Tento súbor obsahuje logiku hráča (žaby).
 * Zodpovedá za pohyb, kreslenie a kolízie s inými objektmi.
 */

package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * Trieda predstavuje hráča (žabu).
 * Obsahuje logiku pohybu, vykresľovania a kolízií.
 */
public class Zaba {
    private int x, y; // Pozícia žaby
    private static final int SIZE = 30; // Veľkosť žaby
    private BufferedImage frogImage; // Obrázok žaby

    /**
     * Konštruktor inicializuje žabu.
     */
   public Zaba() {
        x = 400;
        y = 550;

        try {
            frogImage = ImageIO.read(getClass().getResource("/resources/frog.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (frogImage != null) {
            g.drawImage(frogImage, x, y, SIZE, SIZE, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, SIZE, SIZE);
        }
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
    public boolean intersects(Drevo obstacle) {
        return getBounds().intersects(obstacle.getBounds());
    }

    public void getY() {
        return this.y;

    }

    public void getI() {
        return this.i;

    }
}
