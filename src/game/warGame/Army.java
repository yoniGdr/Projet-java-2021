package game.warGame;

import game.Character;
import game.Player;
import game.Tile;
import game.tile.Desert;
import game.tile.Forest;
import game.tile.Mountain;
import game.tile.Plain;
import game.util.exception.*;
import game.util.io.Colors;

/**
 * @author amevigbe
 *
 */
public class Army extends Character {




    /**
     * 
     * @param name
     * @param position
     * @param gold
     * @param taille
     * @param player
     * @throws WrongSizeCharacterException
     */
    public Army(
        String name,
        Tile position,
        int gold,
        int taille,
        Player player
    ) throws WrongSizeCharacterException {
        super(position, gold, taille, player);
        if (taille > 5) {
            throw new WrongSizeCharacterException(
                "La limite de l'armée ne peut jamais dépasser 5"
            );
        }
        if (taille <= 0) {
            throw new WrongSizeCharacterException(
                "La taille de l'armée ne peut jamais inférieure ou égale à 0"
            );
        }
  

    }

    /** Méthode qui permet de connaitre le nombre de nourriture 
     * nécessaire pour nourrir 
     * @param food : nbr de nourriture 
     * @return : retourne le nombre de nourriture nécessaire
     * @throws NotEnoughFoodException
            déclanche l'exception quand il y a pas assez de nourriture pour nourire
     */
    @Override
    public int eat(int food) throws NotEnoughFoodException {
        int nourriture = 0;
        if (food < this.size) {
                throw new NotEnoughFoodException(Colors.RED+"pas assez de nourriture"+Colors.RESET);
        }
        if (this.position instanceof Desert) {
            if(food*2<this.size)
                throw new NotEnoughFoodException(Colors.RED+"pas assez de nourriture"+Colors.RESET);
            nourriture = 2 * this.size;
        } else {
            nourriture = this.size;
        }
        return nourriture;
    }

    /** Méthode qui donne la paye des characteres
     * @param gold 
     * @return : retourne le nombre d'or du charactere
     * @throws NotEnoughGoldException
            déclanche l'exception quand il y a pas assez d'or pour donner sa paye
     */
    @Override
    public int getPayed(int gold) throws NotEnoughGoldException {
        if (this.owner.getGold() == 0) {
            throw new NotEnoughGoldException("Pas assez d'or");
        } else return this.gold + gold;
    }

    /**Méthode qui rajoute une prime en fonction de la tuile
     * @return le bonus
     */
    public int getPrimeofTile() {
        int res = 0;
        if (this.position instanceof Plain) {
            res = 1;
        }
        if (this.position instanceof Forest) {
            res = 2;
        }
        if (
            this.position instanceof Mountain || this.position instanceof Desert
        ) {
            res = 4;
        }
        return res;
    }

    /** renvoi une chaine de caractere 
     * return : "Armée"
     */
    public String toString() {
        return "Armée";
    }
}
