package bonus.therapy.participant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.HttpConstraint;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ParticipantController {

    private ParticipantService participantService;

    @GetMapping("/participants")
    public List<ParticipantDto> listParticipants() {
        return participantService.listParticipants();
    }

    @GetMapping("/participants/{id}")
    public ParticipantDto findParticipantById(@PathVariable long id) {
        return participantService.findParticipantById(id);
    }

    @PostMapping("/participants")
    public ParticipantDto save(@RequestBody @Valid CreateParticipantCommand command) {
        return participantService.save(command);
    }

    @PostMapping("/participants/{id}/therapysessions")
    public ParticipantDto addTherapySession(@PathVariable ("id") long participantId, @RequestBody @Valid AddTherapySessionCommand command) {
        return participantService.addTherapySession(participantId, command);
    }

    @DeleteMapping("/participants/{participant_id}/therapysessions/{therapysession_id}")
    public void deleteTherapySession(@PathVariable ("participant_id") long participantId, @PathVariable ("therapysession_id") long therapySessionId) {
        participantService.deleteTherapySession(participantId, therapySessionId);
    }
}
