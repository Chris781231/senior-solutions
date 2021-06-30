package training.bikes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    private BikeService service;

    public BikeController(BikeService service) {
        this.service = service;
    }

    @GetMapping("/history")
    public List<BikeDto> getBikes() {
        return service.getBikes();
    }

    @GetMapping("/users")
    public List<String> getUserIds() {
        return service.getUserIds();
    }
}
