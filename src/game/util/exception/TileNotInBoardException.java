package game.util.exception;

public class TileNotInBoardException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TileNotInBoardException() {
        super();
    }

    public TileNotInBoardException(String msg) {
        super(msg);
    }
}
