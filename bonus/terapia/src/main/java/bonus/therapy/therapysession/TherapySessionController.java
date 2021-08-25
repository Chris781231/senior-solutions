package bonus.therapy.therapysession;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/therapysessions")
public class TherapySessionController {

    private TherapySessionService therapySessionService;

    @GetMapping
    public List<TherapySessionDto> listTherapySessions() {
        return therapySessionService.listTherapySessions();
    }

    @GetMapping("/{id}")
    public TherapySessionDto findTherapySessionById(@PathVariable long id) {
        return therapySessionService.findTherapySessionById(id);
    }

    @PostMapping
    public TherapySessionDto save(@RequestBody @Valid CreateTherapySessionCommand command) {
        return therapySessionService.save(command);
    }
}
