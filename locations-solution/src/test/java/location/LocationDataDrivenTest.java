package location;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationDataDrivenTest {

    private final LocationParser parser = new LocationParser();

    private final String[][] values = {
            {"Budapest,47.497912,19.040235", "false"},
            {"Equador,0.0,-78.5706226", "true"},
            {"Greenwich,51.477928,0.0", "false"}
    };

    @RepeatedTest(value = 3, name = "Get location is on Equator {currentRepetition} / {totalRepetition}")
    void testIsOnEquator(RepetitionInfo info) {
        Location location = parser.parse(values[info.getCurrentRepetition() - 1][0]);
        String result = location.getLat() < 0.0005 && location.getLat() >= 0 ? "true" : "false";

        assertEquals(values[info.getCurrentRepetition() - 1][1],
                result);
    }
}
