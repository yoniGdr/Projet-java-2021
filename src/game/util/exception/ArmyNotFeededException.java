package game.util.exception;

public class ArmyNotFeededException extends Exception {

    /** exception qui est déclancher quand
     * l'armé n'est pas nourrie
     */
    private static final long serialVersionUID = 1L;

    public ArmyNotFeededException() {
        super();
    }

    public ArmyNotFeededException(String msg) {
        super(msg);
    }
}
