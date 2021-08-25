package bonus.therapy.participant;

import bonus.therapy.therapysession.TherapySession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "participants")
    private List<TherapySession> therapySessions = new ArrayList<>();

    public Participant(String name) {
        this.name = name;
    }

    public void addTherapySession(TherapySession therapySession) {
        therapySessions.add(therapySession);
        therapySession.getParticipants().add(this);
    }

    public void removeTherapySession(TherapySession therapySession) {
        therapySessions.remove(therapySession);
        therapySession.getParticipants().remove(this);
    }
}
