package game.action;

import game.Board;
import game.Character;
import game.Player;
import game.Resource;
import game.Tile;
import game.util.io.Colors;
import java.util.Map;

/** Classe responsable des echange
 * pour le jeu de guerre
 */
public class ExchangeRessourceWarGame extends ExchangeRessource {

    
    
    /**
     * 
     * @param board
     */
    public ExchangeRessourceWarGame(Board board) {
        super();
    }

    /**
     * 
     */
    public void realise(Character army, Player player) throws Exception {
        for (Map.Entry<Resource, Integer> entry : player
            .getResource()
            .entrySet()) {
            player.addFood(entry.getValue() * entry.getKey().getValue());
        }
        player.removeAllResource();
        System.out.println(
            Colors.GREEN_BOLD +
            "************Toutes les ressources sont convertis ***********" +
            Colors.RESET
        );
    }

    /**
     * 
     * @param character
     * @param player
     * @param tile
     * @param resource
     * @return
     */
    @Override
    public String toString(
        Character character,
        Player player,
        Tile tile,
        Resource resource
    ) {
        String chaine = "";
        return chaine;
    }
}
