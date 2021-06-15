package locations;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Location {

    @Getter
    private Long id;

    @Getter
    private final String name;

    @Getter
    private final double lat;

    @Getter
    private final double lon;

    public Location(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }
}
