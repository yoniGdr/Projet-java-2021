package game.farmGame;

import game.*;
import game.action.*;
import game.board.*;
import game.tile.*;
import game.util.exception.*;
import game.util.io.Colors;
import java.util.*;

public class FarmGame extends Game {


    /** Constructeur de la classe FarmGame
     * @param board : plateau de jeu
     */
    public FarmGame(ClassicBoard board) {
        super(board, 10);
        this.board = board;
        this.actions = new HashMap<>();
        this.actions.put("D", new DeployFarmGame(board));
        this.actions.put("R", new DoNothingFarmGame());
        this.actions.put("E", new ExchangeRessourceFarmGame());
    }



    /**Méthode qui permet de deployer un player
     * @param player
     * @throws IndexOutOfBoundsException Déclanche l'exeption si player est hors limites
     */
    private void deploy(Player player) throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean valideTile = false;
        Tile t = null;
        int x = 0;
        int y = 0;
        while (!valideTile) {
            try {
                System.out.print("Valeur y : ");
                y = scan.nextInt();
                System.out.print("Valeur x : ");
                x = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(
                    Colors.RED + "Cordonnées invalides." + Colors.RESET
                );
                scan = new Scanner(System.in);
                continue;
            }
            try {
                t = this.board.getTile(y, x);
                if (
                    t instanceof UsableTile &&
                    this.board.allUsableTiles().contains(t)
                ) valideTile = true; else if (t.isBusy()) System.out.println(
                    Colors.RED + "Tuile occupée." + Colors.RESET
                ); else System.out.println(
                    Colors.RED + "Tuile de type Ocean" + Colors.RESET
                );
            } catch (IndexOutOfBoundsException e) {
                System.out.println(
                    Colors.RED + "Cordonnées invalides." + Colors.RESET
                );
            }
        }

        System.out.print("\n- ");
        this.actions.get("D").realise(player.createCharcater(t, 1), player);
    }

    /** Méthode qui permet de gerer un round
     * @throws NoUsableTileAvailableException déclanche l'exeption 
     * quand aucune tuile utilisable est disponible
     */
    public void oneRound() throws NoUsableTileAvailableException {
        if (
            this.board.allUsableTiles().isEmpty()
        ) throw new NoUsableTileAvailableException();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~Board~~~~~~~~~~~~~~~~~~~~~\n");
        this.board.displayBoard();
        System.out.println("Voici les coordonnées des tuiles disponibles (y,x): ");
        System.out.println(this.board.getCoordinates());
        System.out.println(
            "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        );
        Scanner scan = new Scanner(System.in);
        String action;
        FarmPlayer player;
        for (int i = 0; i < thePlayers.size(); i++) {
            player = (FarmPlayer) thePlayers.get(i);
            if (player.isInterfactive()) {
                System.out.println(
                    "D : Déployer ;  R : Ne rien faire ; E : Echanger"
                );
                System.out.print(
                    player.getName() + " choisissez une action : "
                );

                action = scan.nextLine();
                System.out.println("");
                while (!actions.containsKey(action.toUpperCase())) {
                    System.out.println(
                        Colors.RED +
                        "Action invalide (soit D soit E soit R)" +
                        Colors.RESET
                    );
                    System.out.print(
                        player.getName() + " choisissez une action : "
                    );
                    action = scan.nextLine();
                }
                try {
                    if (action.equalsIgnoreCase("D")) {
                        this.deploy(player);
                    } else {
                        System.out.print("- ");
                        actions.get(action.toUpperCase()).realise(null, player);
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                }
            } else { //aleatoire
                ArrayList<String> actionsRandom = new ArrayList<>();
                actionsRandom.add("D");
                actionsRandom.add("R");
                if (player.getResource().size() > 0) actionsRandom.add("E");
                if (
                    player.getGold() <= 8 &&
                    player.getDeployedCharacters().size() >= 1
                ) actionsRandom.add("E");
                action =
                    actionsRandom.get(random.randomInt(0,actionsRandom.size()-1));

                if (action.equalsIgnoreCase("D")) {
                    //this.board.displayBoard();
                    ArrayList<Tile> usableTiles = this.board.allUsableTiles();
                    if (
                        usableTiles.isEmpty()
                    ) throw new NoUsableTileAvailableException();

                    Tile t = usableTiles.get(
                        random.randomInt(0, usableTiles.size() - 1)
                    );
                    try {
                        System.out.print("- ");
                        actions
                            .get(action.toUpperCase())
                            .realise(player.createCharcater(t, 1), player);
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                } else {
                    try {
                        System.out.print("- ");
                        actions.get(action.toUpperCase()).realise(null, player);
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
            }

            System.out.print("\n");
            player.harvest();
            player.payDeployedCharacters();
        }
    }

    /** Méthode qui exécute le jeu
     * elle fait apelle a oneRound() nbTours
     * @throws NoPlayersInGameException déclanche l'exception 
     * quand il n'y a pas de joueurs dans le jeu
     */
    public void play() throws NoPlayersInGameException {
        for (int i = 0; i < NbTours; i++) {
            System.out.println(
                Colors.GREEN +
                "======================================Round " +
                (i + 1) +
                "=========================================" +
                Colors.RESET
            );
            try {
                displayOneRound();
                this.oneRound();
                System.out.println(
                    Colors.GREEN +
                    "======================================================================================\n" +
                    Colors.RESET
                );
            } catch (NoUsableTileAvailableException e) {
                System.out.println(
                    Colors.RED+"Il n'y a plus de tuiles disponible, le jeu est terminé.\n"+Colors.RESET
                );
                break;
            }
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
            Colors.GREEN+"Gagnant : " + winner.getName()+Colors.RESET
        ); else System.out.println(
            Colors.GREEN+
            "Pas de gagnant."+
            Colors.RESET
            );

        System.out.println("*****************************************");
    }


    /** Méthode qui affiche le déroulement d'un round
     *
     */
    public void displayOneRound() {
        System.out.println("");
        for (Player p : this.thePlayers) {
            System.out.println(
                p.getName() + " a " + p.getGold() + " pieces d'or."
            );
            System.out.print(
                "Ressources de " + p.getName() + " " + p.showResources() + "\n"
            );

            System.out.println(
                "Points actuelles de " +
                p.getName() +
                " : " +
                p.countPoint() +
                "."
            );

            System.out.println("");
        }
        System.out.println("");
    }
}
