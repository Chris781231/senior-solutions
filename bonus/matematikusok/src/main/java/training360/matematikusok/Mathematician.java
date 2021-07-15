package training360.matematikusok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mathematician {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    private List<String> favoriteThemes;

    @IsValidBirthDay
    private LocalDate dateOfBirth;

    @IsPrime
    @Max(100)
    private int favoritePrime;
}
