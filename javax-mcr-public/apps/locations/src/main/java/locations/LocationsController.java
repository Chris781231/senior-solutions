package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationsController {

    private List<Location> locations = List.of(
            new Location("Nézsa", 0.001243, 12.123103),
            new Location("Vác", 0.123456, 12.123456)
    );

    @GetMapping("/locations")
    public String getLocations() {
        return locations.toString();
    }
}
