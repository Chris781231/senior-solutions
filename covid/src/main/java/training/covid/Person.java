package training.covid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;
    private String name;
    private String sid;
    private LocalDate birthOfDate;
    private String city;
    private String address;

    public Person(String name, String sid, LocalDate birthOfDate, String city, String address) {
        this.name = name;
        this.sid = sid;
        this.birthOfDate = birthOfDate;
        this.city = city;
        this.address = address;
    }
}
