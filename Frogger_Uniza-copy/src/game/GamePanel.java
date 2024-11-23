package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Táto trieda predstavuje hernú obrazovku, na ktorej sa zobrazuje hra.
 * Zahŕňa ovládanie žaby, pohyb áut, logov a detekciu kolízií.
 * 
 * @author [Tvoje meno]
 */
public class GamePanel extends JPanel implements KeyListener {
    private int frogX = 400; // Počiatočná horizontálna pozícia žaby
    private int frogY = 550; // Počiatočná vertikálna pozícia žaby
    private int frogSpeed = 30; // Rýchlosť pohybu žaby
    private boolean isFrogAlive = true; // Stav, či je žaba nažive

    private int carX = 200; // Počiatočná horizontálna pozícia auta
    private int carSpeed = 2; // Rýchlosť pohybu auta
    private int logX = 400; // Počiatočná horizontálna pozícia logu
    private int logSpeed = 1; // Rýchlosť pohybu logu

    /**
     * Konstruktor na inicializáciu herného panela.
     * Nastaví rozmer panela, pozadie a umožní interakciu cez klávesnicu.
     */
    public GamePanel() {
        setPreferredSize(new Dimension(800, 600)); // Nastavenie veľkosti panela
        setBackground(Color.BLACK); // Nastavenie pozadia na čiernu
        setFocusable(true); // Povolenie zamerania klávesnice
        addKeyListener(this); // Pripojenie KeyListener-a
    }

    /**
     * Kreslí herné objekty na panel (žaba, autá, logy a pozadie).
     * Ak žaba nie je nažive, zobrazí sa nápis "Game Over".
     * 
     * @param g Grafický objekt na kreslenie
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Zobrazenie správy o skončení hry
        if (!isFrogAlive) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", 300, 250);
            return;
        }

        // Kreslenie cesty
        g.setColor(Color.GRAY);
        g.fillRect(0, 300, 800, 100);

        // Kreslenie prekážok (čiary na ceste)
        g.setColor(Color.WHITE);
        for (int i = 0; i < 800; i += 80) {
            g.fillRect(i, 345, 40, 10);
        }

        // Kreslenie rieky
        g.setColor(new Color(0, 100, 255));
        g.fillRect(0, 200, 800, 100);

        // Kreslenie auta
        g.setColor(Color.RED);
        g.fillRect(carX, 320, 60, 30);

        // Kreslenie logu
        g.setColor(new Color(139, 69, 19));
        g.fillRect(logX, 220, 100, 30);

        // Kreslenie žaby
        g.setColor(Color.GREEN);
        g.fillRect(frogX, frogY, 30, 30);
    }

    /**
     * Metóda na aktualizáciu stavu hry (pohyb áut a logov, kontrola kolízií).
     * 
     * Ak žaba je mŕtva, prestane sa hra aktualizovať.
     */
    public void update() {
        if (!isFrogAlive) return; // Ak je žaba mŕtva, neaktualizujeme hru

        // Pohyb auta
        carX += carSpeed;
        if (carX > 800) carX = -60; // Ak auto prejde cez obrazovku, vráti sa na začiatok

        // Pohyb logu
        logX -= logSpeed;
        if (logX < -100) logX = 800; // Ak log prejde cez obrazovku, vráti sa na začiatok

        // Detekcia kolízie medzi žabou a autom
        if (new Rectangle(carX, 320, 60, 30).intersects(new Rectangle(frogX, frogY, 30, 30))) {
            isFrogAlive = false; // Žaba je zničená pri kolízii s autom
        }

        // Detekcia, či žaba je na logu
        if (frogY > 200 && frogY < 300) {
            if (!isFrogOnLog()) {
                isFrogAlive = false; // Žaba je zničená, ak nie je na logu
            }
        }

        repaint(); // Kreslenie novej obrazovky
    }

    /**
     * Skontroluje, či sa žaba nachádza na logu.
     * 
     * @return True, ak žaba je na logu
     */
    public boolean isFrogOnLog() {
        return frogX >= logX && frogX <= logX + 100 && frogY >= 220 && frogY <= 250;
    }

    /**
     * Ovládanie pohybu žaby pomocou klávesnice.
     * 
     * @param e Klávesová udalosť
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (!isFrogAlive) return; // Ak je žaba mŕtva, neumožníme ďalší pohyb

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: 
            case KeyEvent.VK_W:
                frogY -= frogSpeed; // Pohyb hore
                break;
            case KeyEvent.VK_DOWN: 
            case KeyEvent.VK_S:
                frogY += frogSpeed; // Pohyb dole
                break;
            case KeyEvent.VK_LEFT: 
            case KeyEvent.VK_A:
                frogX -= frogSpeed; // Pohyb doľava
                break;
            case KeyEvent.VK_RIGHT: 
            case KeyEvent.VK_D:
                frogX += frogSpeed; // Pohyb doprava
                break;
        }

        // Kontrola hraníc pohybu žaby
        if (frogX < 0) frogX = 0;
        if (frogX > 770) frogX = 770;
        if (frogY < 0) frogY = 0;
        if (frogY > 570) frogY = 570;

        repaint(); // Kreslenie novej obrazovky
    }

    /**
     * Tento metóda sa nevyužíva v tomto prípade.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // V tomto prípade nič nevyžaduje
    }

    /**
     * Tento metóda sa nevyužíva v tomto prípade.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Tento metóda sa necháva prázdna
    }
}
