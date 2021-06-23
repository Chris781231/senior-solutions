package training.cars;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerMockTest {

    @Mock
    CarService service;

    @InjectMocks
    CarController controller;

    @Test
    void getCars() {
        when(service.getCars()).thenReturn(
                Collections.synchronizedList(List.of(
                        new CarDto("Volkswagen", "Golf Plus 1.6 Benzin", 15, Status.GOOD, List.of(
                                new KmState(LocalDate.of(2012, 8, 6), 52_314),
                                new KmState(LocalDate.of(2014, 8, 12), 134_985))),
                        new CarDto("Volvo", "S40 1.9 TDI", 12, Status.GOOD, List.of(
                                new KmState(LocalDate.of(2013, 5, 16), 38_268),
                                new KmState(LocalDate.of(2014, 8, 12), 125_798))))));

        assertThat(controller.getCars())
                .hasSize(2)
                .extracting(CarDto::getMark)
                .contains("Volkswagen", "Volvo");

        verify(service).getCars();
    }

    @Test
    void getMarks() {
        when(service.getMarks()).thenReturn(List.of("Volkswagen", "Volvo", "Skoda"));

        assertThat(controller.getMarks())
                .hasSize(3)
                .contains("Volkswagen", "Volvo", "Skoda");

        verify(service).getMarks();
    }
}