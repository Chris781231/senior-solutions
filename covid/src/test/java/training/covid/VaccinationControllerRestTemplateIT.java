package training.covid;

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
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VaccinationControllerRestTemplateIT {

    public static final String URL = "/api/vaccination";

    VaccinationDto vaccination;
    VaccinationDto vaccination2;
    VaccinationDto vaccination3;

    @Autowired
    TestRestTemplate template;

    @Autowired
    VaccinationService service;

    @BeforeEach
    void setUp() {
        service.deleteAll();

        vaccination = template.postForObject(URL, new CreateVaccinationCommand("123456788", "Budapest", LocalDate.now(), Type.PFIZER, Status.RESERVED), VaccinationDto.class);
        vaccination2 = template.postForObject(URL, new CreateVaccinationCommand("123456788", "Budapest", LocalDate.now(), Type.MODERNA, Status.RESERVED), VaccinationDto.class);
        vaccination3 = template.postForObject(URL, new CreateVaccinationCommand("223456788", "Budapest", LocalDate.now(), Type.SYNOPHARM, Status.RESERVED), VaccinationDto.class);
    }

    @Test
    void registrationTest() {
        assertEquals("123456788", vaccination.getSid());
    }

    @Test
    void getVaccionationListTest() {
        List<VaccinationDto> vaccinationDtos = template.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<VaccinationDto>>() {
        }).getBody();

        assertThat(vaccinationDtos)
                .hasSize(3)
                .extracting(VaccinationDto::getType)
                .containsExactly(Type.PFIZER, Type.MODERNA, Type.SYNOPHARM);
    }

    @Test
    void getVaccinationsBySidTest() {
        List<VaccinationDto> vaccinationDtos = template.exchange(
                URL + "?sid=123456788",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<VaccinationDto>>() {
                }
        ).getBody();

        assertThat(vaccinationDtos)
                .hasSize(2)
                .extracting(VaccinationDto::getType)
                .containsExactly(Type.PFIZER, Type.MODERNA);
    }
}
