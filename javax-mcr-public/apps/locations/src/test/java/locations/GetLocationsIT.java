package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GetLocationsIT {

    @Autowired
    private LocationsController controller;

    @Test
    void getLocations() {
        String locations = controller.getLocations();

        assertThat(locations)
                .startsWith("Nézsa")
                .contains("Vác", "Budapest")
                .contains("47.02", "19.12");
    }
}
