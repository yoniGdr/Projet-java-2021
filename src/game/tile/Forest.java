package game.tile;

import game.*;
import game.Character;
import game.resource.Wood;

//import game.util.*;

/** Classe qui représente la tuile Foret
 */
public class Forest extends UsableTile {
    /** Constructueur de la classe Forest
     * @param xPosition Position en X de la tuile 
     * @param yPosition Position en Y de la tuile 
     */
    public Forest(int xPosition, int yPosition) {
        super(xPosition, yPosition);
    }

    /** return the resource Wood
     * @return wood
     */
    @Override
    public Resource getRessource(int value) {
        return new Wood(value);
    }

    /** return 2 of gold
     * @return 2
     */
    @Override
    public int getGold() {
        return 2;
    }
    /** return string 'F'
     * @return F
     */
    @Override
    public String toString() {
        return "F ";
    }
    /** Méthode qui permet d'acceder a l'attribut 
     * character
     * @return le character
     */
    @Override
    public Character getCharacter() {
        return this.character;
    }
}
