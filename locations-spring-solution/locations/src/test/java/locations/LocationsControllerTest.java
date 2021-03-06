package locations;

import locations.controller.LocationsController;
import locations.dto.LocationDto;
import locations.entity.Location;
import locations.service.LocationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService service;

    @InjectMocks
    LocationsController controller;

    @Test
    void getLocations() {
        when(service.getLocationsList()).thenReturn(List.of(new LocationDto(1L, "Nézsa", 15, 18)));
        assertThat(controller.getLocationsString())
                .contains("Nézsa", "15", "18");
    }
}