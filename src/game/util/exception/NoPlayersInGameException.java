package game.util.exception;

/**
 * An exception class for a game without players.
 */

public class NoPlayersInGameException extends Exception {

    /** Exeption qui est declancher quand il n'y a pas de joueur 
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of NoPlayersInGameException
     *
     * @param msg the message to display
     */
    public NoPlayersInGameException(String msg) {
        super(msg);
    }

    public NoPlayersInGameException() {
        super();
    }
}
