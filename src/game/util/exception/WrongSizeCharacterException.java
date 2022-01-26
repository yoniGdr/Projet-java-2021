/**
 *
 */
package game.util.exception;

/**
 * @author amevigbe
 *
 */
public class WrongSizeCharacterException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public WrongSizeCharacterException(String message) {
        super(message);
    }

    public WrongSizeCharacterException() {
        super();
    }
}
