package game.action;

import game.*;
import game.Character;
import game.tile.*;
import java.util.ArrayList;

public class DoNothingFarmGame extends DoNothing {

    public DoNothingFarmGame() {
        /*super();*/

    }

    
     /** Méthode qui permet de realiser l'action 
     * @param character : character
     * @param player : joueur
     * @throws Exception
     */
    @Override
    public void realise(Character character, Player player) throws Exception {
        ArrayList<Character> deployedFarmers = player.getDeployedCharacters();
        int c = 0;
        Tile t;
        for (int i = 0; i < deployedFarmers.size(); i++) {
            t = deployedFarmers.get(i).getPosition();
            if (t instanceof Forest || t instanceof Plain) {
                player.addGold(1);
                c++;
            } else if (t instanceof Desert) {
                player.addGold(2);
                c += 2;
            }
        }
        System.out.println(
            player.getName() + " ne fait rien, il reçois " + c + " pieces d'or."
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
        return "This Action is usless";
    }
}
