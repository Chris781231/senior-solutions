package meetengroomsjpa;

import java.util.List;

public interface MeetingRoomRepository {

    MeetingRoom save(String name, int width, int length);

    List<String> getMeetingRoomsOrderedByName();

    List<String> getEverySecondMeetingRoom();

    List<MeetingRoom> getMeetingRooms();

    MeetingRoom getExactMeetingRoomByName(String name);

    List<MeetingRoom> getMeetingRoomsByPrefix(String prefixOfName);

    void deleteAll();
}
