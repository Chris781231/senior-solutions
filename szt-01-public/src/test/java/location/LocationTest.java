package location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    private LocationParser parser;
    private String locText;
    private String locIsOnEquatorText;
    private String locIsOnPrimeMeridianText;

    @BeforeEach
    void init() {
        parser = new LocationParser();
        locText = "Budapest,47.497912,19.040235";
        locIsOnEquatorText = "Equador,0.0,-78.5706226";
        locIsOnPrimeMeridianText = "Greenwich,51.477928,0.0";
    }

    @Test
    @DisplayName("Tests for parse() method")
    void testParse() {
        Location location = parser.parse(locText);

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLat(), 0.005);
        assertEquals(19.040235, location.getLon(), 0.005);
    }

    @Test
    @DisplayName("Test for parse() method with assertAll() method")
    void testParseWithAssertAll() {
        Location location = parser.parse(locText);

        assertAll(
                () -> assertEquals("Budapest", location.getName()),
                () -> assertEquals(47.497912, location.getLat(), 0.005),
                () -> assertEquals(19.040235, location.getLon(), 0.005)
        );
    }

    @Test
    @DisplayName("Tests for isOnEquator() method")
    void testIsOnEquator() {
        Location loc1 = parser.parse(locText);
        Location loc2 = parser.parse(locIsOnEquatorText);

        assertFalse(parser.isOnEquator(loc1));
        assertTrue(parser.isOnEquator(loc2));
    }

    @Test
    @DisplayName("Tests for isOnPrimeMeridian() method")
    void testIsOnPrimeMeridian() {
        Location loc1 = parser.parse(locText);
        Location loc2 = parser.parse(locIsOnPrimeMeridianText);

        assertFalse(parser.isOnPrimeMeridian(loc1));
        assertTrue(parser.isOnPrimeMeridian(loc2));
    }

    @Test
    @DisplayName("Test for instance identity")
    void testInstanceOfPrimeIdentity() {
        Location location = new LocationParser().parse(locText);
        Location otherLocation = new LocationParser().parse(locText);

        assertNotSame(location, otherLocation);
    }

    @Test
    @DisplayName("Test for distance from other location")
    void testDistanceFrom() {
        Location loc = new Location("Budapest", 47.497912, 19.040235);
        Location otherLoc = new Location("Greenwich", 51.477928, 0.0);

        assertEquals(1439944.635, loc.distanceFrom(otherLoc), 0.05);
    }

    @Test
    void testCreateLocationWithInvalidLatOrLon() {
        assertThrows(IllegalArgumentException.class, () -> new Location("Budapest", -91, 19.040235));
        assertThrows(IllegalArgumentException.class, () -> new Location("Budapest", 90.5, 19.040235));
        assertThrows(IllegalArgumentException.class, () -> new Location("Budapest", 47.497912, -180.9));
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> new Location("Budapest", 47.497912, 180.9));

        assertEquals("Invalid latitude and/or longitude. Lat: 47.497912, lon: 180.9", ex.getMessage());
    }
}
