package game.tile;

import game.*;
import game.Character;
import game.util.exception.TooManyCharacterforDesert;
import game.util.exception.TooManyCharacterforMountain;
import game.util.exception.WrongSizeCharacterException;

/** 
 * 
 */
public abstract class UsableTile implements Tile {

    protected Character character;
    protected Player player;
    protected int xPosition;
    protected int yPosition;

    /** Constructeur de la classe UsableTile
     * @param xPosition Position en X de la tuile 
     * @param yPosition Position en Y  de la tuile 
     */
    public UsableTile(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /** Méthode qui permet d'ajouter un character
     * @param character : characrer que l'on ajoute 
     * @throws TooManyCharacterforMountain : déclanche l'exeption quand il y a trop de caractères pour la montagne
     * @throws TooManyCharacterforDesert : déclanche l'exeption quand il y a trop de caractères pour le desert
     * @throws WrongSizeCharacterException : déclanche l'exeption quand la taille de caractère est incorrecte
     */
    public void welcomeCharacter(Character character)
        throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
        this.character = character;
        this.player = this.character.getOwner();
        if (character.getSize() == 0) {
            throw new WrongSizeCharacterException(
                "Impossible de déployer une armée de taille 0"
            );
        }
        // System.out.println(this.getPlayer());
    }

    /** Méthode qui permet d'acceder au ressource 
     * avec ca valeur
     * @param value valeur de la ressource 
     * @return
     */
    public abstract Resource getRessource(int value);

    /** Méthode qui permet de savoir si il y a un player
     * @return retourn True si il y a un player 
     */
    public boolean isBusy() {
        return this.player != null;
    }

    /** Méthode qui permet d'obtenir le joueur
     * @return renvoi le joueur
     */
    public Player getPlayer() {
        return this.player;
    }

    /** Méthode qui permet de connaitre le nbr d'or 
     * @return : nbr de gold
     */
    public abstract int getGold();

    /** Méthode qui suprime le caractère et le player 
     */
    public void removeCharacter() {
        this.character = null;
        this.player = null;
    }

    /** Méthode qui permet de renvoyer
     *  une chaine de charactere
     * @return string
     */
    public abstract String toString();

    /** Méthode qui permet d'obtenir le caractère 
     * @return retourn le caractère
     */
    public Character getCharacter() {
        return this.character;
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

    /** Méthode qui defini le joueur du jeu 
     * @param player ( joueur du jeu )
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}

