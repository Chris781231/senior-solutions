package location;

import java.util.ArrayList;
import java.util.List;

public class LocationOperators {

    public List<Location> filterOnNorth(List<Location> locations) {
        return locations.stream()
                .filter(l -> l.getLat() > 0)
                .toList();
    }
}
