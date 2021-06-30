package training.bikes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
public class BikeControllerIT {

    @Autowired
    BikeController controller;

    @Test
    void getBikes() {
        List<BikeDto> bikes = controller.getBikes();

        assertThat(bikes)
                .hasSize(5)
                .extracting(BikeDto::getId, BikeDto::getLastUserId)
                .contains(tuple("FH675", "US3434"), tuple("FH676", "US3a34"));
    }

    @Test
    void getUserIds() {
        List<String> userIds = controller.getUserIds();

        assertThat(userIds)
                .hasSize(0);

        controller.getBikes();
        userIds = controller.getUserIds();

        assertThat(userIds)
                .hasSize(5)
                .contains("US3a34", "US3434");
    }
}
