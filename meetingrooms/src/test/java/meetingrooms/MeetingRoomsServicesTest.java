package meetingrooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MeetingRoomsServicesTest {

//    private MeetingRoomsServices services = new MeetingRoomsServices(new InMemoryMeetingRoomsRepository());
    private MeetingRoomsServices services = new MeetingRoomsServices(new MariaDbMeetingRoomsRepository());

    @BeforeEach
    void init() { services.deleteAll(); }

    @Test
    void testSaveThenList() {
        services.save("Red", 3, 4);
        services.save("Blue", 4, 4);

        List<String> result = services.listAllByName();

        assertEquals(2, result.size());
        assertEquals("[Blue, Red]", result.toString());
    }

    @Test
    void testSaveThenListReversed() {
        services.save("Red", 3, 4);
        services.save("Blue", 4, 4);

        List<String> result = services.listAllByNameReversed();

        assertEquals(2, result.size());
        assertEquals("[Red, Blue]", result.toString());
    }

    @Test
    void testListEverySecond() {
        services.save("Red", 3, 4);
        services.save("Blue", 4, 4);
        services.save("Green", 3, 4);
        services.save("Yellow", 4, 4);
        services.save("Brown", 3, 4);
        services.save("Magenta", 4, 4);

        List<String> result = services.listEverySecond();
        assertEquals(3, result.size());
        assertEquals("[Brown, Magenta, Yellow]", result.toString());
    }

    @Test
    void testListAreas() {
        services.save("Red", 3, 4);
        services.save("Blue", 4, 4);
        services.save("Green", 3, 4);

        List<MeetingRoom> areas = services.listAreas();

        assertEquals("name: 'Blue', width: 4.0, length: 4.0, area: 16.0", areas.get(0).toString());
    }

    @Test
    void testFindByName() {
        services.save("Red", 3, 4);
        services.save("Blue", 4, 4);
        services.save("Green", 3, 4);

        MeetingRoom result = services.findByName("Blue");

        assertEquals("name: 'Blue', width: 4.0, length: 4.0, area: 16.0", result.toString());
        assertNull(services.findByName("Brown"));
    }

    @Test
    void testFindByNamePart() {
        services.save("Red", 3, 4);
        services.save("Blue", 4, 4);
        services.save("Brown", 3, 3);
        services.save("Green", 3, 4);

        List<MeetingRoom> result = services.findByNamePart("B");

        assertEquals(2, result.size());
        assertEquals("name: 'Blue', width: 4.0, length: 4.0, area: 16.0", result.get(0).toString());
    }

    @Test
    void testFindByAreaLargerThan() {
        services.save("Red", 3, 4);
        services.save("Blue", 4, 4);
        services.save("Brown", 3, 3);

        List<MeetingRoom> result = services.findByAreaLargerThan(13);

        assertEquals(1, result.size());
        assertEquals("name: 'Blue', width: 4.0, length: 4.0, area: 16.0", result.get(0).toString());
    }
}
