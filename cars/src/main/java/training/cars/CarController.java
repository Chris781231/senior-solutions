package training.cars;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<CarDto> getCars() {
        return carService.getCars();
    }

    @GetMapping("/types")
    public List<String> getMarks() {
        return carService.getMarks();
    }
}
