package locations;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations = List.of(
            new Location("Nézsa", 47.020535, 19.123456),
            new Location("Vác", 47.020535, 19.123459),
            new Location("Budapest", 47.020534, 19.123451));

    public List<Location> getLocations() {
        return new ArrayList<>(locations);
    }
}
