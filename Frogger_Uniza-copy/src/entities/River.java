package entities;

import java.awt.*;
import java.util.ArrayList;

public class River {
    private int y; // Vertikálna pozícia rieky
    private ArrayList<Platform> platforms; // Plošiny (napr. guľatiny)

    /**
     * Inicializuje rieku na danej vertikálnej pozícii.
     * 
     * @param y Vertikálna pozícia rieky.
     */
    public River(int y) {
        this.y = y;
        this.platforms = new ArrayList<>();

        // Pridáme plošiny na rieku (napr. guľatiny)
        initPlatforms();
    }

    /**
     * Inicializuje plošiny v rieke.
     */
    private void initPlatforms() {
        // Príklad pridania plošín s rôznymi rýchlosťami a pozíciami
        platforms.add(new Platform(100, y, 80, 30, 2)); // Plošina vľavo doprava
        platforms.add(new Platform(300, y, 100, 30, -2)); // Plošina sprava doľava
        platforms.add(new Platform(600, y, 90, 30, 1)); // Plošina vľavo doprava
    }

    /**
     * Aktualizuje pozíciu plošín.
     */
    public void update() {
        for (Platform platform : platforms) {
            platform.update();
        }
    }

    /**
     * Nakreslí rieku a plošiny na obrazovku.
     * 
     * @param g Grafický objekt na kreslenie.
     */
    public void draw(Graphics g) {
        // Kreslenie rieky (pozadie)
        g.setColor(new Color(0, 100, 255)); // Modrá farba pre rieku
        g.fillRect(0, y, 800, 50);

        // Kreslenie plošín (guľatiny)
        for (Platform platform : platforms) {
            platform.draw(g);
        }
    }

    /**
     * Kontroluje, či sa žaba nachádza na niektorej z plošín.
     * 
     * @param frog Objekt žaby na kontrolu.
     * @return true, ak sa žaba nachádza na plošine, inak false.
     */
    public boolean isFrogOnPlatform(Frog frog) {
        for (Platform platform : platforms) {
            if (platform.getBounds().intersects(frog.getBounds())) {
                frog.move(platform.getSpeed(), 0); // Žaba sa hýbe spolu s plošinou
                return true;
            }
        }
        return false;
    }

    /**
     * Získava vertikálnu pozíciu rieky.
     * 
     * @return Vertikálna pozícia rieky.
     */
    public int getY() {
        return y;
    }
}
