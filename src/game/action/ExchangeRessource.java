package game.action;

import game.Action;
import game.Character;
import game.Player;


/** Classe abstraite qui permet de gerer
 * les changement de ressource
 */
public abstract class ExchangeRessource implements Action {

    /**
     * 
     */
    public ExchangeRessource() {
        super();
    }

      /** Méthode qui permet de realiser l'action 
     * @param character : character
     * @param player : joueur
     * @throws Exception
     */
    public abstract void realise(Character army, Player player)
        throws Exception;
}
