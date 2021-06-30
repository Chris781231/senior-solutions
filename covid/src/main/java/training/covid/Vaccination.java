package training.covid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccination {

    private Long id;
    private String sid;
    private String city;
    private LocalDate date;
    private Type type;
    private Status status;

    public Vaccination(String sid, String city, LocalDate date, Type type, Status status) {
        this.sid = sid;
        this.city = city;
        this.date = date;
        this.type = type;
        this.status = status;
    }
}
