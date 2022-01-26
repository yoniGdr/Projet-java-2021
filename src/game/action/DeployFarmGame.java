package game.action;

import game.*;
import game.Character;
import game.board.ClassicBoard;
import game.util.exception.*;

/** classe responsable du deploiement
 * pour le jeu agricole 
 */
public class DeployFarmGame extends Deploy {

    /**
     * 
     * @param board plateau de jeu
     */
    public DeployFarmGame(ClassicBoard board) {
        super(board);
    }

     /** Méthode qui realise une action 
     * @param character : character
     * @param player : joueur
     * @throws Exception
     */
    @Override
    public void realise(Character character, Player player)
        throws TileNotInBoardException, TilenotAvailableException, TooManyCharacterforMountain, NoUsableTileAvailableException, TooManyCharacterforDesert {
        Tile tile = character.getPosition();
        try {
            tile.welcomeCharacter(character);
        } catch (Exception e) {}

        int x = tile.getXposition();
        int y = tile.getYposition();
        System.out.println(
            player.getName() +
            " a deployé sur " +
            tile.toString() +
            "{" +
            x +
            "," +
            y +
            "}."
        );
        player.adddeployedCharacter(character);
    }



}

