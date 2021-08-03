package locations.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import locations.entity.Location;
import locations.dto.LocationDto;
import locations.service.LocationsService;
import locations.command.CreateLocationCommand;
import locations.command.UpdateLocationCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    private LocationsService service;

    public LocationsController(LocationsService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List locations (optional by prefix, min/max latitude, min/max longitude")
    public List<LocationDto> getLocations(
            @RequestParam Optional<String> prefix,
            @RequestParam Optional<Double> minLat,
            @RequestParam Optional<Double> maxLat,
            @RequestParam Optional<Double> minLon,
            @RequestParam Optional<Double> maxLon) {
        return service.getLocations(prefix, minLat, maxLat, minLon, maxLon);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a location by id")
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return service.findLocationById(id);
    }

    @GetMapping("/string")
    @Operation(summary = "List locations (This method is not called by service, StringBuilder is under the hood")
    public String getLocationsString() {
        StringBuilder sb = new StringBuilder();

        for (LocationDto location : service.getLocationsList()) {
            sb.append(location.getId() != null ? location.getId() + ". " : "");
            sb.append(location.getName()).append(" (");
            sb.append(location.getLat()).append(", ");
            sb.append(location.getLon()).append(")<br>");
        }

        return sb.toString();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a location")
    @ApiResponse(responseCode = "201", description = "Location has been created")
    public LocationDto createLocation(@RequestBody @Valid CreateLocationCommand command) {
        return service.createLocation(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a location by id")
    public LocationDto updateLocation(@PathVariable("id") long id, @RequestBody @Valid UpdateLocationCommand command) {
        return service.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a location by id")
    @ApiResponse(responseCode = "204", description = "Location has been deleted.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id) {
        service.deleteLocation(id);
    }

    @DeleteMapping
    @Operation(summary = "Delete all of locations")
    @ApiResponse(responseCode = "204", description = "Locations have been deleted.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        service.deleteAll();
    }
}
