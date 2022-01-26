package game.action;

import game.*;
import game.Character;
import game.board.ClassicBoard;
import game.tile.Mountain;
import game.tile.Ocean;
import game.util.exception.*;
import game.util.io.Colors;
import game.warGame.*;
import java.util.*;
import java.util.ArrayList;

/** classe responsable du deploiement 
 * pour le jeu de guerre 
 */
public class DeployWarGame extends Deploy {

    protected Army army;
    protected WarPlayer player;
    protected boolean resultat;

    /** 
     * 
     * @param board plateau de jeu
     */
    public DeployWarGame(Board board) {
        super(board);
        this.resultat = false;
        this.board = board;
    }

    
    /** Méthode qui permet de réalise l'action
     * @param army
     * @param player
     * @throws WrongSizeCharacterException
     * @throws TooManyCharacterforMountain
     * @throws TooManyCharacterforDesert
     * @throws TilenotAvailableException
     */
    public void realise(Character army, Player player)
        throws WrongSizeCharacterException, TooManyCharacterforMountain, TooManyCharacterforDesert, TilenotAvailableException {
        Tile tile = army.getPosition();
        String chaine =
            "\n" +
            player.getName() +
            " Voilà votre situation : " +
            " \nVous deployer un/une " +
            army.toString() +
            " de taille" +
            " " +
            army.getSize() +
            " sur " +
            tile.toString() +
            "{" +
            tile.getYposition() +
            "," +
            tile.getXposition() +
            "}.";

        

        if (army.getSize() > 5) {
            throw new WrongSizeCharacterException(
                "Trop de guerrier pour le déployement pensez à réduire la taille de l'armée"
            );
        } else {
            //System.out.println(this.board);
            if (
                this.board.validTile(tile) &&
                !tile.isBusy() &&
                !(tile instanceof Ocean)
            ) {
                tile.welcomeCharacter(army);
                player.adddeployedCharacter(army);
                this.resultat = true;
                ((WarPlayer) player).setWarriors(army.getSize());
                this.tileAdjacentAffect(army, player, tile);
                System.out.println(chaine);
            } else {
                throw new TilenotAvailableException(
                    " Vérifier si la tuile n'est pas occupé ou si la tuile appartient au Board\n ou que la tuile n'est pas un Ocean "
                );
            }
        }
    }

    
    /** 
     * @param army
     * @param player
     * @param tile
     */
    private void tileAdjacentAffect(Character army, Player player, Tile tile) {
        // Récupération de la liste des tuiles adjacentes

        ArrayList<Tile> tileAdjacentennemy = new ArrayList<>();
        ArrayList<Tile> tileAdjacentforPlayer = new ArrayList<>();
        ArrayList<Map<Integer, Integer>> adjacent =
            ((ClassicBoard) this.board).generateAdjacentTile(
                    tile.getXposition(),
                    tile.getYposition()
                );
        for (Map<Integer, Integer> a : adjacent) {
            for (Map.Entry<Integer, Integer> entry : a.entrySet()) {
                Tile tile1 =
                    this.board.getTile(entry.getKey(), entry.getValue());

                if (!(tile1 instanceof Ocean) && tile1.getPlayer() != null) {
                    if (tile1.getPlayer().getName() != player.getName()) {
                        tileAdjacentennemy.add(tile1);
                    } else {
                        tileAdjacentforPlayer.add(tile1);
                    }
                }
            }
        }

        for (Tile ennemyTile : tileAdjacentennemy) {
            if (ennemyTile.getCharacter() != null) {
                int sizeEnnemy = ennemyTile.getCharacter().getSize();
                if (ennemyTile instanceof Mountain) sizeEnnemy += 2;

                if (sizeEnnemy < army.getSize()) {
                    ennemyTile
                        .getCharacter()
                        .reduceSize(
                            (int) ennemyTile.getCharacter().getSize() / 2
                        );

                    if (ennemyTile.getCharacter().getSize() <= 1) {
                        Player j = ennemyTile.getPlayer();
                        ennemyTile
                            .getPlayer()
                            .removeDeployedCharacter(ennemyTile.getCharacter());
                        ennemyTile.setPlayer(player);

                        player.adddeployedCharacter(ennemyTile.getCharacter());

                        System.out.print(Colors.YELLOW_BOLD+
                            player.getName() + " a attaqué " + j.getName() + " sur " + ennemyTile.toString() + "{" + ennemyTile.getXposition()+"," + ennemyTile.getYposition()+  "}\n\n" 
                        +Colors.RESET);
                        /*ennemyTile.getPlayer().removeDeployedCharacter(ennemyTile.getCharacter());*/
                        System.out.println(j);
                        army.addGold(2);
                    }
                }
            }
        }
        for (Tile tilePlayer : tileAdjacentforPlayer) {
            if (
                tilePlayer.getCharacter() != null &&
                tilePlayer.getCharacter().getSize() < army.getSize()
            ) {
                tilePlayer.getCharacter().addSize(1);
                army.addGold(1);
            }
        }
    }
}
