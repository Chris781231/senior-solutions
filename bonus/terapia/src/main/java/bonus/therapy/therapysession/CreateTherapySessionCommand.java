package bonus.therapy.therapysession;

import bonus.therapy.ValidDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTherapySessionCommand {

    @NotBlank
    private String therapist;

    @NotBlank
    private String location;

    @NotNull
    @ValidDateTime
    private LocalDateTime time;
}
