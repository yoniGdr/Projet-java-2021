package game.tile;

import game.*;
import game.Character;
import game.resource.Sand;
import game.util.exception.TooManyCharacterforDesert;
import game.util.exception.TooManyCharacterforMountain;
import game.util.exception.WrongSizeCharacterException;

//import game.util.*;

/** Classe qui représente la tuile Desert
 */
public class Desert extends UsableTile {
    /** Constructueur de la classe Desert
     * @param xPosition Position en X de la tuile 
     * @param yPosition Position en Y de la tuile 
     */
    public Desert(int xPosition, int yPosition) {
        super(xPosition, yPosition);
    }

    /** return the resource SAND
     * @return sand 
     */
    @Override
    public Resource getRessource(int value) {
        return new Sand(value);
    }

    /** return 5 of gold
     * @return 5
     */
    @Override
    public int getGold() {
        return 5;
    }

    /** return string 'D'
     * @return D
     */
    @Override
    public String toString() {
        return "D ";
    }

    /** Méthode qui permet d'ajouter un character
     * @param character : le charactere
     */
    @Override
    public void welcomeCharacter(Character character)
        throws TooManyCharacterforDesert, TooManyCharacterforMountain, WrongSizeCharacterException {
        if (character.getSize() > 3) {
            throw new TooManyCharacterforDesert(
                "Trop de guerrier pour le tuile Desert"
            );
        }
        super.welcomeCharacter(character);
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

