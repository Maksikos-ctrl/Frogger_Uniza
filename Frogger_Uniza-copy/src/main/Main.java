/* 
 * Tento súbor predstavuje vstupný bod aplikácie.
 * Zodpovedá za vytvorenie hlavného okna (JFrame) a spustenie hry.
 */

package main;

import javax.swing.*;

import game.HernaPanel;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frogger");
        HernaPanel panel = new HernaPanel();

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

      
        
    }
}

 