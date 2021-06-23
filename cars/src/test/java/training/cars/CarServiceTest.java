package training.cars;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarServiceTest {

    private ModelMapper modelMapper = new ModelMapper();

    private CarService service = new CarService(modelMapper);

    private List<Car> cars;

    @Test
    void getCars() {
        assertThat(service.getCars())
                .hasSize(2)
                .extracting(c -> c.getMark()).contains("Volvo", "Volkswagen");
    }

    @Test
    void getMarks() {
        assertThat(service.getMarks())
                .hasSize(2)
                .contains("Volkswagen", "Volvo");
    }
}