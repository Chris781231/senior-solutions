package location;

import java.util.Optional;

public interface LocationRepository {

    Optional<Location> findByName(String name);
}
