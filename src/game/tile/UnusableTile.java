package game.tile;

import game.*;
import game.Character;

//import game.util.*;

public abstract class UnusableTile implements Tile {

    protected int xPosition;
    protected int yPosition;

    public UnusableTile(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    /** Méthode qui permet d'obternir la postion X d'une tuile 
     * @return retourne la position de X
     */
    public int getXposition() {
        return this.xPosition;
    }
    /** Méthode qui permet d'obternir la postion Y d'une tuile 
     * @return retourne la position de Y
     */
    public int getYposition() {
        return this.yPosition;
    }
     /** Méthode qui permet d'ajouter un character
     * @param character
     * @throws TooManyCharacterforMountain
     *      déclanche l'exeption quand il y a trop de caractères pour la montagne
     * @throws TooManyCharacterforDesert
     *      déclanche l'exeption quand il y a trop de caractères pour le desert
     * @throws WrongSizeCharacterException
            déclanche l'exeption quand la taille de caractère est incorrecte
     */
     @Override
    public void welcomeCharacter(Character character) {}

    /** Méthode qui accède a l'attribut ressource 
     * avec ca valeur
     * @param value valeur de la ressource
     * @return null
     */
    @Override
    public Resource getRessource(int value) {
        return null;
    }

    /** Méthode qui permet de savoir
     * si la tuile est occuper
     * @return false
     */
    @Override
    public boolean isBusy() {
        return false;
    }

    /** Méthode qui permet d'acceder 
    * a l'attribut player
     * @return null
     */
    @Override
    public Player getPlayer() {
        return null;
    }

    /** Méthode qui permet d'enlever
     * un charactere
     */
    @Override
    public void removeCharacter() {}

    /** méthode qui permet d'acceder 
     * a l'attribut character
     * @return null
     */
    @Override
    public Character getCharacter() {
        return null;
    }
}
