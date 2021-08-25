package bonus.doggo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from dogs"})
public class DogControllerRestIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    void testSaveDog() {
        CreateDogCommand command = new CreateDogCommand("Buksi", "keverék", 2, "rongybaba");
        System.out.println(command);
        DogDto dog = template.postForObject("/api/dogs",
                command,
                DogDto.class);

        assertEquals("Buksi", dog.getName());
        assertEquals("keverék", dog.getBreed());
        assertEquals(2, dog.getAge());
        assertEquals("rongybaba", dog.getFavGame());
    }

    @Test
    void testListDogs() {
        template.postForObject("/api/dogs",
                new CreateDogCommand("Buksi", "keverék", 2, "rongybaba"),
                DogDto.class);
        template.postForObject("/api/dogs",
                new CreateDogCommand("Morzsi", "Foxi", 5, "fakocka"),
                DogDto.class);

        List<DogDto> dogs = template.exchange("/api/dogs",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DogDto>>() {
                }).getBody();

        assertThat(dogs)
                .hasSize(2)
                .extracting(DogDto::getName)
                .containsExactly("Buksi", "Morzsi");
    }

    @Test
    void testFindDogById() {
        long id = template.postForObject("/api/dogs",
                new CreateDogCommand("Buksi", "keverék", 2, "rongybaba"),
                DogDto.class).getId();

        DogDto dog = template.exchange("/api/dogs/" + id,
                HttpMethod.GET,
                null,
                DogDto.class).getBody();

        assertEquals("Buksi", dog.getName());
        assertEquals("keverék", dog.getBreed());
        assertEquals(2, dog.getAge());
        assertEquals("rongybaba", dog.getFavGame());
    }

    @Test
    void testFindDogsByBreed() {
        template.postForObject("/api/dogs",
                new CreateDogCommand("Buksi", "keverék", 2, "rongybaba"),
                DogDto.class);
        template.postForObject("/api/dogs",
                new CreateDogCommand("Morzsi", "keverék", 5, "fakocka"),
                DogDto.class);
        template.postForObject("/api/dogs",
                new CreateDogCommand("Cuki", "Palotapincsi", 3, "szalag"),
                DogDto.class);

        List<DogDto> dogs = template.exchange("/api/dogs?breed=keverék",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DogDto>>() {
                }).getBody();

        assertThat(dogs)
                .hasSize(2)
                .extracting(DogDto::getName)
                .containsExactly("Buksi", "Morzsi");
    }
}
