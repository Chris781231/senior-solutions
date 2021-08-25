package bonus.therapy;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ParticipantNotRegisteredException extends AbstractThrowableProblem {

    public ParticipantNotRegisteredException(long participnatId, long therapySessionId) {
        super(URI.create("therapy/participant-not-registered-to-this-therapysession"),
                "Bad request",
                Status.BAD_REQUEST,
                String.format("Participant with id %d not registered Therapysession with id %d", participnatId, therapySessionId));
    }
}
