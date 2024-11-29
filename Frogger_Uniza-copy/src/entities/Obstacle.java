/* 
 * Tento súbor predstavuje základnú triedu pre všetky prekážky.
 * Poskytuje spoločnú logiku pre objekty, s ktorými sa hráč môže zraziť.
 */

package entities;

import java.awt.*;

public abstract class Obstacle {
    protected int x, y, width, height;

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    /**
     * Nakreslí prekážku na obrazovku.
    * 
    * @param g Grafický objekt na kreslenie.
    */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
 