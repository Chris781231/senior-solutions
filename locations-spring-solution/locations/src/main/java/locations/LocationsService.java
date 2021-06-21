package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location("Nézsa", 47.020535, 19.123456),
            new Location("Vác", 47.020535, 19.123459),
            new Location("Budapest", 47.020534, 19.123451))));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations() {
        return locations.stream()
                .map(entity -> modelMapper.map(entity, LocationDto.class))
                .toList();
//        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
//        return modelMapper.map(locations, targetListType);
    }

    public List<Location> getLocationsList() {
        return locations;
    }
}
