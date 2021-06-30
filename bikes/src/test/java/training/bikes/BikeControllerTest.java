package training.bikes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeControllerTest {

    @Mock
    BikeService service;

    @InjectMocks
    BikeController controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBikes() {
        when(service.getBikes()).thenReturn(List.of(new BikeDto("FH675", "US3434","2021-06-24 17:12:50",0.8)));

        assertEquals("FH675", controller.getBikes().get(0).getId());

        verify(service).getBikes();
    }

    @Test
    void getUserIds() {
        when(service.getUserIds()).thenReturn(List.of("US3434","US3a34"));

        assertThat(controller.getUserIds())
                .hasSize(2)
                .contains("US3434");

        verify(service).getUserIds();
    }
}