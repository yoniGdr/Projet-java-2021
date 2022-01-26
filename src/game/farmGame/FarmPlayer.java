package game.farmGame;

import game.*;
import game.Character;
import game.tile.*;
import java.util.ArrayList;

public class FarmPlayer extends Player {
    /** Constructeur de la classe FarmPlayer
     * @param name : nom du jouer 
     * @param interactive :  joueur interactive ou non
     */
    public FarmPlayer(String name, boolean interactive) {
        super(name, 15, 0, interactive);
    }


    /** Méthode qui permet de creer un charactere
     * @param tile : tuile 
     * @param size : taille
     * @return : retourne le Character créer 
     */
    @Override
    public Character createCharcater(Tile tile, int size) {
        Farmer farmer = new Farmer(tile, this);
        return farmer;
    }
    /** Méthode qui permet de récolter
     */
    public void harvest() {
        ArrayList<Character> CharactersofthePlayer =
            this.getDeployedCharacters();
        int value = 2;
        for (Character a : CharactersofthePlayer) {
            if (a.getPosition() instanceof Mountain) {
                value = 8;
            } else if (a.getPosition() instanceof Desert) {
                value = 5;
            }

            this.addRessource(a.getPosition().getRessource(value));
        }
    }
    /** Méthode qui permet de supprimer un charactere
     * @param a : charactere que l'on veut enlever 
     */
    @Override
    public void destroyCharacter(Character a) {
        this.deployedCharacters.remove(a);
    }
    /** Méthode qui permet de compter les points
     * en fonction des gold des charactere 
     * @return : retourne les points 
     */
    @Override
    public int countPoint() {
        int points = 0;
        for (Character c : this.deployedCharacters) {
            points += c.getGold();
        }
        return points;
    }
}
