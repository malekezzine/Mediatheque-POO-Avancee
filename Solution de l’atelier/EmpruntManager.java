
public class EmpruntManager {

    private final Catalogue<Document> catalogue;

    public EmpruntManager(Catalogue<Document> catalogue) {
        this.catalogue = catalogue;
    }

    public void emprunter(String titre)
            throws MediathequeException {

        Document document = catalogue.rechercherParTitre(titre)
                .orElseThrow(
                    () -> new DocumentIntrouvableException(titre)
                );

        if (!(document instanceof Empruntable)) {
            throw new DocumentIndisponibleException(
                titre, "consultation sur place uniquement"
            );
        }

        Empruntable empruntable = (Empruntable) document;
        empruntable.emprunter();
    }
}
