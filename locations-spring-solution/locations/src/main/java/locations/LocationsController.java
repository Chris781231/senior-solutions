package locations;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    private LocationsService service;

    public LocationsController(LocationsService service) {
        this.service = service;
    }

    @GetMapping
    public List<LocationDto> getLocations(
            @RequestParam Optional<String> prefix,
            @RequestParam Optional<Double> minLat,
            @RequestParam Optional<Double> maxLat,
            @RequestParam Optional<Double> minLon,
            @RequestParam Optional<Double> maxLon) {
        return service.getLocations(prefix, minLat, maxLat, minLon, maxLon);
    }

    @GetMapping("/string")
    public String getLocationsString() {
        StringBuilder sb = new StringBuilder();

        for (Location location : service.getLocationsList()) {
            sb.append(location.getId() != null ? location.getId() + ". " : "");
            sb.append(location.getName()).append(" (");
            sb.append(location.getLat()).append(", ");
            sb.append(location.getLon()).append(")<br>");
        }

        return sb.toString();
    }

    @GetMapping("/{id}")
    public LocationDto findLocationById(@PathVariable("id") AtomicLong id) {
        return service.findLocationById(id);
    }
}
