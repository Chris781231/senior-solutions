package location;

import org.junit.jupiter.api.BeforeEach;
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
    void testParse() {
        Location location = parser.parse(locText);

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLat());
        assertEquals(19.040235, location.getLon());
    }

    @Test
    void testIsOnEquator() {
        Location loc1 = parser.parse(locText);
        Location loc2 = parser.parse(locIsOnEquatorText);

        assertFalse(parser.isOnEquator(loc1));
        assertTrue(parser.isOnEquator(loc2));
    }

    @Test
    void testIsOnPrimeMeridian() {
        Location loc1 = parser.parse(locText);
        Location loc2 = parser.parse(locIsOnPrimeMeridianText);

        assertFalse(parser.isOnPrimeMeridian(loc1));
        assertTrue(parser.isOnPrimeMeridian(loc2));
    }
}
