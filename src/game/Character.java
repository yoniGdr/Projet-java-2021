package game;


import game.util.exception.*;

/**
 * Cette classe représente la classe d'un guerrier et d'un
 * ouvrier
 * Cette classe dispose d'un constructeur qui prend en paramètre
 * le nom du guerrier sa position auquelle il est rattaché
 * et son porte-monnaie qui est le nombre d'or que le guerrier dispose
 * @author groupe 3
 *
 */
public abstract class Character {
    /** emplacement du character */
    protected Tile position;
    /** or du character */
    protected int gold;
    /** propriétaire */
    protected Player owner;
    /**taille du character*/
    protected int size;

    /** Constructeur Charcater
     * @param position position (tuile) 
     * @param gold nombre de d'or
     * @param size taille du Characeter
     * @param player propriétaire
     */
    public Character(Tile position, int gold, int size, Player player) {
        this.position = position;
        this.gold = gold;
        this.size = size;
        this.owner = player;

    }
    /** Méthode qui permet de connaitre le nombre de nourriture 
     * nécessaire pour nourrir 
     * @param food : nourriture 
     * @return : retourne le nombre de nourriture nécessaire
     * @throws NotEnoughFoodException se déclanche quand il y a pas assez de nourriture pour nourire
     */
    public abstract int eat(int food) throws NotEnoughFoodException;
    
    /** Méthode qui donne la paye des characteres
     * @param gold : or
     * @return : retourne le nombre d'or du charactere
     * @throws NotEnoughGoldException déclanche quand il y a pas assez d'or pour donner sa paye
     */
    public abstract int getPayed(int gold) throws NotEnoughGoldException;


    /**
     * Cette méthode permet de connître la position de notre guerrier
     * @return  tuile sur lequel se trouve notre guerrier
     */
    public Tile getPosition() {
        return this.position;
    }
    /**
     * Cette méthode permet de savoir si 
     * un charactere ce trouve sur une tuile 
     * @return True si il se trouve sur une tuile 
     */
    public boolean isInTile() {
        return this.position != null;
    }
    /** Cette méthode permet d'acceder a l'attribut size
     * @return La taille 
     */
    public int getSize() {
        return this.size;
    }
    /** Cette méthode permet de reduire la taille 
     * @param newSize : taille que l'on veut soustraire
     */
    public void reduceSize(int newSize) {
        this.size = this.size - newSize;
    }
    /** Cette méthode permet d'ajouter de l'or au joueur
     * @param gold : gold que l'ont veut ajouter
     */
    public void addGold(int gold) {
        this.gold += gold;
    }
    /** Cette méthode permet d'acceder
     * a l'attribut gold 
     * @return le nombre d'or du joueur 
     */
    public int getGold() {
        return this.gold;
    }
      /** Cette méthode permet d'acceder
     * a l'attribut Owner
     * @return le propriétaire 
     */
    public Player getOwner() {
        return this.owner;
    }

    /**methode toString
     * */
    public abstract String toString();
      
    /** Cette méthode permet d'ajouter une taille 
    * @param newSize la taille que l'on souhaite ajouter 
    */
    public void addSize(int newSize) {
        this.size += newSize;
    }

    /** Cette méthode permet d'acceder a l'attribut salaire
    * @return : 0 car par defaut pas de salire
    */
    public  int getSalary(){
        return 0;
    } 
    /** Méthode qui donne la prime en fonction de la tuile 
     * @return : le bonus
     */
    public abstract int getPrimeofTile();
}
