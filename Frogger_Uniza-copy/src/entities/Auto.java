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
    private int rychlost;  // Rýchlosť auta

     /**
     * Konštruktor inicializuje auto.
     * 
     * @param x Počiatočná pozícia na osi X.
     * @param y Počiatočná pozícia na osi Y.
     * @param sirka Šírka auta.
     * @param vyska Výška auta.
     * @param rychlost Rýchlosť auta.
     * @param carImage Obrázok auta.
     */
    public Auto(int x, int y, int sirka, int vyska, int rychlost, Image carImage) {
        super(x, y, sirka, vyska, carImage);
        this.rychlost = rychlost;
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
            System.out.println("Obrazok auta je null!");
        }
    }

    
    /**
     * Aktualizuje pozíciu auta.
     */

    public void update() {
        x += rychlost;

        
        if (x > Konstanty.SIRKA_OKNA && rychlost > 0) {
            x = -width;
        } else if (x < -width && rychlost < 0) {
            x = Konstanty.SIRKA_OKNA;
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
