package lambdaoptional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SocialNetwork {

    private final List<Member> members;

    public SocialNetwork(List<Member> members) {
        this.members = members;
    }

    public Optional<Member> findFirst(Predicate<Member> cond) {
        return members.stream()
                .filter(cond)
                .findFirst();
    }

    public Optional<Double> averageNumberOfSkills() {
        OptionalDouble average = members.stream()
                .mapToDouble(m -> m.getSkills().size())
                .average();
        return average.isPresent() ? Optional.of(average.getAsDouble()) : Optional.empty();
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }
}
