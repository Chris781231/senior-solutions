package training360.matematikusok;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mathematicians")
public class MathematicianController {

    private MathematicianService service;

    public MathematicianController(MathematicianService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Mathematician> getMathematicians() {
        return service.getMathematicians();
    }

    @PostMapping
    public Mathematician saveMathematician(@RequestBody @Valid Mathematician mathematician) {
        return service.saveMathematician(mathematician);
    }

    @DeleteMapping
    void deleteAll() {
        service.deleteAll();
    }
}
