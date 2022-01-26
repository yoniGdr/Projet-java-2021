package game.tile;

import game.*;
import game.Character;
import game.resource.Corn;

//import game.util.*;

/** Classe qui représente la tuile Plain
 */
public class Plain extends UsableTile {
    /** Constructueur de la classe Plain
     * @param xPosition Position en X de la tuile 
     * @param yPosition Position en Y de la tuile 
     */
    public Plain(int xPosition, int yPosition) {
        super(xPosition, yPosition);
    }

    /** return the resource Corn
     * @return Corn
     */
    @Override
    public Resource getRessource(int value) {
        return new Corn(value);
    }

    /** return 2 of gold
     * @return 2
     */
    @Override
    public int getGold() {
        return 2;
    }
    /** return string 'P'
     * @return P
     */
    @Override
    public String toString() {
        return "P ";
    }
    /** Méthode qui permet d'acceder a l'attribut charactere 
     * @return retourne le character
     */
    @Override
    public Character getCharacter() {
        // TODO Auto-generated method stub
        return this.character;
    }
}
