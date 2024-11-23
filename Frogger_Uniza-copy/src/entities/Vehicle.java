/* 
 * Tento súbor obsahuje logiku vozidiel (prekážok).
 * Aktualizuje ich pohyb a kontroluje, či nevychádzajú za okraje okna.
 */

 package entities;

 public class Vehicle extends Obstacle {
    private int speed;
 
    public Vehicle(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        this.speed = speed;
    }
 
    @Override
    public void update() {
        x += speed;
 
        // Ak vozidlo vyjde mimo obrazovky, objaví sa na opačnej strane
        if (x > 800) x = -width;
        if (x < -width) x = 800;
     }
 }
 