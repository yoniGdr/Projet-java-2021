package game.action;

import game.*;
import game.Character;
import game.util.MyRandom;

/** classe responsable du deploiement 
 */
public abstract class Deploy implements Action {

    protected MyRandom random;
    protected Board board;

    /** Constructeur de la classe Deploy
     * @param board : plateau de jeu
     */
    public Deploy(Board board) {
        this.board = board;
        this.random = new MyRandom();
    }


     /** Méthode qui realise les action 
     * @param character : le character
     * @param player : le joueur
     * @throws Exception
     */
    public abstract void realise(Character character,Player player) throws Exception;

    /** Méthode qui permet de donner les info
     * sur l'action du joueur 
     * @param character : le character
     * @param player : le joueur 
     * @param tile : la tuile 
     * @param resource : ressource
     * @return chaine de caractère 
     */
    public String toString(
        Character character,
        Player player,
        Tile tile,
        Resource resource
    ) {
        String chaine =
      
            player.getName() +
            " \n Vous deployez un/une " +
            character.toString() +
            " de taille" +
            " " +
            character.getSize() +
            " sur " +
            tile.toString();
        return chaine;
    }
}

