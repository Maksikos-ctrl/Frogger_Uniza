package entities;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import utils.ImageUtils;

import javax.imageio.ImageIO;

public class Rieka {
    private int y;
    private ArrayList<Platforma> platforms;
    private BufferedImage riverImage;

    public Rieka(int y) {
        this.y = y;
        this.platforms = new ArrayList<>();

        try {
            riverImage = ImageIO.read(getClass().getResource("/resources/river.png"));
            riverImage = ImageUtils.resizeImage(riverImage, 800, 100); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        initPlatforms();
    }

    
    

    private void initPlatforms() {
        platforms.add(new Platforma(100, y, 80, 30, 2, riverImage));
        platforms.add(new Platforma(300, y, 100, 30, -2, riverImage));
        platforms.add(new Platforma(600, y, 90, 30, 1, riverImage));
    }

    public void update() {
        for (Platforma platform : platforms) {
            platform.update();
        }
    }

    public void draw(Graphics g) {
        // Рисуем реку
        if (riverImage != null) {
            g.drawImage(riverImage, 0, y, 800, 100, null);
        } else {
            g.setColor(new Color(0, 100, 255));
            g.fillRect(0, y, 800, 100);
        }
    
        // Рисуем бревна
        for (Platforma platform : platforms) {
            platform.draw(g);
        }
    }
    

    public boolean isFrogOnPlatform(Zaba frog) {
        for (Platforma platform : platforms) {
            if (platform.getBounds().intersects(frog.getBounds())) {
                frog.move(platform.getSpeed(), 0);
                return true;
            }
        }
        return false;
    }

    public int getY() {
        return y;
    }
}
