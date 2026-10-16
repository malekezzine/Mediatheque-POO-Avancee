
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ChargeurCatalogue {

    public static List<String> chargerTitres(Path chemin)
            throws CatalogueCorrompuException {

        try (BufferedReader lecteur =
                     Files.newBufferedReader(chemin)) {

            return lecteur.lines().collect(Collectors.toList());

        } catch (IOException e) {
            throw new CatalogueCorrompuException(
                    "Echec du chargement : " + chemin, e);
        }
    }
}
