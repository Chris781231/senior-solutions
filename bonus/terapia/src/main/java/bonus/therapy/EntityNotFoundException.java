package bonus.therapy;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class EntityNotFoundException extends AbstractThrowableProblem {

    public EntityNotFoundException(long id, String entity) {
        super(URI.create("therapy/" + entity + "-not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format(entity + " with id %d not found", id));
    }
}
