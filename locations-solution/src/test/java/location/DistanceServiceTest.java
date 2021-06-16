package location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DistanceServiceTest {

    @Mock
    LocationRepository repository;

    @InjectMocks
    DistanceService service;

    @BeforeEach
    void init() {
        when(repository.findByName("Budapest")).thenReturn(
                Optional.of(new Location("Budapest", 47.497912, 19.040235)));
        when(repository.findByName("Debrecen")).thenReturn(
                Optional.of(new Location("Debrecen", 47.531605, 21.627312)));
    }

    @Test
    void testCalculateDistanceForCorrespondingNames() {
        service.calculateDistance("Budapest", "Debrecen");

        verify(repository).findByName("Budapest");
        verify(repository).findByName("Debrecen");
    }

    @Test
    void testCalculateDistanceExistingNames() {
        assertEquals(194319.417,
                service.calculateDistance("Budapest", "Debrecen").get(),
                0.005);

    }

    @Test
    void testCalculateDistanceFalseNames() {
        when(repository.findByName("NonExistsCity")).thenReturn(
                Optional.empty());

        assertEquals(Optional.empty(), service.calculateDistance("Budapest", "NonExistsCity"));
        assertEquals(Optional.empty(), service.calculateDistance("NonExistsCity", "Debrecen"));
        assertEquals(Optional.empty(), service.calculateDistance("NonExistsCity", "NonExistsCity"));
    }
}