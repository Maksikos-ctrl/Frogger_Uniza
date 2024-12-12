/* 
 * Tento súbor obsahuje hlavnú logiku hry.
 * Zodpovedá za aktualizáciu herného stavu, spracovanie kolízií a ovládanie herných udalostí.
 */

package game;

import java.util.ArrayList;
import entities.Zaba;
import entities.Drevo;

public class Hra {
    private Zaba frog;
    private ArrayList<Drevo> obstacles;
    private boolean running;

    public Hra() {
        initGame();
    }

    /**
     * Inicializuje hru, vytvára hráča a prekážky.
    */
    private void initGame() {
        frog = new Zaba();
        obstacles = new ArrayList<>();
        // Pridanie prekážok
        // Napríklad: obstacles.add(new Vehicle(x, y, speed));
        running = true;
    }

    /**
     * Aktualizuje stav hry vrátane hráča a prekážok.
    */
    public void update() {
        if (!running) return;

        // Aktualizácia hráča a prekážok
        frog.update();
        for (Drevo obstacle : obstacles) {
            obstacle.update();

            // Kontrola kolízií
            if (frog.intersects(obstacle)) {
                running = false; // Zastavenie hry pri kolízii
            }
        }
    }

    public Zaba getFrog() {
        return frog;
    }

    public ArrayList<Drevo> getObstacles() {
        return obstacles;
    }

    public boolean isRunning() {
        return running;
    }
}
