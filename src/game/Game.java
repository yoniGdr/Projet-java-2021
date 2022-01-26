package game;

import game.util.MyRandom;
import game.util.exception.*;
import java.io.IOException;
import java.util.*;

/**Classe abstraite qui gère le jeu
 * */
public abstract class Game {
    /** le board */
    protected Board board;
    /** nombre de tours  */
    protected int NbTours;
    /**liste de players qui jouent*/
    protected List<Player> thePlayers;
    /** points de chaque player */
    protected HashMap<Player, Integer> thePlayersPoints;
    /** random ppour gerer le mode aleatoire */
    protected MyRandom random;
    /** interator de players pour gerer les tours */
    protected Iterator<Player> it_thePlayers;
    /** les actions disponible */
    protected HashMap<String, Action> actions;

    /** constructeur de Game
     * @param board le plateau du jeu
     * @param NbTours nombre de tour de jeu
     */
    public Game(Board board, int NbTours) {
        this.board = board;
        this.NbTours = NbTours;
        this.thePlayersPoints = new HashMap<>();
        this.thePlayers = new ArrayList<>();
        this.it_thePlayers = this.thePlayers.iterator();
        this.random = new MyRandom();
    }

    
    /** Méthodes qui permet d'acceder aux actions du joueur
     * @return : les actions
     */
    public HashMap<String, Action> getActions() {
        return this.actions;
    }

    
    
    /** Méthodes qui permet d'accéder 
     * a l'attribut board 
     * @return Board : le plateau
     */
    public Board getBoard() {
        return this.board;
    }

    /** Méthodes qui permet d'accéder 
     * a l'attribut NbTours 
     * @return int : le nombre de tour
     */
    public int getNbTours() {
        return this.NbTours;
    }

    
    /** Méthode qui permet d'acceder 
     * a l'attribut thePlayers 
     * @return  liste des joueurs
     */
    public List<Player> getPlayers() {
        return this.thePlayers;
    }

    
    /** Méthode  qui permet d'ajouter 
     * des points au player
     * @param player : joueur a qui ont donne les points
     */
    public void addPoints(Player player) {
        this.thePlayersPoints.put(player, player.countPoint());
    }

    /** Méthode  qui permet de éfinir le nombre total de points sur les joueurs
     */
    public void setTotalPointsToPlayers() {
        for (Player player : this.thePlayers) {
            player.setTotalPoints();
            this.thePlayersPoints.put(player, player.getPoints());
        }
    }

    
    /** Méthode qui permet de gerer un round du jeu 
     * 
     * @throws TooManyCharacterforMountain se declanche quand il y a trop de charactere 
     * @throws WrongSizeCharacterException se declanche quand la taille de charactere n'est pas bonne
     * @throws TilenotAvailableException se declanche quand la tuile n'est pas disponible 
     * @throws IOException autres exceptions
     * @throws Exception autres exceptions
     */
    public abstract void oneRound()
        throws TooManyCharacterforMountain, WrongSizeCharacterException, TilenotAvailableException, IOException, Exception;

    
    /** Méthode qui permet de jouer au jeu 
     * @throws NoPlayersInGameException se declanche quand il n'y a pas de joueur 
     * @throws Exception autres exceptions
     */
    public abstract void play() throws NoPlayersInGameException, Exception;

    
    /** Méthode qui permet d'ajouter un player 
     * @param player : joueur que l'on souhaute ajouer 
     */
    public void addPlayer(Player player) {
        this.thePlayers.add(player);
        this.thePlayersPoints.put(player, 0);
        /*this.thePlayersInterfactive.put(player,player.isInterfactive());*/

    }

    /** Méthode qui permet d'avoir le joueur suivant 
     * @param it_thePlayers le joueur 
     * @return Player : le joueur suivant 
     */
    public Player nextPlayer(Iterator<Player> it_thePlayers) {
        if (!it_thePlayers.hasNext()) {
            //it_thePlayers= thePlayers.iterator();
            return null;
        } else return it_thePlayers.next();
    }

    

    
    /** Méthode qui permet de donner le jouer gagnant
     * @return Player : joueur qui gagne le jeu
     */
    public Player winner() {
        this.setTotalPointsToPlayers();
        int score = 0;
        Player player = null;
        for (Map.Entry<Player, Integer> entry : this.thePlayersPoints.entrySet()) {
            if (entry.getValue() > score) {
                player = entry.getKey();
                score = entry.getValue();
            }
        }
        System.out.print("");
        return player;
    }


}

