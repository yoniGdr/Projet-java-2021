package game.tile;

//import game.util.*;
import game.*;
import game.Character;
import game.resource.Rock;
import game.util.exception.*;

/** cette classe repréésente la tuile Montagne
 */
public class Mountain extends UsableTile {
    /** Constructueur de la classe Mountain
     * @param xPosition Position en X de la tuile 
     * @param yPosition Position en Y de la tuile 
     */
    public Mountain(int xPosition, int yPosition) {
        super(xPosition, yPosition);
    }

    /** return the resource Rock
     * @return Rock
     */

    @Override
    public Resource getRessource(int value) {
        return new Rock(value);
    }

    /** return 8 of gold
     * @return 8
     */
    @Override
    public int getGold() {
        return 8;
    }
    /** return string M
     * @return M
     */
    @Override
    public String toString() {
        return "M ";
    }
    /** Méthode qui permet d'ajouter un character
     * @param character : le charactere
     */
    @Override
    public void welcomeCharacter(Character character)
        throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
        if (character.getSize() > 3) {
            throw new TooManyCharacterforMountain(
                "Trop de guerrier pour une tuile Montagne"
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
