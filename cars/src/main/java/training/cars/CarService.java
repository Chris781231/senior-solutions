package training.cars;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class CarService {

    private ModelMapper modelMapper;

    public CarService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private List<Car> cars = Collections.synchronizedList(List.of(
            new Car("Opel", "Vectra", 4, Status.EXCELLENT, List.of(
                    new KmState(LocalDate.of(2018, 2, 1), 15000),
                    new KmState(LocalDate.of(2019, 2, 1), 30000),
                    new KmState(LocalDate.of(2020, 2, 1), 45000))),
            new Car("Opel", "Astra", 20, Status.BAD, List.of(
                    new KmState(LocalDate.of(2010, 2, 1), 50000),
                    new KmState(LocalDate.of(2015, 2, 1), 150000),
                    new KmState(LocalDate.of(2020, 2, 1), 300000))),
            new Car("Volkswagen", "Golf", 13, Status.GOOD, List.of(
                    new KmState(LocalDate.of(2012, 2, 5), 30000),
                    new KmState(LocalDate.of(2016, 2, 5), 120000),
                    new KmState(LocalDate.of(2020, 2, 5), 180000)))
    ));

    public List<CarDto> getCars() {
        return cars.stream()
                .map(entity -> modelMapper.map(entity, CarDto.class))
                .toList();
    }

    public List<CarDto> getCarsByMark(String mark) {
        return cars.stream()
                .filter(c -> c.getMark().equalsIgnoreCase(mark))
                .map(entity -> modelMapper.map(entity, CarDto.class))
                .toList();
    }
}
