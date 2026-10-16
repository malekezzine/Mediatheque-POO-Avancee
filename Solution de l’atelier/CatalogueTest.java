
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {

    @Test
    void empruntNominal() throws MediathequeException {
        Livre livre = new Livre(
                "Clean Code", 2008, "R. Martin", 464);

        livre.emprunter();
        assertFalse(livre.estDisponible());

        livre.rendre();
        assertTrue(livre.estDisponible());
    }

    @Test
    void doubleEmpruntLeveException()
            throws MediathequeException {

        Livre livre = new Livre(
                "Refactoring", 1999, "M. Fowler", 448);

        livre.emprunter();

        DocumentIndisponibleException ex = assertThrows(
                DocumentIndisponibleException.class,
                () -> livre.emprunter()
        );

        assertTrue(ex.getMessage().contains("Refactoring"));
    }

    @Test
    void rechercheInfructueuse() {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Revue("Science", 2026, 412));

        assertTrue(
                catalogue.rechercherParTitre("Nature").isEmpty()
        );
    }

    @Test
    void rechercheIgnoreLaCasse() {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Dvd("Inception", 2010, 148));

        assertTrue(
                catalogue.rechercherParTitre("INCEPTION").isPresent()
        );
    }
}
