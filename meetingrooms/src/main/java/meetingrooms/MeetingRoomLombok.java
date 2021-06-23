package meetingrooms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class MeetingRoomLombok implements Comparable<MeetingRoomLombok> {

    private @Getter long id;
    private @Getter final String name;
    private @Getter final double width;
    private @Getter final double length;

    private @Getter List<Meeting> meetings = new ArrayList<>();

    public MeetingRoomLombok(String name, double width, double length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public double getArea() {
        return width * length;
    }

    @Override
    public int compareTo(MeetingRoomLombok m) {
        return this.getName().compareTo(m.getName());
    }
}
