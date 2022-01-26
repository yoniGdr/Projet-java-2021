package game.farmGame;

import game.*;
import game.Character;
import game.tile.*;
import game.util.exception.*;

public class Farmer extends Character {

    private int salary;

    /** Constructeur de la classe Farmer
     *
     * @param position
     * @param owner
     */
    public Farmer(Tile position, FarmPlayer owner) {
        super(position, 0, 1, owner);
        fixSalary();
    }

    /** Cette methode permets de fixer un salaire en fonction du territoire occupé
     */
    private void fixSalary() {
        if (position instanceof Mountain) this.salary = 8; else if (
            position instanceof Desert
        ) this.salary = 3; else this.salary = 1;
    }

    /** Cette methode renvoie le salaire de l'ouvrier si l'or passé en parametre (or total du joueur)
     * Sinon une exception est declonché
     * @param gold
     * @throws NotEnoughGoldException si le jouer n'as pas assez d'argent
     * @return le salaire de l'ouvrier
     */
    public int getPayed(int gold) throws NotEnoughGoldException {
        /* System.out.println(this.owner.getName()+this.position.toString()+"{"+position.getXposition() +"," +position.getYposition() +" }"  +" : " + salary + " vs " + gold); */
        if (this.salary > gold) throw new NotEnoughGoldException();

        this.gold += this.salary;

        return this.salary;
    }

    /**
     * 
     * @param food
     * @return
     * @throws NotEnoughFoodException
     */
    @Override
    public int eat(int food) throws NotEnoughFoodException {
        return 0;
    }
    /**
     * 
     * @return
     */
    @Override
    public int getPrimeofTile() {
        return 0;
    }
    /**
     * 
     */
    public String toString() {
        return "Farmer";
    }
    public int getSalary(){
        return this.salary;
    }
}
