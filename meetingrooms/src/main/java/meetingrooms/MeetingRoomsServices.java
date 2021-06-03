package meetingrooms;

import java.util.List;

public class MeetingRoomsServices {

    private final MeetingRoomsRepository repository;

    public MeetingRoomsServices(MeetingRoomsRepository repository) {
        this.repository = repository;
    }

    public void save(String name, double width, double length) {
        repository.saveMeetingRooms(name, width, length);
    }

    public List<String> listAllByName() {
        return repository.listMeetingRoomsByName();
    }

    public List<String> listAllByNameReversed() {
        return repository.listMeetingRoomsByNameReversed();
    }

    public List<String> listEverySecond() {
        return repository.listMeetingRoomsByNameEverySecond();
    }

    public List<MeetingRoom> listAreas() {
        return repository.listAreas();
    }

    public MeetingRoom findByName(String name) {
        return repository.findByName(name);
    }

    public List<MeetingRoom> findByNamePart(String namePart) {
        return repository.findByNamePart(namePart);
    }

    public List<MeetingRoom> findByAreaLargerThan(int area) {
        return repository.findByAreaLargerThan(area);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
