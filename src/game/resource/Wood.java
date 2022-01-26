package game.resource;

import game.*;

/** Classe qui représente la 
 * ressource Wood
 */
public class Wood extends Resource {

    /** Constructeur de la class Wood
     * 
     * @param value : valeur de la ressource 
     */
    public Wood(int value) {
        super(value);
    }

    /** Méthode qui permet de tester l'egalité 
     * @param o l'objet que l'ont veut tester
     * @return boolean : false si pas égaux
     */
    public boolean equals(Object o) {
        if (o instanceof Wood) {
            Resource o1 = (Wood) o;
            return this.value == o1.getValue();
        }
        return false;
    }

    /** Méthode qui return string 'Wood'
     * @return String
     */
    public String toString() {
        return "Wood";
    }
}
