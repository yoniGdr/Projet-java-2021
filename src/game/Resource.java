package game;
/** Classe abstraite qui gère les différentes 
* ressource présente dans les deux jeux
*/
public abstract class Resource {

    /**the value of the resource(in food or gold or another thing) after the conversion*/
    protected int value;

    /** Méthode qui defini une valeur 
     * @param value : valeur que l'ont va donner
     */
    public Resource(int value) {
        this.value = value;
    }
    
    /** Méthode qui permet d'acceder a la valeur
     * @return : valeur
     */
    public int getValue() {
        return this.value;
    }


}
