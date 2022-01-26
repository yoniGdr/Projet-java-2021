package game;

import game.tile.Ocean;
import game.tile.UsableTile;
import game.util.io.Colors;

import java.util.*;

//import game.Tile;

/** classe abstract qui represente le plateau du jeu 
 * il est représenter avec une longeur et une largeur et 
 * contient ainsi toute les methode necessaire a son utilisation
 */
public abstract class Board {

    
    /** nombre de tuiles (width*heigth)*/
    protected int numberofTile;
    /**largeur du baord*/
    protected int width;
    /**hauteur du baord*/
    protected int heigth;
    /** le board */
    protected Tile[][] board;

    /** Constructeur de la classe board
     * @param width largeur 
     * @param heigth hauteur 
     */    
    public Board(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
        this.numberofTile = (this.heigth) * this.width;
        this.board = new Tile[this.width][this.heigth];
    }

    /** Méthode qui permet de retourner la tuile présente
     * en postion X et Y dans le board
     * @param Xposition position en x 
     * @param Yposition position en y
     * @return Tile
     */
    public Tile getTile(int Xposition, int Yposition) {
        return this.board[Xposition][Yposition];
    }

    
    /** Méthode qui permet de savoir si la tuile est valide 
     * @param tile : la tuile a tester  
     * @return boolean : False si la tuile n'est pas valide
     */
    public boolean validTile(Tile tile) {
        return (
            tile.getXposition() < this.width &&
            tile.getYposition() < this.heigth
        );
    }

    /** Méthode qui permet d'acceder a l'attribut numberofTile
     * @return int : le nbr de tuile 
     */
    public int getnumberofTile() {
        return this.numberofTile;
    }

    
    

    /** Méthode qui permet d'initialiser le board
     */
    public abstract void initBoard();

    

    
    /** Méthode qui permet d'acceder a l'attribut width
     * @return int : width
     */
    public int getWidth() {
        return this.width;
    }

    
     /** Méthode qui permet d'acceder a l'attribut height
     * @return int : height
     */
    public int getHeight() {
        return this.heigth;
    }

    
    /** Méthode qui permet de connaitre les tuiles qui peuvent
     *  recevoir des character
     * @return tuiles disponibles
     */
    public ArrayList<Tile> allUsableTiles() {
        ArrayList<Tile> res = new ArrayList<>();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.heigth; j++) {
                if (
                    this.board[i][j] instanceof UsableTile &&
                    !this.board[i][j].isBusy()
                ) {
                    res.add(this.board[i][j]);
                }
            }
        }
        return res;
    }

    
    

    /** Méthode qui permet d'avoir les coordoner 
     * @return  les coordoner
     */
    public abstract ArrayList<Map<Integer, Integer>> getCoordinates();

    /** Méthode qui permet d'afficher le board
     */
    public void displayBoard() {
    	Tile t = null;
        System.out.print("  ");
        for (int i = 0; i < this.heigth; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < this.width; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.heigth; j++) {
                t = this.board[i][j];
                if (t instanceof Ocean) System.out.print(
                    Colors.CYAN + t.toString() + Colors.RESET
                ); else if (t.isBusy()) System.out.print(
                    Colors.RED_BOLD + t.toString() + Colors.RESET
                ); else System.out.print(
                    Colors.GREEN_BOLD + t.toString() + Colors.RESET
                );
            }
            System.out.print("\n");
        }
    }
}
