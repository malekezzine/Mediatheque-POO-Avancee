
import java.util.Optional;

public class Bibliothecaire {

    private final String nom;
    private final Catalogue<Document> catalogue;

    public Bibliothecaire(
            String nom, Catalogue<Document> catalogue) {
        this.nom = nom;
        this.catalogue = catalogue;
    }

    public void accueillir() {
        System.out.println("Bonjour, " + nom);
    }

    public Optional<Document> chercher(String titre) {
        return catalogue.rechercherParTitre(titre);
    }
}
