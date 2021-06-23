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

    @GetMapping
    public List<CarDto> getCars() {
        return carService.getCars();
    }

    @GetMapping("/carsbyfilter")
    public List<CarDto> getCarsByMark(String mark) {
        return carService.getCarsByMark(mark);
    }
}
