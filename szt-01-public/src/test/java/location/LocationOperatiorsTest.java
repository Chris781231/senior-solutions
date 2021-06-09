package location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationOperatiorsTest {

    private LocationOperatiors operators;
    private Location loc;
    private Location locIsOnEquator;
    private Location locIsOnPrimeMeridian;

    @BeforeEach
    void init() {
        operators = new LocationOperatiors();
        loc = new Location("Budapest",47.497912,19.040235);
        locIsOnEquator = new Location("Equador",0.0,-78.5706226);
        locIsOnPrimeMeridian = new Location("Greenwich",51.477928,0.0);
    }

    @Test
    void testFilterOnNorth() {
        List<Location> expected = operators.filterOnNorth(List.of(loc, locIsOnEquator, locIsOnPrimeMeridian));

        assertEquals(List.of("Budapest", "Greenwich"),
                expected.stream().map(Location::getName).toList());
    }
}
