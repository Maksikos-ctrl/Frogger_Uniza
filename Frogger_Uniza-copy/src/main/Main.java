/* 
 * Tento súbor predstavuje vstupný bod aplikácie.
 * Zodpovedá za vytvorenie hlavného okna (JFrame) a spustenie hry.
 */

package main;

import javax.swing.*;

import game.HernyPanel;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frogger");
        HernyPanel panel = new HernyPanel();

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

      
        
    }
}

 