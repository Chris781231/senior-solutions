package locations;

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
        when(service.getLocationsList()).thenReturn(List.of(new Location("Nézsa", 15, 18)));
        assertThat(controller.getLocationsString())
                .startsWith("Nézsa")
                .contains("15", "18");
    }
}