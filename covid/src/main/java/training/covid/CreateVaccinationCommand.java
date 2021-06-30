package training.covid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVaccinationCommand {

    private String sid;
    private String city;
    private LocalDate date;
    private Type type;
    private Status status;
}
