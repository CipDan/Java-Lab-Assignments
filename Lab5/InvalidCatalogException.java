package Lab5;

/**
 * Implementation of a exception thrown when the loaded object is not a catalog.
 */
public class InvalidCatalogException extends Exception {
    public InvalidCatalogException() {
        super("Invalid catalog file!");
    }
}
