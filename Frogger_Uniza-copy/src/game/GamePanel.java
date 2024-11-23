package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private int frogX = 400;
    private int frogY = 550; 
    private int frogSpeed = 30; 
    private boolean isFrogAlive = true; 

    private int carX = 200;
    private int carSpeed = 2;
    private int logX = 400;
    private int logSpeed = 1;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true); 
        addKeyListener(this); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isFrogAlive) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", 300, 250);
            return;
        }

        g.setColor(Color.GRAY);
        g.fillRect(0, 300, 800, 100);

        g.setColor(Color.WHITE);
        for (int i = 0; i < 800; i += 80) {
            g.fillRect(i, 345, 40, 10);
        }

        g.setColor(new Color(0, 100, 255));
        g.fillRect(0, 200, 800, 100);

        g.setColor(Color.RED);
        g.fillRect(carX, 320, 60, 30);

        g.setColor(new Color(139, 69, 19));
        g.fillRect(logX, 220, 100, 30);

        g.setColor(Color.GREEN);
        g.fillRect(frogX, frogY, 30, 30);
    }

    public void update() {
        if (!isFrogAlive) return;

        carX += carSpeed;
        if (carX > 800) carX = -60;

        logX -= logSpeed;
        if (logX < -100) logX = 800;

        if (new Rectangle(carX, 320, 60, 30).intersects(new Rectangle(frogX, frogY, 30, 30))) {
            isFrogAlive = false;
        }

        if (frogY > 200 && frogY < 300) {
            if (!isFrogOnLog()) {
                isFrogAlive = false;
            }
        }

        repaint();
    }

    public boolean isFrogOnLog() {
        return frogX >= logX && frogX <= logX + 100 && frogY >= 220 && frogY <= 250;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!isFrogAlive) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: 
            case KeyEvent.VK_W:
                frogY -= frogSpeed;
                break;
            case KeyEvent.VK_DOWN: 
            case KeyEvent.VK_S:
                frogY += frogSpeed;
                break;
            case KeyEvent.VK_LEFT: 
            case KeyEvent.VK_A:
                frogX -= frogSpeed;
                break;
            case KeyEvent.VK_RIGHT: 
            case KeyEvent.VK_D:
                frogX += frogSpeed;
                break;
        }

        if (frogX < 0) frogX = 0;
        if (frogX > 770) frogX = 770;
        if (frogY < 0) frogY = 0;
        if (frogY > 570) frogY = 570;

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // В этом случае ничего не нужно делать
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Этот метод тоже не используется, оставим его пустым
    }
}
