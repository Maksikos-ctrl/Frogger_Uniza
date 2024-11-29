/* 
 * Tento súbor obsahuje pomocné metódy.
 * Môže sa používať na náhodné čísla alebo iné užitočné funkcie.
 */

package utils;

import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    /**
     * Generuje náhodné číslo v zadanom rozsahu.
    * 
    * @param min Minimálna hodnota.
    * @param max Maximálna hodnota.
    * @return Náhodné číslo medzi min a max (vrátane).
    */
    public static int randomInt(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }
}
 