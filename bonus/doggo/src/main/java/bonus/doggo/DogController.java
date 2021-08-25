package bonus.doggo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dogs")
@AllArgsConstructor
public class DogController {

    private DogService service;

    @PostMapping
    public DogDto save(@RequestBody CreateDogCommand command) {
        return service.save(command);
    }

    @GetMapping()
    public List<DogDto> listDogs(@RequestParam Optional<String> breed) {
        return service.listDogs(breed);
    }

    @GetMapping("/{id}")
    public DogDto findById(@PathVariable long id) {
        return service.findById(id);
    }
}
