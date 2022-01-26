package game.util.exception;

public class NotEnoughFoodException extends Exception {

    /** Expetion qui est declancher quand le joueur ne 
     * possède pas assez de nourriture
     *
     */

    private static final long serialVersionUID = 1L;

    public NotEnoughFoodException(String message) {
        super(message);
    }

    public NotEnoughFoodException() {
        super();
    }
}
