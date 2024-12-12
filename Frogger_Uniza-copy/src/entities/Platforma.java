package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Konstanty;
/**
 * Trieda predstavuje plávajúce platformy (kmene).
 * Obsahuje logiku pre pohyb a kontrolu kolízií.
 */
public class Platforma extends Drevo {
    private int speed; // Rýchlosť platformy

     /**
     * Konštruktor inicializuje platformu.
     * 
     * @param x Počiatočná pozícia na osi X.
     * @param y Počiatočná pozícia na osi Y.
     * @param width Šírka platformy.
     * @param height Výška platformy.
     * @param speed Rýchlosť pohybu platformy.
     * @param image Obrázok platformy.
     */
    public Platforma(int x, int y, int width, int height, int speed, BufferedImage image) {
        super(x, y, width, height, image);
        this.speed = speed;
    }

     /**
     * Aktualizuje pozíciu platformy.
     */
    @Override
    public void update() {
        x += speed;
        if (x > Konstanty.WINDOW_WIDTH) {
            x = -width;
        } else if (x + width < 0) {
            x = Konstanty.WINDOW_WIDTH;
        }
    }


     /**
     * @return Obdĺžnik reprezentujúci hranice platformy.
     */
    @Override


    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getSpeed() {
        return speed;
    }
}
