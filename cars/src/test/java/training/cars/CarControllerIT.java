package training.cars;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
public class CarControllerIT {

    @Autowired
    private CarController controller;

    @Test
    void getCars() {
        List<CarDto> cars = controller.getCars();

        assertThat(cars)
                .hasSize(2)
                .extracting(CarDto::getMark, CarDto::getStatus)
                .contains(tuple("Volkswagen", Status.GOOD))
                .contains(tuple("Volvo", Status.GOOD));
    }
}
