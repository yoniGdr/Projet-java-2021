package game.warGame;

import game.*;
import game.Character;
import game.action.*;
import game.board.*;
import game.tile.Desert;
import game.tile.Mountain;
import game.tile.UsableTile;
import game.util.exception.*;
import game.util.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 
 *
 */
public class WarGame extends Game {

    protected ClassicBoard board;

    protected Map<WarPlayer, Integer> points;
    protected ExchangeRessourceWarGame exchange;
    protected boolean interactive;

    /**Constructeur de WarGame
     * @param board le board
     */
    public WarGame(ClassicBoard board) {
        super(board, 10);
        this.board = board;
        Action deploy = new DeployWarGame(this.board);
        Action don = new DoNothing();
        this.exchange = new ExchangeRessourceWarGame(this.board);
        this.actions = new HashMap<>();
        this.actions.put("D", deploy);
        this.actions.put("R", don);
    }

    /** Méthodes qui renvoi les actions du joueur
     * @return : les actions
     */
    public HashMap<String, Action> getActions() {
        return this.actions;
    }

    /**Méthode qui permet de deployer un player
     * @param player le joueur qui deploi
     * @throws Exception des exception selon l'etat de la tuile
     */
    private void deploy(Player a) throws Exception {
        Army army = null;

        Scanner input = new Scanner(System.in);

        int x = 0;
        int y = 0;
        Tile tile;
        Tile t = null;
        boolean validTile = false;
        boolean d = true;/*Ce booléen permet de gérer les exceptions*/
        while (d) {
            try {
                if (a.isInterfactive()) {
                    System.out.print("Choisissez les coordonnées \n");
                    while (!validTile) {
                        try {
                            System.out.print("Valeur de y : ");
                            y = input.nextInt();
                            if (y < 0 || y >= this.board.getHeight()) {
                                throw new TilenotAvailableException(
                                    Colors.RED_BOLD +
                                    "Saisie Invalide" +
                                    Colors.RESET
                                );
                            }
                            System.out.print("Valeur de x : ");
                            x = input.nextInt();
                            if (x < 0 || x >= this.board.getWidth()) {
                                throw new TilenotAvailableException(
                                    Colors.RED_BOLD +
                                    "Saisie Invalide" +
                                    Colors.RESET
                                );
                            }
                            t = this.board.getTile(y, x);
                            if (
                                t instanceof UsableTile &&
                                this.board.allUsableTiles().contains(t)
                            ) validTile = true; else if (
                                t.isBusy()
                            ) System.out.println(
                                Colors.RED_BOLD +
                                "Tuile occupée." +
                                Colors.RESET
                            ); else System.out.println(
                                Colors.RED_BOLD +
                                "Tuile de type Ocean" +
                                Colors.RESET
                            );
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(
                                Colors.RED_BOLD +
                                "Cordonnées invalides." +
                                Colors.RESET
                            );
                        } catch (TilenotAvailableException e) {
                            System.out.println(
                                Colors.RED_BOLD +
                                "Saississez des valeurs entières" +
                                Colors.RESET
                            );
                        }
                    }

                    System.out.print(
                        "Choisissez la taille de l'armée à déployer : "
                    );
                    int nbArmee = input.nextInt();

                    String name = "armée ";
                    System.out.print("\n");
                    //tile = this.board.getTile(y, x);
                    army = new Army(name, t, 0, nbArmee, a);
                } else {
                    int taille;
                    ArrayList<Tile> theTiles = this.board.allUsableTiles();
                    int size = theTiles.size();
                    int valeur = this.random.randomInt(0, size - 1);
                    tile = theTiles.get(valeur);
                    if (!(tile instanceof Desert || tile instanceof Mountain)) {
                        taille = this.random.randomInt(1, 5);
                    } else {
                        taille = this.random.randomInt(1, 3);
                    }
                    army = new Army("random ", tile, 0, taille, a);
                }

                this.actions.get("D").realise(army, a);
                String src1 = null;
                if (a.isInterfactive()) {
                    System.out.print("Echanger [E/N] : ");

                    src1 = input.next();
                    while (true) {
                        if (src1.equalsIgnoreCase("E")) break; else if (
                            src1.equalsIgnoreCase("N")
                        ) break;
                        System.out.println(
                            "E : echanger ;  N : Ne pas echanger "
                        );
                        System.out.print("Echanger [E/N] : ");

                        src1 = input.next();
                    }
                    System.out.println("");
                } else {
                    int valeur = this.random.randomInt(0, 1);
                    ArrayList<String> tab = new ArrayList<>();
                    tab.add("E");
                    tab.add("N");
                    src1 = tab.get(valeur);
                    if (
                        a.getDeployedCharacters().size() == 0 &&
                        a.getResource().size() > 0
                    ) {
                        src1 = "E";
                    }
                }
                if (src1.toUpperCase().equals("E")) {
                    if (a.getResource().size() > 0) {
                        try {
                            this.exchange.realise(null, (WarPlayer) a);
                        } catch (RessourceNotAvailableException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println(
                            Colors.RED_BOLD +
                            "**Vous n'avez pas de ressource à échanger** \n" +
                            Colors.RESET
                        );
                    }
                }
                d = false;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(
                    Colors.RED_BOLD +
                    "La tuile n'est pas une tuile du tableau" +
                    Colors.RESET
                );
                input = new Scanner(System.in);
            } catch (TooManyCharacterforMountain e) {
                System.out.println(
                    Colors.RED_BOLD +
                    "Veuillez réduire la taille de l'armée \ncar vous êtes sur une montagne" +
                    Colors.RESET
                );

                input = new Scanner(System.in);
            } catch (TooManyCharacterforDesert e) {
                System.out.println(
                    Colors.RED_BOLD +
                    "Veuillez réduire la taille de l'armée \ncar vous êtes sur un desert" +
                    Colors.RESET
                );

                input = new Scanner(System.in);
            } catch (WrongSizeCharacterException e) {
                System.out.println(
                    Colors.RED_BOLD +
                    "La taille devrait être comprise entre 1 et 5" +
                    Colors.RESET
                );

                input = new Scanner(System.in);
            } catch (TilenotAvailableException e) {
                System.out.println(
                    Colors.RED_BOLD + "Tuile non valide " + Colors.RESET
                );

                input = new Scanner(System.in);
            } catch (InputMismatchException e) {
                System.out.println(
                    Colors.RED_BOLD +
                    "Saisissez des entiers valides " +
                    Colors.RESET
                );

                input = new Scanner(System.in);
            }
            //System.out.println("\n");
        }
    }

    /** Méthode qui permet de gerer un round
     * @throws Exception pas d'exception
     */
    public void oneRound() throws Exception {
        Iterator<Player> it_thePlayers = this.thePlayers.iterator();
        WarPlayer nextPlayer = (WarPlayer) this.nextPlayer(it_thePlayers);
        String src;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~Board~~~~~~~~~~~~~~~~~~~~~\n");
        this.board.displayBoard();
        System.out.println(
            "Voici les coordonnées des tuiles disponibles (y,x): "
        );
        System.out.println(this.board.getCoordinates());
        System.out.println(
            "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        );
        while (nextPlayer != null && this.board.getCoordinates().size() != 0) {
            System.out.println(
                "\n" + nextPlayer.getName() + " c'est votre tour"
            );
            if (nextPlayer.isInterfactive()) {
                Scanner input = new Scanner(System.in);
                System.out.println("D : Déployer ;  R : Ne rien faire ");

                System.out.println(
                    Colors.GREEN_BOLD +
                    "**********************()()()()()()********************* " +
                    Colors.RESET
                );
                System.out.print("Choisissez une action : ");

                src = input.next();
                while (!this.actions.containsKey(src.toUpperCase())) {
                    System.out.println("D : Déployer ;  R : Ne rien faire ");
                    System.out.print("Choisissez une action : ");
                    src = input.next();
                }
            } else {
                int valeur = this.random.randomInt(0, 1);
                ArrayList<String> tab = new ArrayList<>();
                tab.add("D");
                tab.add("R");

                src = tab.get(valeur);
            }
            System.out.println("\n");

            if (src.toUpperCase().equals("D")) {
                System.out.println(nextPlayer);
                this.deploy(nextPlayer);
            }

            if (src.toUpperCase().equals("R")) {
                this.actions.get("R").toString();
            }

            nextPlayer.feedAllCharacter();

            nextPlayer.harvest();
            this.addPoints(nextPlayer);
            System.out.println(nextPlayer.toString());
            System.out.print(
                Colors.GREEN +
                "******************************************************************" +
                Colors.RESET
            );
            nextPlayer = (WarPlayer) this.nextPlayer(it_thePlayers);
        }
    }

    /** Méthode qui exécute le jeu
     * elle fait apelle a oneRound() nbTours
     * @throws Exception pas d'exception
     */
    @Override
    public void play() throws Exception {
        int i = 0;
        while (i < this.getNbTours() && board.getCoordinates().size() != 0) {
            System.out.println(
                Colors.GREEN +
                "\n======================================Round " +
                (i + 1) +
                "=========================================" +
                Colors.RESET
            );
            this.oneRound();
            i = i + 1;
            System.out.print("\n");
            this.displayOneRound();
            System.out.println(
                Colors.GREEN +
                "=========================================================================" +
                Colors.RESET
            );
        }

        System.out.println("***************Jeu terminé***************");
        for (Player p : this.thePlayers) {
            p.setTotalPoints();
            System.out.println(
                p.getName() + " a " + p.getPoints() + " points."
            );
        }
        Player winner = winner();
        if (winner != null) System.out.println(
            Colors.GREEN + "Gagnant : " + winner.getName() + Colors.RESET
        ); else System.out.println(
            Colors.GREEN + "Pas de gagnant." + Colors.RESET
        );

        System.out.println("*****************************************");
    }

    /** Méthode qui affiche le déroulement d'un round
     */
    public void displayOneRound() {
        for (Player p : this.thePlayers) {
            System.out.print(
                "Ressources de " + p.getName() + " " + p.showResources() + "\n"
            );
        }
    }
}
