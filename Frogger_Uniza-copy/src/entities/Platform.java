package entities;

import java.awt.*;

/**
 * Tento súbor obsahuje logiku plošiny (napr. guľatiny).
 * Používa sa ako pohyblivý objekt v rieke.
 */
public class Platform {
    private int x, y, width, height, speed;

    /**
     * Inicializuje pohyblivú plošinu.
     * 
     * @param x Počiatočná pozícia na osi X.
     * @param y Počiatočná pozícia na osi Y.
     * @param width Šírka plošiny.
     * @param height Výška plošiny.
     * @param speed Rýchlosť pohybu plošiny.
     */
    public Platform(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    /**
     * Aktualizuje pozíciu plošiny.
     */
    public void update() {
        x += speed;

        // Opätovný vstup plošiny na opačnú stranu obrazovky
        if (x > 800) x = -width;
        if (x < -width) x = 800;
    }

    /**
     * Nakreslí plošinu na obrazovku.
     * 
     * @param g Grafický objekt na kreslenie.
     */
    public void draw(Graphics g) {
        g.setColor(new Color(139, 69, 19)); // Hnedá farba pre guľatinu
        g.fillRect(x, y, width, height);
    }

    /**
     * Získava hranice plošiny.
     * 
     * @return Objekt Rectangle pre kontrolu kolízie.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Získava rýchlosť pohybu plošiny.
     * 
     * @return Rýchlosť pohybu plošiny.
     */
    public int getSpeed() {
        return speed;
    }
}
