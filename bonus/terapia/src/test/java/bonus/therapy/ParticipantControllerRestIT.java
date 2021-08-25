package bonus.therapy;

import bonus.therapy.participant.CreateParticipantCommand;
import bonus.therapy.participant.ParticipantDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from participants"})
public class ParticipantControllerRestIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    void testAddNewPlayers(){
        ParticipantDto result = template.postForObject("/api/participants",
                        new CreateParticipantCommand("John Doe"),
                        ParticipantDto.class);

        assertEquals("John Doe",result.getName());
    }

    @Test
    void testListParticipants() {
        template.postForObject("/api/participants",
                new CreateParticipantCommand("John Doe"),
                ParticipantDto.class);

        template.postForObject("/api/participants",
                new CreateParticipantCommand("Jack Doe"),
                ParticipantDto.class);

        List<ParticipantDto> result = template.exchange(
                "/api/participants",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ParticipantDto>>() {
                }).getBody();

        assertThat(result).extracting(ParticipantDto::getName)
                .containsExactly("John Doe","Jack Doe");
    }

    @Test
    void testCreatePlayerWithInvalidName(){
        Problem result =
                template.postForObject("/api/participants",
                        new CreateParticipantCommand(""),
                        Problem.class);

        assertEquals(Status.BAD_REQUEST,result.getStatus());
    }
}
