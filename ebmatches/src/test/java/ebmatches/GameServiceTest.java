package ebmatches;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    GameRepository repository;

    @InjectMocks
    GameService service;

    @Test
    void testGetLargestGoalDiffMatch() {
        when(repository.getLargestGoalDiffMatch())
                .thenReturn(Optional.of(new Game("Hungary", "Portugal", 4, 2)));
        assertThat(service.getLargestGoalDiffMatch().get().getWinner()).isEqualTo("Hungary");
    }

    @Test
    void testGetAllKickedGoalByCountry() {
        when(repository.getAllKickedGoalByCountry1("Hungary"))
                .thenReturn(9);
        assertEquals(9, service.getAllKickedGoalByCountry("Hungary"));
    }

    @Test
    void getMostGoalKickCountryTest() {
        when(repository.getMostGoalKickCountry())
                .thenReturn("Hungary");
        assertThat(service.getMostGoalKickCountry()).isEqualTo("Hungary");
    }

}