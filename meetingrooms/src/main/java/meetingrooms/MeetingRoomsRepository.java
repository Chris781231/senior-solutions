package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void saveMeetingRooms(String name, double width, double length);

    List<String> listMeetingRoomsByName();

    List<String> listMeetingRoomsByNameReversed();

    List<String> listMeetingRoomsByNameEverySecond();

    List<MeetingRoom> listAreas();

    MeetingRoom findByName(String name);

    List<MeetingRoom> findByNamePart(String namePart);

    List<MeetingRoom> findByAreaLargerThan(int area);

    void deleteAll();
}
