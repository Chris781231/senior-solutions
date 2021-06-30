package training.covid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreatePersonCommand {

    private String name;
    private String sid;
    private LocalDate birthOfDate;
    private String city;
    private String address;
}
