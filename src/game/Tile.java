package game;

import game.util.exception.TooManyCharacterforDesert;
import game.util.exception.TooManyCharacterforMountain;
import game.util.exception.WrongSizeCharacterException;

//import game.resource.*;

/** Interface qui représente les tuiles des jeux, c’est-à-dire les emplacements du Tableau
*/
public interface Tile {
    
     /** Méthode qui permet d'obternir la postion x d'une tuile 
     * @return retourne la position de X
     */
    public int getXposition();
   
    /** Méthode qui permet d'ajouter un character
     * @param character : character que l'ont ajoute 
     * @throws TooManyCharacterforMountain
     *      déclanche l'exeption quand il y a trop de caractères pour la montagne
     * @throws TooManyCharacterforDesert
     *      déclanche l'exeption quand il y a trop de caractères pour le desert
     * @throws WrongSizeCharacterException
            déclanche l'exeption quand la taille de caractère est incorrecte
     */
    public void welcomeCharacter(Character character)
        throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException;
   
     /** Méthode qui permet d'obternir la postion Y d'une tuile 
     * @return retourne la position de Y
     */
    public int getYposition();
   

     /** Méthode qui permet d'acceder a l'atribut ressource
     * avec ca valeur 
     * @param value : valeur de la ressource 
     * @return : ressource en fonction de la valeur
     */
    public abstract Resource getRessource(int value);
    
    /** Méthode qui permet de savoir si il y a un player
     * @return retourn True si il y a un player 
     */
    public boolean isBusy();
    
    /** Méthode qui permet d'obtenir le joueur
     * @return renvoi le joueur
     */
    public Player getPlayer();
    
     /** Méthode qui suprime le caractère et le player 
     */
    public void removeCharacter();
    
      /** Méthode qui permet d'acceder a l'attribut charactere 
     * @return retourne le character
     */
    public Character getCharacter();
    
     /** Méthode qui defini le joueur du jeu 
     * @param player ( joueur du jeu )
     */
    public void setPlayer(Player player);
}
