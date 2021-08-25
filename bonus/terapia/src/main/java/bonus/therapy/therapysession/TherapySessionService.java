package bonus.therapy.therapysession;

import bonus.therapy.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@AllArgsConstructor
@Service
public class TherapySessionService {

    private ModelMapper modelMapper;

    private TherapySessionRepository therapySessionRepository;

    public TherapySessionDto save(CreateTherapySessionCommand command) {
        TherapySession therapySession = new TherapySession(command.getTherapist(), command.getLocation(), command.getTime());
        therapySessionRepository.save(therapySession);
        return modelMapper.map(therapySession, TherapySessionDto.class);
    }

    public List<TherapySessionDto> listTherapySessions() {
        List<TherapySession> therapySessions = therapySessionRepository.findAll();
        Type targetType = new TypeToken<List<TherapySessionDto>>() {
        }.getType();
        return modelMapper.map(therapySessions, targetType);
    }

    public TherapySessionDto findTherapySessionById(long id) {
        TherapySession therapySession = therapySessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "TherapySession"));
        return modelMapper.map(therapySession, TherapySessionDto.class);
    }
}
