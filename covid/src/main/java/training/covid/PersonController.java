package training.covid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/covid")
public class PersonController {

    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<PersonDto> getPersonList() {
        return service.getPersonList();
    }

    @GetMapping("/{sid}")
    public PersonDto findBySid(@PathVariable String sid) {
        return service.findBySid(sid);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto registration(@RequestBody CreatePersonCommand command) {
        return service.registration(command);
    }
}
