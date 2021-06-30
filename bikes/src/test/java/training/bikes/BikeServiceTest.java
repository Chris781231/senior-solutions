package training.bikes;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BikeServiceTest {

    private BikeService service = new BikeService(new ModelMapper());

    @Test
    void getBikes() {
        List<BikeDto> bikes = service.getBikes();
        assertThat(bikes)
                .hasSize(5)
                .extracting(BikeDto::getKmOnLastDrive)
                .containsExactly(0.8, 1.2, 0.7, 1.9, 2.9);
    }

    @Test
    void getUserIds() {
        List<String> users = service.getUserIds();

        assertThat(users)
                .hasSize(0);

        service.readFile();
        users = service.getUserIds();

        assertThat(users)
                .hasSize(5)
                .contains("US3434", "US3a34", "US3334", "US336", "US346");
    }
}
