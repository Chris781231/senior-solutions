package locations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService();
        assertThat(locationsService.getLocations())
                .hasSize(3)
                .extracting(Location::getName).contains("Nézsa", "Vác", "Budapest");
    }
}