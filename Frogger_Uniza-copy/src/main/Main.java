/* 
 * Tento súbor predstavuje vstupný bod aplikácie.
 * Zodpovedá za vytvorenie hlavného okna (JFrame) a spustenie hry.
 */

package main;

import javax.swing.*;

import game.GamePanel;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frogger");
        GamePanel panel = new GamePanel();

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

      
        Timer timer = new Timer(16, e -> panel.update());
        timer.start();
    }
}

 