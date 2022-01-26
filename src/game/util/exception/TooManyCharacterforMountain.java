/**
 *
 */
package game.util.exception;

/**
 * @author amevigbe
 *
 */
public class TooManyCharacterforMountain extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     */

    public TooManyCharacterforMountain(String message) {
        super(message);
    }

    public TooManyCharacterforMountain() {
        super();
    }
}
