/**
 *
 */
package game.util.exception;

/**
 * @author amevigbe
 *
 */
public class RessourceNotAvailableException extends Exception {

    /** se déclanche quand la ressource est non disponible
     *
     */
    private static final long serialVersionUID = 1L;

    public RessourceNotAvailableException(String message) {
        super(message);
    }

    public RessourceNotAvailableException() {
        super();
    }
}
