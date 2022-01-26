package warGameMain;

import game.*;
import game.board.ClassicBoard;
import game.warGame.*;
import game.util.io.*;
/**
 * @author amevigbe
 *
 */
public class WarGameMainI {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        
        ClassicBoard board = new ClassicBoard(10, 10);
        WarGame game = new WarGame(board);
        if (args.length % 2 != 0) {
            System.out.println("\nUtilisation : ");
            System.out.println(
                "java -jar guerreI.jar NomDuJouer true/false NomDuJouer2 true/false     ..."
            );
            System.out.println(
                "true si le joueur est interactif, false sinon. "
            );
            return;
        }
        WarPlayer[] players = new WarPlayer[(args.length)+2];
        //in this game we will add the players in args
        for (int i = 0; i < args.length; i+=2) {
            if (args[i + 1].equalsIgnoreCase("true"))
                players[i] = new WarPlayer(args[i], true); 
            else if (args[i + 1].equalsIgnoreCase("false")) 
                players[i] = new WarPlayer(args[i], false);
            else {
                System.out.println("Utilisation : ");
                System.out.println(
                    "java -jar guerreI.jar NomDuJouer true/false NomDuJouer2 true/false ..."
                );
                System.out.println(
                    "true si le joueur est interactif, false sinon. "
                );
                return;
            }
            game.addPlayer(players[i]); //add them to the game
        }
        System.out.println("\n");
        
        board.displayBoard();
        
        System.out.println("");
        System.out.println(board.getCoordinates());

        for (Player a : game.getPlayers()) {
            System.out.print("\n\n");
            System.out.println(Colors.GREEN+"========================================================================="+Colors.RESET);
            System.out.println(a.toString());
            System.out.println(Colors.GREEN+"========================================================================="+Colors.RESET);
        }

        game.play();
    }
}
