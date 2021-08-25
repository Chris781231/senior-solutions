package bonus.therapy.participant;

import bonus.therapy.therapysession.TherapySessionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {

    private Long id;

    private String name;
}
