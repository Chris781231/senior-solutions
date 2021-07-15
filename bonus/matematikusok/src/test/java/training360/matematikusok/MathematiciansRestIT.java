package training360.matematikusok;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathematiciansRestIT {

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    void init() {
        template.delete("/api/mathematicians");
    }

    @Test
    void createMathematiciansWithValidValuesTest() {
        template.postForObject("/api/mathematicians",
                new Mathematician("John Doe", new ArrayList<>(List.of("Geometry")), LocalDate.now().minusYears(20), 3),
                Mathematician.class);

        List<Mathematician> result = template.exchange("/api/mathematicians",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Mathematician>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(1)
                .extracting(Mathematician::getName, Mathematician::getFavoritePrime)
                .containsExactly(tuple("John Doe", 3));
    }

    @Test
    void createMathematicianFailTest() {
        template.postForObject("/api/mathematicians",
                new Mathematician(null, new ArrayList<>(List.of("Geometry")), LocalDate.now().minusYears(20), 3),
                Mathematician.class);

        template.postForObject("/api/mathematicians",
                new Mathematician("", new ArrayList<>(List.of("Geometry")), LocalDate.now().minusYears(20), 3),
                Mathematician.class);

        template.postForObject("/api/mathematicians",
                new Mathematician("John Doe", null, LocalDate.now().minusYears(20), 3),
                Mathematician.class);

        template.postForObject("/api/mathematicians",
                new Mathematician("John Doe", Collections.emptyList(), LocalDate.now().minusYears(20), 3),
                Mathematician.class);

        template.postForObject("/api/mathematicians",
                new Mathematician("John Doe", new ArrayList<>(List.of("Geometry")), LocalDate.now().plusYears(1), 3),
                Mathematician.class);

        template.postForObject("/api/mathematicians",
                new Mathematician("John Doe", new ArrayList<>(List.of("Geometry")), LocalDate.now().minusYears(20), 4),
                Mathematician.class);

        template.postForObject("/api/mathematicians",
                new Mathematician("John Doe", new ArrayList<>(List.of("Geometry")), LocalDate.now().minusYears(20), 101),
                Mathematician.class);

        List<Mathematician> result = template.exchange("/api/mathematicians",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Mathematician>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(0);
    }
}
