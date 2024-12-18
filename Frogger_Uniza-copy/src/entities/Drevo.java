package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import game.Konstanty;

public abstract class Drevo {
    protected int x, y, width, height;
    protected int speed;
    protected BufferedImage image;

    public Drevo(int x, int y, int width, int height, Image carImage) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = (BufferedImage) carImage;
    }

    

    public void update() {
        x += speed;
        // Ak objekt zmizne mimo obrazovky, presunie sa na druhú stranu
        if (x > Konstanty.SIRKA_OKNA) {
            x = -width;
        } else if (x + width < 0) {
            x = Konstanty.SIRKA_OKNA;
        }
    }


    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    // Gettre a settre
    public int getX() { 
        return x; 
    }

    public int getY() { 
        return y; 
    }

    public int getWidth() { 
        return width; 
    }

    public int getHeight() { 
        return height; 
    }

    public void setX(int x) { 
        this.x = x;
    }

    public void setY(int y) { 
        this.y = y; 
    }

    protected abstract Rectangle getBounds();
}
