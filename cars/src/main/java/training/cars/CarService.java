package training.cars;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarService {

    private ModelMapper modelMapper;

    private List<Car> cars = Collections.synchronizedList(new ArrayList<>(List.of(
            new Car("Volkswagen", "Golf Plus 1.6 Benzin", 15, Status.GOOD, List.of(
                    new KmState(LocalDate.of(2012, 8, 6), 52_314),
                    new KmState(LocalDate.of(2014, 8, 12), 134_985))),
            new Car("Volvo", "S40 1.9 TDI", 12, Status.GOOD, List.of(
                    new KmState(LocalDate.of(2013, 5, 16), 38_268),
                    new KmState(LocalDate.of(2014, 8, 12), 125_798))))));

    public CarService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CarDto> getCars() {
        return cars.stream()
                .map(entity -> modelMapper.map(entity, CarDto.class))
                .toList();
    }

    public List<String> getMarks() {
        return cars.stream()
                .map(Car::getMark)
                .distinct()
                .toList();
    }
}
