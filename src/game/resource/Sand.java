package game.resource;

import game.*;

/** Classe qui représente la 
 * ressource Sand
 */
public class Sand extends Resource {

    /** Constructeur de la class Sand
     * 
     * @param value : valeur de la ressource 
     */
    public Sand(int value) {
        super(value);
    }

    /** Méthode qui permet de tester l'egalité 
     * @param o l'objet que l'ont veut tester
     * @return boolean : false si pas égaux
     */
    public boolean equals(Object o) {
        if (o instanceof Sand) {
            Resource o1 = (Sand) o;
            return this.value == o1.getValue();
        }
        return false;
    }

    /** Méthode qui return string 'Sand'
     * @return String
     */
    public String toString() {
        return "Sand";
    }
}
