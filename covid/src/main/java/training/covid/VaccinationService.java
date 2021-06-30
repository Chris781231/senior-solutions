package training.covid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VaccinationService {

    private AtomicLong atomicLong = new AtomicLong();

    private ModelMapper modelMapper;

    private List<Vaccination> vaccinations = new ArrayList<>(List.of(
            new Vaccination(atomicLong.incrementAndGet(), "123456788", "Budapest", LocalDate.now(), Type.PFIZER, Status.RESERVED)
    ));

    public VaccinationService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<VaccinationDto> findVaccinationBySid(Optional<String> sid) {
        java.lang.reflect.Type type = new TypeToken<List<VaccinationDto>>(){}.getType();
        return modelMapper.map(vaccinations.stream()
                .filter(v -> sid.isEmpty() || v.getSid().equals(sid.get()))
                .toList(), type);
    }

    public VaccinationDto registration(CreateVaccinationCommand command) {
        Vaccination vaccination = new Vaccination(
                atomicLong.incrementAndGet(), command.getSid(), command.getCity(), command.getDate(), command.getType(), command.getStatus()
        );
        vaccinations.add(vaccination);
        return modelMapper.map(vaccination, VaccinationDto.class);
    }

    public void deleteAll() {
        atomicLong = new AtomicLong();
        vaccinations.clear();
    }
}
