
public abstract class MediathequeException extends Exception {

    // Choix : checked exception.
    // L'erreur est un cas metier que l'appelant
    // doit pouvoir traiter.

    protected MediathequeException(String message) {
        super(message);
    }

    protected MediathequeException(String message, Throwable cause) {
        super(message, cause);
    }
}
