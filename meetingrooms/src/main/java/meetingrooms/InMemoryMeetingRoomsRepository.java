package meetingrooms;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository {

    public static final Collator HUN_COLLATOR = Collator.getInstance(new Locale("hu", "HU"));

    private final List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void saveMeetingRooms(String name, double width, double length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    @Override
    public List<String> listMeetingRoomsByName() {

        return meetingRooms.stream()
                .map(MeetingRoom::getName)
                .sorted(HUN_COLLATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listMeetingRoomsByNameReversed() {
        return meetingRooms.stream()
                .map(MeetingRoom::getName)
                .sorted(HUN_COLLATOR.reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listMeetingRoomsByNameEverySecond() {
        List<MeetingRoom> sorted = meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getName, HUN_COLLATOR))
                .collect(Collectors.toList());
        return IntStream.range(0, sorted.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(sorted::get)
                .map(MeetingRoom::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> listAreas() {
        return meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getArea).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public MeetingRoom findByName(String name) {
        return meetingRooms.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findAny().orElse(null);
    }

    @Override
    public List<MeetingRoom> findByNamePart(String namePart) {
        return meetingRooms.stream()
                .filter(m -> m.getName().toUpperCase().contains(namePart.toUpperCase()))
                .sorted(Comparator.comparing(MeetingRoom::getName, HUN_COLLATOR))
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> findByAreaLargerThan(int area) {
        return meetingRooms.stream()
                .filter(m -> m.getArea() > area)
                .sorted(Comparator.comparing(MeetingRoom::getName, HUN_COLLATOR))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        meetingRooms.clear();
    }
}
