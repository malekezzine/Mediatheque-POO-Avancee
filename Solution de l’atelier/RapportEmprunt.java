
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RapportEmprunt implements AutoCloseable {

    private final List<String> titres = new ArrayList<>();
    private boolean ferme = false;

    public void enregistrer(String titre) {
        if (ferme) {
            throw new IllegalStateException(
                    "Le rapport est deja ferme");
        }
        titres.add(titre);
    }

    public List<String> getTitres() {
        return Collections.unmodifiableList(titres);
    }

    public boolean estFerme() {
        return ferme;
    }

    @Override
    public void close() {
        ferme = true;
    }
}
