package game.action;

import game.Character;
import game.Player;
import game.Resource;
import game.Tile;
import java.util.*;

/** Classe qui est responsable les echange 
 * pour le jeu agricole
 */
public class ExchangeRessourceFarmGame extends ExchangeRessource {

    /**
     *
     */
    public ExchangeRessourceFarmGame() {
        super();
    }

    
      /** Méthode qui permet de realiser l'action 
     * @param character : character
     * @param player : joueur
     * @throws Exception
     */
    public void realise(Character farmer, Player player) throws Exception {
        for (Map.Entry<Resource, Integer> entry : player
            .getResource()
            .entrySet()) {
            player.addGold(entry.getValue() * entry.getKey().getValue());
        }
        player.removeAllResource();
        System.out.println(
            player.getName() + " a echangé ses ressources contre de l'or"
        );
    }

    
    /** 
     * @param character
     * @param player
     * @param tile
     * @param resource
     * @return String
     */
    @Override
    public String toString(
        Character character,
        Player player,
        Tile tile,
        Resource resource
    ) {
        String chaine =
            "***********Vous venez de d'échanger vos ressources*********";
        return chaine;
    }
}
