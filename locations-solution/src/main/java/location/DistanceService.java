package location;

import java.util.Optional;

public class DistanceService {

    private LocationRepository repository;

    public DistanceService(LocationRepository repository) {
        this.repository = repository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {
        Optional<Location> location1 = repository.findByName(name1);
        Optional<Location> location2 = repository.findByName(name2);

        if (location1.isPresent() && location2.isPresent()) {
            return Optional.of(location1.get().distanceFrom(location2.get()));
        }

        return Optional.empty();
    }
}
