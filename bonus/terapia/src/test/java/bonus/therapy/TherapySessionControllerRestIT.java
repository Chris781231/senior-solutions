package bonus.therapy;

import bonus.therapy.participant.AddTherapySessionCommand;
import bonus.therapy.participant.CreateParticipantCommand;
import bonus.therapy.participant.ParticipantDto;
import bonus.therapy.therapysession.CreateTherapySessionCommand;
import bonus.therapy.therapysession.TherapySessionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from therapy_sessions_participants", "delete from therapy_sessions", "delete from participants"})
public class TherapySessionControllerRestIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    void testCreateNewTherapySession() {
        TherapySessionDto result = template.postForObject("/api/therapysessions",
                new CreateTherapySessionCommand("Jane Doe", "Budapest", LocalDateTime.now().plusDays(10)),
                TherapySessionDto.class);

        assertEquals("Jane Doe", result.getTherapist());
        assertEquals("Budapest", result.getLocation());
    }

    @Test
    void testListTherapySessions() {
        template.postForObject("/api/therapysessions",
                new CreateTherapySessionCommand("Jane Doe", "Budapest", LocalDateTime.now().plusDays(10)),
                TherapySessionDto.class);

        template.postForObject("/api/therapysessions",
                new CreateTherapySessionCommand("Joe Doe", "Vác", LocalDateTime.now().plusDays(12)),
                TherapySessionDto.class);

        List<TherapySessionDto> result = template.exchange("/api/therapysessions",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TherapySessionDto>>() {
                }).getBody();

        assertThat(result).extracting(TherapySessionDto::getTherapist, TherapySessionDto::getLocation)
                .containsExactly(
                        tuple("Jane Doe", "Budapest"),
                        tuple("Joe Doe", "Vác"));
    }

    @Test
    void testCreateTherapySessionWithInvalidData(){
        Problem invalidTherapistProblem = template.postForObject("/api/therapysessions",
                        new CreateTherapySessionCommand("", "Vác", LocalDateTime.now().plusMinutes(1)),
                        Problem.class);
        Problem invalidLocationProblem = template.postForObject("/api/therapysessions",
                new CreateTherapySessionCommand("Jane Doe", "", LocalDateTime.now().plusMinutes(1)),
                Problem.class);
        Problem invalidTimeProblem = template.postForObject("/api/therapysessions",
                new CreateTherapySessionCommand("Jane Doe", "Vác", LocalDateTime.now().minusMinutes(1)),
                Problem.class);
        Problem nullTimeProblem = template.postForObject("/api/therapysessions",
                new CreateTherapySessionCommand("Jane Doe", "Vác", null),
                Problem.class);

        assertEquals(Status.BAD_REQUEST, invalidTherapistProblem.getStatus());
        assertEquals(Status.BAD_REQUEST, invalidLocationProblem.getStatus());
        assertEquals(Status.BAD_REQUEST, invalidTimeProblem.getStatus());
        assertEquals(500, nullTimeProblem.getStatus().getStatusCode());
    }

    @Test
    void testRegisteringParticipantToTherapySession() {
        long therapySessionId = template.postForObject("/api/therapysessions",
                new CreateTherapySessionCommand("Jane Doe", "Budapest", LocalDateTime.now().plusDays(10)),
                TherapySessionDto.class).getId();
        long participantId = template.postForObject("/api/participants",
                new CreateParticipantCommand("John Doe"),
                ParticipantDto.class).getId();
        template.postForObject("/api/participants/" + participantId + "/therapysessions",
                new AddTherapySessionCommand(therapySessionId),
                ParticipantDto.class);

        TherapySessionDto result = template.exchange("/api/therapysessions/" + therapySessionId,
                HttpMethod.GET,
                null,
                TherapySessionDto.class).getBody();

        assertEquals(participantId, result.getParticipants().stream().map(ParticipantDto::getId).toList().get(0));
    }

    @Test
    void testDeleteParticipantFromTherapySession() {
        long therapySessionId = template.postForObject("/api/therapysessions",
                new CreateTherapySessionCommand("Jane Doe", "Budapest", LocalDateTime.now().plusDays(10)),
                TherapySessionDto.class).getId();
        long participantId = template.postForObject("/api/participants",
                new CreateParticipantCommand("John Doe"),
                ParticipantDto.class).getId();
        template.postForObject("/api/participants/" + participantId + "/therapysessions",
                new AddTherapySessionCommand(therapySessionId),
                ParticipantDto.class);

        template.delete("/api/participants/" + participantId + "/therapysessions/" + therapySessionId);

        TherapySessionDto result = template.exchange("/api/therapysessions/" + therapySessionId,
                HttpMethod.GET,
                null,
                TherapySessionDto.class).getBody();

        assertEquals(Collections.EMPTY_LIST, result.getParticipants());
    }

    @Test
    void testDeleteParticipantFromTherapySessionWithInvalidTherapySession() {
        long therapySessionId = template.postForObject("/api/therapysessions",
                new CreateTherapySessionCommand("Jane Doe", "Budapest", LocalDateTime.now().plusDays(10)),
                TherapySessionDto.class).getId();
        long participantId = template.postForObject("/api/participants",
                new CreateParticipantCommand("John Doe"),
                ParticipantDto.class).getId();

        Problem problem = template.exchange("/api/participants/" + participantId + "/therapysessions/" + therapySessionId,
                HttpMethod.DELETE,
                null,
                Problem.class).getBody();

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
    }
}
