package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationsController {

    private LocationsService service;

    public LocationsController(LocationsService service) {
        this.service = service;
    }

    @GetMapping("/locations")
    public String getLocations() {
        StringBuilder sb = new StringBuilder();

        for (Location location : service.getLocations()) {
            sb.append(location.getId() != null ? location.getId() + ". " : "");
            sb.append(location.getName()).append(" (");
            sb.append(location.getLat()).append(", ");
            sb.append(location.getLon()).append(")<br>");
        }

        return sb.toString();
    }
}
