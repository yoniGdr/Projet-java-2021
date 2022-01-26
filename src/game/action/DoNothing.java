package game.action;

import game.*;
import game.Character;

/* import games.util.exception.*; */
public class DoNothing implements Action {

    public DoNothing() {}

    
      /** Méthode qui permet de realiser l'action 
     * @param character : character
     * @param player : joueur
     * @throws Exception
     */
    @Override
    public void realise(Character character, Player player) throws Exception {}

    
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
        return "This Action is usless";
    }
}
