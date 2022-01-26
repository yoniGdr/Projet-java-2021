package game.util.exception;

public class NoUsableTileAvailableException extends Exception {

    /** Expetion qui est declancher quand la 
     * tuile non disponible
     */
    private static final long serialVersionUID = 1L;

    public NoUsableTileAvailableException() {
        super();
    }

    public NoUsableTileAvailableException(String msg) {
        super(msg);
    }
}
