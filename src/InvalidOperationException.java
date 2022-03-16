/**
 * Represents an invalid arithmetic operation.
 * @author Leonardo Ledda (LeddaZ)
 */
public class InvalidOperationException extends ArithmeticException {

    /**
     * Creates an exception without an error message.
     */
    public InvalidOperationException() {
        super();
    }

    /**
     * Creates an exception with an error message.
     * @param message error message
     */
    public InvalidOperationException(String message) {
        super(message);
    }

}
