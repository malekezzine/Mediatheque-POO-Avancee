
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpruntManagerTest {

    @Test
    void documentInconnu() {
        EmpruntManager manager =
                new EmpruntManager(new Catalogue<>());

        DocumentIntrouvableException ex = assertThrows(
                DocumentIntrouvableException.class,
                () -> manager.emprunter("Fantome")
        );

        assertTrue(ex.getMessage().contains("Fantome"));
    }

    @Test
    void doubleEmprunt() throws MediathequeException {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(
                new Livre("Clean Code", 2008, "R. Martin", 464)
        );

        EmpruntManager manager = new EmpruntManager(catalogue);
        manager.emprunter("Clean Code");

        DocumentIndisponibleException ex = assertThrows(
                DocumentIndisponibleException.class,
                () -> manager.emprunter("Clean Code")
        );

        assertTrue(ex.getMessage().contains("Clean Code"));
    }

    @Test
    void revueNonEmpruntable() {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Revue("Science", 2026, 412));

        EmpruntManager manager = new EmpruntManager(catalogue);

        DocumentIndisponibleException ex = assertThrows(
                DocumentIndisponibleException.class,
                () -> manager.emprunter("Science")
        );

        assertTrue(ex.getMessage().contains("Science"));
    }

    @Test
    void retourPuisNouvelEmprunt() throws MediathequeException {
        Livre livre = new Livre("Dune", 1965, "F. Herbert", 612);

        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(livre);

        EmpruntManager manager = new EmpruntManager(catalogue);
        manager.emprunter("Dune");

        assertFalse(livre.estDisponible());

        livre.rendre();

        assertDoesNotThrow(() -> manager.emprunter("Dune"));
    }
}
