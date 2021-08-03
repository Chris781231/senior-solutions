package locations.service;

import locations.entity.Location;
import locations.dto.LocationDto;
import locations.exceptionhandler.LocationNotFoundException;
import locations.command.CreateLocationCommand;
import locations.command.UpdateLocationCommand;
import locations.repository.LocationsRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class LocationsService {

    private LocationsRepository locationsRepo;

    private ModelMapper modelMapper;

    public List<LocationDto> getLocations(Optional<String> prefix, Optional<Double> minLat, Optional<Double> maxLat, Optional<Double> minLon, Optional<Double> maxLon) {
        List<Location> locations = locationsRepo.findAll();
        return locations.stream()
                .filter(l -> (prefix.isEmpty() || l.getName().toLowerCase().startsWith(prefix.get().toLowerCase())) &&
                        (minLat.isEmpty() || l.getLat() > minLat.get()) &&
                        (maxLat.isEmpty() || l.getLat() < maxLat.get()) &&
                        (minLon.isEmpty() || l.getLon() > minLon.get()) &&
                        (maxLon.isEmpty() || l.getLon() < maxLon.get()))
                .map(entity -> modelMapper.map(entity, LocationDto.class))
                .toList();
    }

    public LocationDto findLocationById(Long id) {
        Location location = locationsRepo.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(String.format("Cannot find id %d in locations", id)));
        return modelMapper.map(location, LocationDto.class);
    }

    public List<LocationDto> getLocationsList() {
        Type targetType = new TypeToken<List<LocationDto>>(){}.getType();
        return modelMapper.map(locationsRepo.findAll(), targetType);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(command.getName(), command.getLat(), command.getLon());
        locationsRepo.save(location);
        return modelMapper.map(location, LocationDto.class);
    }

    @Transactional
    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = locationsRepo.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(String.format("Cannot find id %d in locations", id)));

        if (command.getName() != null) { location.setName(command.getName()); }
        if (command.getLat() != null) { location.setLat(command.getLat()); }
        if (command.getLon() != null) { location.setLon(command.getLon()); }

        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = locationsRepo.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(String.format("Cannot find id %d in locations", id)));
        locationsRepo.delete(location);
    }

    public void deleteAll() {
        locationsRepo.deleteAll();
    }
}
