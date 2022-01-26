package game.resource;

import game.*;

/** Classe qui représente la 
 * ressource Corn
 */
public class Corn extends Resource {

    /** Constructeur de la class Corn
     * 
     * @param value : valeur de la ressource 
     */
    public Corn(int value) {
        super(value);
    }

    /** Méthode qui permet de tester l'egalité 
     * @param o l'objet que l'ont veut tester
     * @return boolean : false si pas égaux
     */
    public boolean equals(Object o) {
        if (o instanceof Corn) {
            Resource o1 = (Corn) o;
            return this.value == o1.getValue();
        }
        return false;
    }

    
    /** Méthode qui return string 'Corn'
     * @return String
     */
    public String toString() {
        return "Corn";
    }
}
