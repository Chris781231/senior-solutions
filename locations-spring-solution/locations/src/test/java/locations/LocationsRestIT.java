package locations;

import locations.command.CreateLocationCommand;
import locations.dto.LocationDto;
import locations.service.LocationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationsRestIT {

    @Autowired
    TestRestTemplate template;

//    @Autowired
//    LocationsService service;

    @BeforeEach
    void init() {
        template.delete("/locations");
    }

    @Test
    void getLocationsTest() {
        template.postForObject("/locations", new CreateLocationCommand("Budapest", 47.12, 19.17), LocationDto.class);
        template.postForObject("/locations", new CreateLocationCommand("Nézsa", 47.18, 19.12), LocationDto.class);

        List<LocationDto> result = template.exchange("/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest", "Nézsa");
    }

    @Test
    void getLocationById() {
        LocationDto budapest = template.postForObject("/locations", new CreateLocationCommand("Budapest", 47.12, 19.17), LocationDto.class);
        LocationDto nezsa = template.postForObject("/locations", new CreateLocationCommand("Nézsa", 47.18, 19.12), LocationDto.class);

        LocationDto result = template.exchange("/locations/" + budapest.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<LocationDto>() {
                })
                .getBody();

        assertEquals("Budapest", Objects.requireNonNull(result).getName());
    }
}
