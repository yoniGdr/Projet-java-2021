/**
 *
 */
package game.board;

import game.*;
import game.tile.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author amevigbe
 *
 */
public class ClassicBoard extends Board {

    protected final int numberofTileearth = (int) ((this.numberofTile) / 3);
    protected ArrayList<Map<Integer, Integer>> coordinates;




    /**
     *
     * @param width
     * @param heigth
     */
    public ClassicBoard(int width, int heigth) {
        super(width, heigth);
        this.coordinates = new ArrayList<Map<Integer, Integer>>();


        initBoard();

    }

    /**
     *
     */
    @Override
    public void initBoard() {
        while (this.coordinates.size() < this.numberofTileearth - 1) {
            int x = this.positionXrandom();
            int y = this.positionYrandom();

            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            map.put(x, y);
            ArrayList<Map<Integer, Integer>> adjacent =
                this.generateAdjacentTile(x, y);
            int size = adjacent.size();
            int numberoftile = this.randomNumberofTileNext(size);

            if (!this.coordinates.contains(map)) {
                int i = 0;

                ArrayList<Integer> stock = new ArrayList<>();

                while (
                    i < numberoftile &&
                    this.coordinates.size() + i + 2 <= this.numberofTileearth &&
                    stock.size() < size
                ) {
                    int i1 = this.randomNumber4(size);

                    Map<Integer, Integer> r = adjacent.get(i1);
                    if (!(this.coordinates.contains(r))) {
                        for (Map.Entry<Integer, Integer> x2 : r.entrySet()) {
                            this.generateTile(
                                    this.randomNumberofTile(),
                                    x2.getKey(),
                                    x2.getValue()
                                );

                            this.coordinates.add(r);
                            i = i + 1;
                        }
                    } else if (!stock.contains(i1)) {
                        stock.add(i1);
                    }
                }

                if (i != 0) {
                    this.generateTile(this.randomNumberofTile(), x, y);
                    this.coordinates.add(map);
                }
            }
        }
        int j = 0;
        while (j < this.width) {
            int j1 = 0;
            while (j1 < this.heigth) {
                Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
                map1.put(j, j1);

                if (!(this.coordinates.contains(map1))) {
                    this.board[j][j1] = new Ocean(j, j1);
                }
                j1 = j1 + 1;
            }
            j = j + 1;
        }
    }


    /** Méthode qui permet de savoir si une tuile de position 1 
     * est adjacent a une autre de position 2.
     * @param Xposition1 : position en X de la premiere tuile
     * @param Yposition1 : position en Y de la premiere tuile
     * @param Xposition2 : position en X de deuxieme tuile
     * @param Yposition2 : position en Y de la deuxieme tuile
     * @return : retourne True si les tuile sont adjacente 
     */
    public boolean isAdjacent(
        int Xposition1,
        int Yposition1,
        int Xposition2,
        int Yposition2
    ) {
        if (
            (Xposition2 == Xposition1 - 1 || Xposition2 == Xposition1 + 1) &&
            Yposition2 == Yposition1
        ) {
            return true;
        } else if (
            (Yposition2 == Yposition1 - 1 || Yposition2 == Yposition1 + 1) &&
            Xposition2 == Xposition1
        ) {
            return true;
        } else return false;
    }

    /** Méthode qui permet de generer des tuile adjacente
     * @param x : position en X
     * @param y : postion en Y
     * @return : retourne la liste des position des tuiles generer
     */
    public ArrayList<Map<Integer, Integer>> generateAdjacentTile(int x, int y) {
        ArrayList<Map<Integer, Integer>> thecoordinates = new ArrayList<Map<Integer, Integer>>();
        Map<Integer, Integer> thecoordinates2 = new HashMap<Integer, Integer>();

        if ((0 < y && y < this.heigth - 1) && (0 < x && x < this.width - 1)) {
            thecoordinates2.put(x + 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x - 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y - 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y + 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
        } else if (x == 0 && (0 < y && y < this.heigth - 1)) {
            thecoordinates2.put(x + 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y - 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y + 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
        } else if (x == 0 && y == 0) {
            thecoordinates2.put(x + 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y + 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
        } else if (y == 0 && (0 < x && x < this.width - 1)) {
            thecoordinates2.put(x, y + 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x + 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x - 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
        } else if (x == 0 && y == this.heigth - 1) {
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x + 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y - 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
        } else if (y == 0 && x == this.width - 1) {
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x - 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y + 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
        } else if (x == this.width - 1 && y == this.heigth - 1) {
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x - 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y - 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
        } else if (x == this.width - 1 && (0 < y && y < this.heigth - 1)) {
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x - 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y - 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y + 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
        } else if (y == this.heigth - 1 && (0 < x && x < this.width - 1)) {
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x, y - 1);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x + 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
            thecoordinates2.put(x - 1, y);
            thecoordinates.add(thecoordinates2);
            thecoordinates2 = new HashMap<Integer, Integer>();
        }
        return thecoordinates;
    }

    /** Méthode qui donne une postion aléatoire en X pour une tuile 
     * @return : postion en X
     */
    public int positionXrandom() {
        int Min = 0;
        int Max = this.width;
        return Min + (int) (Math.random() * ((Max - Min) + 1));
    }

    /** Méthode qui donne une postion aléatoire en Y pour une tuile 
     * @return : postion en Y
     */
    public int positionYrandom() {
        int Min = 0;
        int Max = this.heigth;
        return Min + (int) (Math.random() * ((Max - Min) + 1));
    }

    /** Méthode qui donne un nombre aléatoire de tuile suivante
     * @param size : taille max de tuile suivante
     * @return : nbr de tuiles suivante
     */
    public int randomNumberofTileNext(int size) {
        int Min = 1;
        int Max = size;
        return Min + (int) (Math.random() * ((Max - Min) + 1));
    }

     /** Méthode qui donne un nombre aléatoire de tuile 
     * @return : nbr de tuiles 
     */
    public int randomNumberofTile() {
        int Min = 1;
        int Max = 4;
        return Min + (int) (Math.random() * ((Max - Min) + 1));
    }

     /** Méthode qui donne un nombre aléatoire
     * @param x : int 
     * @return : nbr aléatoire
     */
    public int randomNumber4(int x) {
        int Min = 0;
        int Max = x - 1;
        return Min + (int) (Math.random() * ((Max - Min) + 1));
    }

    /** Méthode qui permet de génerer une tuile 
     * @param number : nbr entre 1 et 4 (correspond au different type ex : Desert pour 1 )
     * @param x2 : position en X de la tuile
     * @param y : position en Y de la tuile
     */
    public void generateTile(int number, int x2, int y) {
        if (number == 1) {
            this.board[x2][y] = new Desert(x2, y);
        }

        if (number == 2) {
            this.board[x2][y] = new Plain(x2, y);
        }
        if (number == 3) {
            this.board[x2][y] = new Forest(x2, y);
        }

        if (number == 4) {
            this.board[x2][y] = new Mountain(x2, y);
        }
    }

    /** Méthode qui permet de donner toute les postions des tuiles
     * @return : la liste des postions des tuiles
     */
    public ArrayList<Map<Integer, Integer>> getCoordinates() {
        ArrayList<Map<Integer, Integer>> coordinate = new ArrayList<Map<Integer, Integer>>();
        for (Map<Integer, Integer> a : this.coordinates) {
            for (Map.Entry<Integer, Integer> entry : a.entrySet()) {
                if (!this.board[entry.getKey()][entry.getValue()].isBusy()) {
                    coordinate.add(a);
                }
            }
        }
        return coordinate;
    }
}
