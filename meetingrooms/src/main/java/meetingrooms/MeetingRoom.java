package meetingrooms;

import java.util.ArrayList;
import java.util.List;

public class MeetingRoom implements Comparable<MeetingRoom> {

    private long id;
    private final String name;
    private final double width;
    private final double length;

    private List<Meeting> meetings = new ArrayList<>();

    public MeetingRoom(String name, double width, double length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public MeetingRoom(long id, String name, double width, double length) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public List<Meeting> getMeetings() {
        return new ArrayList<>(meetings);
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public double getArea() {
        return width * length;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    @Override
    public int compareTo(MeetingRoom m) {
        return this.getName().compareTo(m.getName());
    }

    @Override
    public String toString() {
        return "name: '" + name + '\'' +
                ", width: " + width +
                ", length: " + length +
                ", area: " + getArea();
    }
}
