package game.util.exception;

public class NotEnoughGoldException extends Exception {

    /** Expetion qui est declancher quand le joueur ne 
     * possède pas assez de gold
     */

    private static final long serialVersionUID = 1L;

    public NotEnoughGoldException(String message) {
        super(message);
    }

    public NotEnoughGoldException() {
        super();
    }
}
