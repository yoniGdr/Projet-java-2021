package game.resource;

import game.*;

/** Classe qui représente la 
 * ressource Rock
 */
public class Rock extends Resource {

    /** Constructeur de la class Rock
     * 
     * @param value : valeur de la ressource 
     */
    public Rock(int value) {
        super(value);
    }

    /** Méthode qui permet de tester l'egalité 
     * @param o l'objet que l'ont veut tester
     * @return boolean : false si pas égaux
     */
    public boolean equals(Object o) {
        if (o instanceof Rock) {
            Resource o1 = (Rock) o;
            return this.value == o1.getValue();
        }
        return false;
    }

    
    /** Méthode qui return string 'Rock'
     * @return String
     */
    public String toString() {
        return "Rock";
    }
}
