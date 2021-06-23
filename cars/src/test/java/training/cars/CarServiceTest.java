package training.cars;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarService service = new CarService(new ModelMapper());

    @Test
    void getCars() {
        assertEquals(3, service.getCars().size());

        assertThat(service.getCars())
                .extracting(CarDto::getMark, CarDto::getAge)
                .contains(tuple("Opel", 4), tuple("Opel", 20), tuple("Volkswagen", 13));
    }

    @Test
    void getCarsByMark() {
        assertEquals(2, service.getCarsByMark("Opel").size());

        List<CarDto> vws = service.getCarsByMark("Volkswagen");

        assertEquals(Status.GOOD, vws.get(0).getStatus());
    }
}