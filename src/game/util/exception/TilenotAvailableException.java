/**
 *
 */
package game.util.exception;

/**
 * @author amevigbe
 *
 */
public class TilenotAvailableException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TilenotAvailableException(String message) {
        super(message);
    }

    public TilenotAvailableException() {
        super();
    }
}
