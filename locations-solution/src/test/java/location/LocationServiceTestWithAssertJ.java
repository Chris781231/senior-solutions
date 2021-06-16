package location;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SoftAssertionsExtension.class)
public class LocationServiceTestWithAssertJ {

    @Test
    void testThrowsExceptionWhenCannotReadFile() {
        assertThrows(IllegalStateException.class, () -> new LocationService().getLocationsFromFile(Path.of("invalid.csv")));
    }

    @Test
    void testGetLocationsFromFile() {
        LocationService service = new LocationService();
        List<Location> result = service.getLocationsFromFile(Path.of("locations.csv"));

        assertThat(result)
                .hasSize(3)
                .extracting(Location::getName, Location::getLat)
                .contains(tuple("Equador",0.0));
    }

    @Test
    void testGetLocationsFromFileBySoftly(SoftAssertions softly) {
        LocationService service = new LocationService();
        List<Location> result = service.getLocationsFromFile(Path.of("locations.csv"));

        softly.assertThat(result).hasSize(3);
        softly.assertThat(result).extracting(Location::getName).contains("Budapest");
    }
}
