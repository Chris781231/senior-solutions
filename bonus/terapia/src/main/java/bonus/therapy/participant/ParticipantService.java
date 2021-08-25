package bonus.therapy.participant;

import bonus.therapy.EntityNotFoundException;
import bonus.therapy.ParticipantNotRegisteredException;
import bonus.therapy.therapysession.TherapySession;
import bonus.therapy.therapysession.TherapySessionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@AllArgsConstructor
@Service
public class ParticipantService {

    private ModelMapper modelMapper;

    private ParticipantRepository participantRepository;

    private TherapySessionRepository therapySessionRepository;

    public ParticipantDto save(CreateParticipantCommand command) {
        Participant participant = new Participant(command.getName());
        participantRepository.save(participant);
        return modelMapper.map(participant, ParticipantDto.class);
    }

    public List<ParticipantDto> listParticipants() {
        List<Participant> participants = participantRepository.findAll();
        Type targetType = new TypeToken<List<ParticipantDto>>() {
        }.getType();
        return modelMapper.map(participants, targetType);
    }

    public ParticipantDto findParticipantById(long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "Participant"));
        return modelMapper.map(participant, ParticipantDto.class);
    }

    @Transactional
    public ParticipantDto addTherapySession(long participantId, AddTherapySessionCommand command) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new EntityNotFoundException(participantId, "Participant"));
        TherapySession therapySession = therapySessionRepository.findById(command.getTherapySessionId())
                .orElseThrow(() -> new EntityNotFoundException(command.getTherapySessionId(), "TherapySession"));
        participant.addTherapySession(therapySession);
        return modelMapper.map(participant, ParticipantDto.class);
    }

    @Transactional
    public void deleteTherapySession(long participantId, long therapySessionId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new EntityNotFoundException(participantId, "Participant"));
        TherapySession therapySession =  therapySessionRepository.findById(therapySessionId)
                .orElseThrow(() -> new EntityNotFoundException(therapySessionId, "TherapySession"));
        if (!participant.getTherapySessions().contains(therapySession)) {
            throw new ParticipantNotRegisteredException(participantId, therapySessionId);
        }
        participant.removeTherapySession(therapySession);
    }
}
