package bonus.therapy.therapysession;

import bonus.therapy.participant.Participant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "therapy_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TherapySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String therapist;

    private String location;

    private LocalDateTime time;

    @ManyToMany
    private List<Participant> participants = new ArrayList<>();

    public TherapySession(String therapist, String location, LocalDateTime time) {
        this.therapist = therapist;
        this.location = location;
        this.time = time;
    }
}
