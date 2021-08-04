package meetengroomsjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeetingRoomDaoTest {

    private MeetingRoomDao dao;

    @BeforeEach
    void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        dao = new MeetingRoomDao(factory);
    }

    @Test
    void testSaveThanList() {
        dao.save("piros", 3, 3);
        dao.save("blue", 4, 5);

        List<String> expected = dao.getMeetingRoomsOrderedByName();

        assertThat(expected)
                .hasSize(2)
                .containsExactly("blue", "piros");
    }

    @Test
    void testGetEverySecondMeetingRoom() {
        dao.save("piros", 3, 3);
        dao.save("kék", 4,  5);

        List<String> expected = dao.getEverySecondMeetingRoom();

        assertEquals("piros", expected.get(0));
    }

    @Test
    void testGetExactMeetingRoomByName() {
        dao.save("piros", 3, 3);
        dao.save("kék", 4,  5);

        MeetingRoom expected = dao.getExactMeetingRoomByName("kék");

        assertEquals(4, expected.getWidth());
        assertEquals(5, expected.getLength());
    }

    @Test
    void testGetMeetingRoomsByPrefix() {
        dao.save("piros", 3, 3);
        dao.save("kék", 4,  5);

        List<MeetingRoom> expected = dao.getMeetingRoomsByPrefix("kék");

        assertThat(expected)
                .hasSize(1)
                .extracting(MeetingRoom::getWidth, MeetingRoom::getLength)
                .contains(tuple(4, 5));
    }

    @Test
    void testDeleteAll() {
        dao.save("piros", 3, 3);
        dao.save("kék", 4,  5);

        dao.deleteAll();

        List<MeetingRoom> expected = dao.getMeetingRooms();

        assertEquals(0, expected.size());
    }
}
