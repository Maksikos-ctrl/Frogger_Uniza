package game;

import javax.swing.*;
import entities.Platforma;
import entities.Auto;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Trieda predstavuje hlavnú hernú plochu.
 * Zodpovedá za vykresľovanie objektov, detekciu kolízií a ovládanie hry.
 */
public class HernaPanel extends JPanel implements KeyListener {
    private int frogX = 500; // Pozícia žaby na osi X
    private int frogY = 700;  // Pozícia žaby na osi Y
    private int frogSpeed = 50; // Rýchlosť pohybu žaby

    private boolean isFrogAlive = true; // Indikátor, či je žaba nažive
    private boolean gameOver = false; // Indikátor, či je hra ukončená
    private int score = 0; // Body hráča
    private int lives = 3; // Počet životov hráča
    
    private boolean onLog = false; // Indikátor, či je žaba na kmeni
    private Platforma currentLog = null; // Aktuálny kmeň, na ktorom stojí žaba



    private BufferedImage frogImage;
    private BufferedImage carImage;
    private BufferedImage logImage;
    private BufferedImage backgroundImage;
    

    private ArrayList<Platforma> platforms;
    private ArrayList<Auto> vehicles;

    
    /**
     * Konštruktor inicializuje hernú plochu a objekty v hre.
     */

    public HernaPanel() {
        setPreferredSize(new Dimension(Konstanty.WINDOW_WIDTH, Konstanty.WINDOW_HEIGHT));
        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(this);

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resources/bg.png"));
            if (backgroundImage == null) System.out.println("backgroundImage is null!");

            frogImage = ImageIO.read(getClass().getResource("/resources/frog.png"));
            if (frogImage == null) System.out.println("frogImage is null!");

            carImage = ImageIO.read(getClass().getResource("/resources/car.png"));
            if (carImage == null) System.out.println("carImage is null!");

            logImage = ImageIO.read(getClass().getResource("/resources/log.png"));
            if (logImage == null) System.out.println("logImage is null!");

            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("chyba sa stala!");
        }

        platforms = new ArrayList<>();
        vehicles = new ArrayList<>();

        vehicles.add(new Auto(200, 470, 120, 60, 4, carImage));
        vehicles.add(new Auto(600, 520, 120, 60, -3, carImage));
        vehicles.add(new Auto(900, 500, 120, 60, 2, carImage));  

        platforms.add(new Platforma(100, 260, 200, 50, 3, logImage)); 
        platforms.add(new Platforma(400, 290, 200, 50, -2, logImage));
        platforms.add(new Platforma(700, 270, 200, 50, 2, logImage)); 


        Timer timer = new Timer(30, e -> update());
        timer.start();
    }

     /**
     * Vykresľuje hernú plochu, objekty a stav hry.
     * 
     * @param g Grafický objekt pre vykresľovanie.
     */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vykreslenie pozadia
        g.drawImage(backgroundImage, 0, 0, Konstanty.WINDOW_WIDTH, Konstanty.WINDOW_HEIGHT, null);

       // Vykreslenie objektov
        for (Auto vehicle : vehicles) {
            vehicle.draw(g);
        }

       // Vykreslenie bŕvien
        for (Platforma platform : platforms) {
            platform.draw(g);
        }

        // Vykreslenie žaby
        if (onLog && currentLog != null) {
            g.drawImage(frogImage, frogX, currentLog.getY(), 80, 80, null);
        } else if (!gameOver) {
            g.drawImage(frogImage, frogX, frogY, 80, 80, null);
        }

        // Vykreslenie skóre a životov
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, Konstanty.WINDOW_WIDTH - 150, 30);
        g.drawString("Lives: " + lives, Konstanty.WINDOW_WIDTH - 150, 60);

        // Vykreslenie Game Over obrazovky
        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Konstanty.WINDOW_WIDTH, Konstanty.WINDOW_HEIGHT);

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", Konstanty.WINDOW_WIDTH / 2 - 150, Konstanty.WINDOW_HEIGHT / 2 - 50);

            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Final Score: " + score, Konstanty.WINDOW_WIDTH / 2 - 100, Konstanty.WINDOW_HEIGHT / 2 + 50);
        }
    }








     /**
     * Aktualizuje stav hry (pohyb objektov, detekcia kolízií).
     */
    public void update() {
        if (gameOver) return;
    
        // ak je žaba na břevně, posuneme ji spolu s břevnem
        if (onLog && currentLog != null) {
            frogX += currentLog.getSpeed(); // Posun žaby spolu s břevnem
            if (!currentLog.getBounds().intersects(new Rectangle(frogX, frogY, 60, 60))) {
                onLog = false;
                currentLog = null;
            }
        }
    
        // Kontrola kolízií s autami
        for (Auto vehicle : vehicles) {
            if (new Rectangle(vehicle.getX(), vehicle.getY(), vehicle.getWidth(), vehicle.getHeight())
                    .intersects(new Rectangle(frogX, frogY, 60, 60))) {
                playSound("/resources/collision.wav");
                lives--;
                if (lives <= 0) {
                    triggerGameOver();
                } else {
                    resetFrogPosition();
                }
                return;
            }
        }
    
        // Kontrola kolízií s břevny
        if (!onLog && frogY > 220 && frogY < 380) {
            playSound("/resources/fall.wav");
            lives--;
            if (lives <= 0) {
                triggerGameOver();
            } else {
                resetFrogPosition();
            }
            return;
        }
    
        // Kontrola kolízií s břevny
        if (frogY < 60) {
            score += 10; // pridáme body
            playSound("/resources/score.wav"); 
            resetFrogPosition(); // resetujeme poziciu žaby
            return;
        }
    
        // Aktualizujeme objekty
        vehicles.forEach(Auto::update);
        platforms.forEach(Platforma::update);
    
        repaint();
    }
    
    
      
    /**
     * Spracováva stlačené klávesy pre pohyb žaby.
     * 
     * @param e Udalosť stlačenia klávesy.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                resetGame();
            }
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Žaba skáče a robi dvoj-násobný krok
            frogY -= frogSpeed * 2; // Skok žaby

            // Kontrola kolízií s břevny
            for (Platforma platform : platforms) {
                if (platform.getBounds().intersects(new Rectangle(frogX, frogY, 60, 60))) {
                    // Žaba je na břevně
                    onLog = true;
                    currentLog = platform;
                    System.out.println("Zaba je na brevne!"); 
                    return;
                }
            }

            // ak žaba neskočila na břevno, kontrolujeme kolíziu s vodou
            if (!onLog && frogY > 220 && frogY < 380) {
                lives--;
                playSound("/resources/fall.wav");
                if (lives <= 0) {
                    triggerGameOver();
                } else {
                    resetFrogPosition();
                }
            }
            return;
        }

        // ak žaba je na břevne, nemôže sa pohybovať
        if (!onLog) {
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

            // Kontrola hraníc okna
            frogX = Math.max(0, Math.min(frogX, Konstanty.WINDOW_WIDTH - 60));
            frogY = Math.max(0, Math.min(frogY, Konstanty.WINDOW_HEIGHT - 60));
        }
    }


    

   /**
     * Resetuje hru na začiatočný stav.
     */

    private void resetGame() {
        frogX = 500;
        frogY = 700;
        gameOver = false;
        isFrogAlive = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}


    /**
     * Resetuje pozíciu žaby na východiskovú.
     */
    private void resetFrogPosition() {
        frogX = 500; 
        frogY = 700; 
        onLog = false;
        currentLog = null;
    }
    

    private void respawnFrog() {
        frogX = 500;
        frogY = 700;
    }

    /**
     * Aktivuje stav "Game Over".
     */

    private void triggerGameOver() {
        gameOver = true;
        playSound("/resources/gameover.wav"); 
        repaint();
    }

     /**
     * Prehráva zvukový efekt z určeného súboru.
     * 
     * @param filePath Cesta k súboru so zvukovým efektom.
     */

    private void playSound(String filePath) {
        try {
            URL resource = getClass().getResource(filePath);
            if (resource == null) {
                System.out.println("Subor nebol najdeny: " + filePath);
                return;
            }
            File soundFile = new File(resource.toURI());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    

}
