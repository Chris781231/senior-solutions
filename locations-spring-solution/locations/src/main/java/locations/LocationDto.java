package locations;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class LocationDto {

    private AtomicLong id;

    private String name;

    private double lat;

    private double lon;
}
