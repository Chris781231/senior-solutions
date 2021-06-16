package location;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocationService {

    private final LocationParser parser = new LocationParser();

    public List<Location> getLocationsFromFile(Path path) {
        List<Location> locations = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                locations.add(parser.parse(line));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file", ioe);
        }

        return locations;
    }
}
