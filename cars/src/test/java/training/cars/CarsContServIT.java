package training.cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CarsContServIT {

    @Autowired
    private CarController controller;

    @Test
    void getCarsTest() {
        List<CarDto> cars = controller.getCars();

        assertThat(cars)
               .hasSize(3);
    }
}
