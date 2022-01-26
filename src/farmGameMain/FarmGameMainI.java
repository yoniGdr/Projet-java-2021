package farmGameMain;

import game.board.ClassicBoard;
import game.farmGame.*;

/**
 * main du jeu d'agricole
 */
public class FarmGameMainI {

    /** le main
     * @param args les arguments
     * @throws Exception exceptions
     */
    public static void main(String[] args) throws Exception {
        ClassicBoard board = new ClassicBoard(10, 10);
        FarmGame game = new FarmGame(board);
        if (args.length % 2 != 0 || args.length == 0) {
            System.out.println("\nUtilisation : ");
            System.out.println(
                "java -jar agricoleI.jar NomDuJouer true/false NomDuJouer2 true/false ..."
            );
            System.out.println(
                "true si le joueur est interactif, false sinon. "
            );
            return;
        }
        
        FarmPlayer[] players = new FarmPlayer[(args.length) + 2];
        //in this game we will add the players in args
        for (int i = 0; i < args.length; i += 2) {
            if (args[i + 1].equalsIgnoreCase("true")) players[i] =
                new FarmPlayer(args[i], true); else if (
                args[i + 1].equalsIgnoreCase("false")
            ) players[i] = new FarmPlayer(args[i], false); else {
                System.out.println("Utilisation : ");
                System.out.println(
                    "java -jar agricoleI.jar NomDuJouer true/false NomDuJouer2 true/false ..."
                );
                System.out.println(
                    "true si le joueur est interactif, false sinon. "
                );
                return;
            }
            game.addPlayer(players[i]); //add them to the game
        }
        System.out.println("\n");
        game.play();
    }
}
