package entities;

import game.Konstanty;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Trieda predstavuje autá v hre.
 * Obsahuje logiku pre pohyb a vykresľovanie áut.
 */
public class Auto extends Drevo {
    private int speed;  // Rýchlosť auta

     /**
     * Konštruktor inicializuje auto.
     * 
     * @param x Počiatočná pozícia na osi X.
     * @param y Počiatočná pozícia na osi Y.
     * @param width Šírka auta.
     * @param height Výška auta.
     * @param speed Rýchlosť auta.
     * @param carImage Obrázok auta.
     */
    public Auto(int x, int y, int width, int height, int speed, Image carImage) {
        super(x, y, width, height, carImage);
        this.speed = speed;
    }

      /**
     * Vykresľuje auto na obrazovku.
     * 
     * @param g Grafický objekt na vykresľovanie.
     */

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            System.out.println("Vehicle image is null!");
        }
    }

    
    /**
     * Aktualizuje pozíciu auta.
     */

    public void update() {
        x += speed;

        
        if (x > Konstanty.WINDOW_WIDTH && speed > 0) {
            x = -width;
        } else if (x < -width && speed < 0) {
            x = Konstanty.WINDOW_WIDTH;
        }
    }

    /**
     * @return Obdĺžnik reprezentujúci hranice auta.
     */

    @Override
    protected Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
