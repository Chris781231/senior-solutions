package training.cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarService service;

    @InjectMocks
    private CarController controller;

    @Test
    void getCars() {
        when(service.getCars()).thenReturn(List.of(
                new CarDto("Opel", "Astra", 5, Status.EXCELLENT, List.of(
                        new KmState(LocalDate.of(2019, 3, 1), 20000),
                        new KmState(LocalDate.of(2020,  3, 1), 30000)))));

        assertThat(controller.getCars())
                .hasSize(1)
                .extracting(CarDto::getType)
                .contains("Astra");
    }

    @Test
    void getCarsByMark() {
        when(service.getCarsByMark("Volvo")).thenReturn(List.of(
                new CarDto("Volvo", "S40", 10, Status.GOOD, List.of(
                        new KmState(LocalDate.of(2019, 3, 1), 20000),
                        new KmState(LocalDate.of(2020,  3, 1), 30000)))));

        assertThat(controller.getCarsByMark("Volvo"))
                .hasSize(1)
                .extracting(CarDto::getType)
                .contains("S40");

        verify(service).getCarsByMark("Volvo");
    }
}