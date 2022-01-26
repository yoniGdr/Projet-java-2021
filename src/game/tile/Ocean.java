package game.tile;

import game.Player;

/**
 * Cette classe est differente, pas de resource, gold, ni Player
 * faire autrement l'heritage
 * */
public class Ocean extends UnusableTile {

    /**Constructeur de la classe Ocean
     * @param xPosition position en X
     * @param yPosition postition en Y
     */
    public Ocean(int xPosition, int yPosition) {
        super(xPosition, yPosition);
    }
    /** return string 'O'
     * @return O
     */
    public String toString() {
        return "O ";
    }
    /** Méthode qui defini le joueur du jeu 
     * @param player joueur du jeu 
     */
    @Override
    public void setPlayer(Player player) {
        // TODO Auto-generated method stub

    }
}
