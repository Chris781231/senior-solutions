package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LocationsController {

    private List<Location> locations = List.of(
            new Location("Nézsa", 47.0205, 19.2512),
            new Location("Vác", 47.0206, 19.2511)
    );

    @GetMapping("/")
    @ResponseBody
    public String getLocations() {
        return locations.toString();
    }
}
