package training.covid;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerRestTemplateIT {

    private PersonDto personDto;
    private PersonDto personDto2;

    @Autowired
    TestRestTemplate template;

    @Autowired
    PersonService service;

    @BeforeEach
    void setUp() {
        service.deleteAll();

        personDto = template.postForObject("/api/covid", new CreatePersonCommand("Lucc Gizi", "123456789", LocalDate.now(), "Karakócsörcsög", "Fő utca 3"), PersonDto.class);
        personDto2 = template.postForObject("/api/covid", new CreatePersonCommand("Lucc Géza", "123456711", LocalDate.now(), "Karakócsörcsög", "Fő utca 3"), PersonDto.class);
    }

    @Test
    void registrationTest() {
        assertEquals("Lucc Gizi", personDto.getName());
    }

    @Test
    void findPersonBySidTest() {
        PersonDto personDto = template.exchange("/api/covid/123456789",
                HttpMethod.GET,
                null,
                PersonDto.class).getBody();

        assertThat(personDto)
                .extracting(PersonDto::getName)
                .isEqualTo("Lucc Gizi");
    }

    @Test
    void getPersonListTest() {
        List<PersonDto> personDtoList = template.exchange("/api/covid",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PersonDto>>() {
                }).getBody();

        assertThat(personDtoList)
                .hasSize(2)
                .extracting(PersonDto::getName)
                .containsExactly("Lucc Gizi", "Lucc Géza");
    }
}