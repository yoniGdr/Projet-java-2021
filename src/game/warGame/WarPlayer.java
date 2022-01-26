package game.warGame;

//import WarPlayer.Gold;
//import WarPlayer.Soldier;
import game.*;
import game.Character;
import game.tile.*;
//import game.tile.*;
//import game.util.*;
import game.util.exception.WrongSizeCharacterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import game.util.io.*;
public class WarPlayer extends Player {


    protected int warriors;

    public WarPlayer(String name, boolean interactive) {
        super(name, 0, 10, interactive);
        this.food = 10;
        this.warriors = 35;
    }
    /** Méthode qui permet de supprimer un charactere
     * @param a : charactere que l'on veut enlever 
     */
    public void destroyCharacter(Character army) {
        
        this.removeDeployedCharacter(army);
        

        army.getPosition().removeCharacter();
        
        this.gold = this.gold + 1;
    }

    /** Méthode qui permet de connaitre le nombre de guerrier
     * que possède le joueur 
     * @return : nbr de guerrier
     */
    public int getWarriors() {
        return this.warriors;
    }
    /** Méthode qui permet d'enlever des guerrier a un joueur
     * @param warriors : nbr de guerrier a enlever 
     */
    public void setWarriors(int warriors) {
        this.warriors = this.warriors - warriors;
    }
    /** Méthode qui permet de compter les points
     * en fonction du nbr ,gold et prime des charactere 
     * @return : retourne les points 
     */
    public int countPoint() {
    	int c = 0;
        if (this.deployedCharacters.size() >= 10) {
            c+= 5;
        }

        this.points = this.points + this.gold;
        for (Character a : this.deployedCharacters) {
           c+= + a.getGold() + a.getPrimeofTile();
        }
        return c;
    }

    /** Méthode qui donne des info sur la parti du joueur
     * @return : chaine de caractères
     */
    public String toString() {
         String chaine =
            this.name +
            " vous disposez de " +
            this.food +
            " unite de nourriture totale \n" +
            "Votre nombre de " +
            "guerrier actuel : " +
            this.warriors +
            ".\net la taille de votre" +
            " armée actuel est de " +
            this.deployedCharacters.size() +
            " et vos points totaux : " +
            this.points;
        return chaine;
    }

    /**
     * Cette méthode permet de recolter les ressources 
     * selon les tuiles 
     */
    public void harvest() {
        ArrayList<Character> CharactersofthePlayer =
            this.getDeployedCharacters();
        int value = 0;
        for (Character a : CharactersofthePlayer) {
            if (a.getPosition() instanceof Forest) {
                value = 1;
            }
            if (a.getPosition() instanceof Plain) {
                value = 5;
            }
            this.addRessource(a.getPosition().getRessource(value));
        }
    }
     /** Méthode qui permet de creer une armé
     * @param tile : tuile 
     * @param size : taille
     * @return : retourne l'armé créer 
     */
    public Character createCharcater(Tile tile, int size)
        throws WrongSizeCharacterException {
        Army army = new Army("name", tile, 0, size, tile.getPlayer());
        return army;
    }
}
