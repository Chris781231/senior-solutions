package training.covid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationDto {

    private Long id;
    private String sid;
    private String city;
    private LocalDate date;
    private Type type;
    private Status status;
}
