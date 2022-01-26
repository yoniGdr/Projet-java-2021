package farmGameMain;

import game.board.ClassicBoard;
import game.farmGame.*;

/**
 * main du jeu d'agricole
 */
public class FarmGameMain {

    /** le main
     * @param args les arguments
     * @throws Exception exceptions
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("\nUtilisation : ");
            System.out.println(
                "java -jar agricole.jar NomDuJouer NomDuJouer2   ..."
            );
            return;
        }

        ClassicBoard board = new ClassicBoard(10, 10);
        FarmGame game = new FarmGame(board);

        FarmPlayer[] players = new FarmPlayer[args.length];
        //in this game we will add the players in args
        for (int i = 0; i < args.length; i++) {
            players[i] = new FarmPlayer(args[i], false);
            game.addPlayer(players[i]); //add them to the game
        }
        System.out.println("\n");
        game.play();
    }
}
