package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(new AtomicLong(1), "Nézsa", 47.020535, 19.123456),
            new Location(new AtomicLong(2), "Vác", 47.020535, 19.123459),
            new Location(new AtomicLong(3), "Budapest", 47.020534, 19.123451))));

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

    public LocationDto findLocationById(AtomicLong id) {
        return modelMapper.map(locations.stream()
                        .filter(e -> e.getId().get() == id.get())
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Location not found: " + id)),
                LocationDto.class);
    }

    public List<Location> getLocationsList() {
        return locations;
    }
}
