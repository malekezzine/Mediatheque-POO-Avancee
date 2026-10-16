
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CauseEtRessourcesTest {

    @Test
    void causeChainee() {
        Path absent = Path.of("fichier-inexistant-test.txt");

        CatalogueCorrompuException ex = assertThrows(
            CatalogueCorrompuException.class,
            () -> ChargeurCatalogue.chargerTitres(absent)
        );

        assertTrue(
            ex.getMessage().contains("fichier-inexistant-test.txt")
        );

        assertInstanceOf(IOException.class, ex.getCause());
    }

  
@Test
void rapportFermeMemeSiEmpruntEchoue()
        throws MediathequeException {

    Catalogue<Document> catalogue = new Catalogue<>();

    catalogue.ajouter(
        new Livre("Clean Code", 2008, "R. Martin", 464)
    );

    EmpruntManager manager = new EmpruntManager(catalogue);

    // Premier emprunt : le livre devient indisponible
    manager.emprunter("Clean Code");

    RapportEmprunt rapport = new RapportEmprunt();

    // Deuxième emprunt : une exception doit être déclenchée
    try {
        assertThrows(
            DocumentIndisponibleException.class,
            () -> {
                try (RapportEmprunt r = rapport) {
                    manager.emprunter("Clean Code");
                    r.enregistrer("Clean Code");
                }
            }
        );
    } finally {
        // Vérifier que le rapport est fermé
        assertTrue(rapport.estFerme());
    }}}

