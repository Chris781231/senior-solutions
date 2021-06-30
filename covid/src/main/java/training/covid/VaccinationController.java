package training.covid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vaccination")
public class VaccinationController {

    private VaccinationService service;

    public VaccinationController(VaccinationService service) {
        this.service = service;
    }

    @GetMapping
    public List<VaccinationDto> findVaccinationsBySid(@RequestParam Optional<String> sid) {
        return service.findVaccinationBySid(sid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VaccinationDto registration(@RequestBody CreateVaccinationCommand command) {
        return service.registration(command);
    }
}
