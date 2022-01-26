/**
 * thanks Diawara
 */
package game.util;

import java.util.Random;

/**
 * generation d'entier aleatoire d'un intervalle donné
 *
 */
public class MyRandom {

    /**
     * allow to give a random integer beetween inf and sup.
     * Be carreful, inf and sup are included. [inf, sup]
     * @param inf the left born of the interval
     * @param sup the right born
     * @return
     */
    public int randomInt(int inf, int sup) {
        int randomInt = inf + (int) (Math.random() * ((sup - inf) + 1));
        return randomInt;
    }
}
