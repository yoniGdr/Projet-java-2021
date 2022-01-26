package warGameMain;

import game.*;
import game.board.ClassicBoard;
import game.warGame.*;
import game.util.io.*;
/**
 * @author amevigbe
 *
 */
public class WarGameMain {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        if (args.length== 0) {
            System.out.println("\nUtilisation : ");
            System.out.println(
                "java -jar guerre.jar NomDuJouer NomDuJouer2   ..."
            );
            return;
        }

        ClassicBoard board = new ClassicBoard(10, 10);
        WarGame game = new WarGame(board);
        
        WarPlayer[] players = new WarPlayer[args.length];
        //in this game we will add the players in args
        for(int i =0;i<args.length;i++){
            players[i] = new WarPlayer(args[i],false);
            game.addPlayer(players[i]); //add them to the game
        }
     
        // System.out.println("Voici les tuiles qui sont de disponibles : ");
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
