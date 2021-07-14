package locations;

import locations.entity.Location;
import locations.service.LocationsService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

    @Test
    void getLocations() {
        ModelMapper modelmapper = new ModelMapper();

        LocationsService locationsService = new LocationsService(modelmapper);
        assertThat(locationsService.getLocationsList())
                .hasSize(3)
                .extracting(Location::getName).contains("Nézsa", "Vác", "Budapest");
    }
}