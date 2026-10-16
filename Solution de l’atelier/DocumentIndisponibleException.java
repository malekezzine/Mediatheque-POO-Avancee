
public class DocumentIndisponibleException
        extends MediathequeException {

    public DocumentIndisponibleException(String titre) {
        super("Document deja emprunte : " + titre);
    }

    public DocumentIndisponibleException(
            String titre, String motif) {
        super("Document indisponible (" + motif + ") : " + titre);
    }
}
