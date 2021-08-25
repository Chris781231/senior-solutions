package bonus.therapy.therapysession;

import bonus.therapy.participant.ParticipantDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TherapySessionDto {

    private Long id;

    private String therapist;

    private String location;

    private LocalDateTime time;

    private List<ParticipantDto> participants;
}
