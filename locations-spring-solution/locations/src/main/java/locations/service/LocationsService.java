package locations.service;

import locations.entity.Location;
import locations.entity.LocationDto;
import locations.exceptionhandler.LocationNotFoundException;
import locations.command.CreateLocationCommand;
import locations.command.UpdateLocationCommand;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private AtomicLong atomicLong = new AtomicLong();

    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(atomicLong.incrementAndGet(), "Nézsa", 47.020535, 19.123456),
            new Location(atomicLong.incrementAndGet(), "Vác", 47.020535, 19.123459),
            new Location(atomicLong.incrementAndGet(), "Budapest", 47.020534, 19.123451))));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations(Optional<String> prefix, Optional<Double> minLat, Optional<Double> maxLat, Optional<Double> minLon, Optional<Double> maxLon) {
        return locations.stream()
                .filter(l -> (prefix.isEmpty() || l.getName().toLowerCase().startsWith(prefix.get().toLowerCase())) &&
                        (minLat.isEmpty() || l.getLat() > minLat.get()) &&
                        (maxLat.isEmpty() || l.getLat() < maxLat.get()) &&
                        (minLon.isEmpty() || l.getLon() > minLon.get()) &&
                        (maxLon.isEmpty() || l.getLon() < maxLon.get()))
                .map(entity -> modelMapper.map(entity, LocationDto.class))
                .toList();
//        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
//        return modelMapper.map(locations, targetListType);
    }

    public LocationDto findLocationById(Long id) {
        return modelMapper.map(locations.stream()
                        .filter(e -> e.getId() == id)
                        .findFirst()
                        .orElseThrow(() -> new LocationNotFoundException("Location not found: " + id)),
                LocationDto.class);
    }

    public List<Location> getLocationsList() {
        return locations;
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(atomicLong.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        locations.add(location);
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Cannot find location: " + id));

        if (command.getName() != null) { location.setName(command.getName()); }
        if (command.getLat() != 0.0) { location.setLat(command.getLat()); }
        if (command.getLon() != 0.0) { location.setLon(command.getLon()); }

        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Cannot found location: " + id));
        locations.remove(location);
    }

    public void deleteAll() {
        atomicLong = new AtomicLong();
        locations.clear();
    }
}
