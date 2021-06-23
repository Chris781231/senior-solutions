package meetingrooms;

import java.time.LocalDateTime;

public class Meeting {

    private Long id;
    private String name;
    private LocalDateTime begin;
    private LocalDateTime end;

    public Meeting(String name, LocalDateTime begin, LocalDateTime end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public Meeting(Long id, String name, LocalDateTime begin, LocalDateTime end) {
        this.id = id;
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
