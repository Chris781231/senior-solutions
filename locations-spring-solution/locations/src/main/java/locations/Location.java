package locations;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private Long id;

    private String name;

    private double lat;

    private double lon;

    public Location(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }
}
