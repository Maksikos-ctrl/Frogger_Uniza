/* 
 * Tento súbor obsahuje hlavnú logiku hry.
 * Zodpovedá za aktualizáciu herného stavu, spracovanie kolízií a ovládanie herných udalostí.
 */

package game;

import java.util.ArrayList;
import entities.Frog;
import entities.Obstacle;

public class Game {
    private Frog frog;
    private ArrayList<Obstacle> obstacles;
    private boolean running;

    public Game() {
        initGame();
    }

    /**
     * Inicializuje hru, vytvára hráča a prekážky.
    */
    private void initGame() {
        frog = new Frog();
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
        for (Obstacle obstacle : obstacles) {
            obstacle.update();

            // Kontrola kolízií
            if (frog.intersects(obstacle)) {
                running = false; // Zastavenie hry pri kolízii
            }
        }
    }

    public Frog getFrog() {
        return frog;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public boolean isRunning() {
        return running;
    }
}
