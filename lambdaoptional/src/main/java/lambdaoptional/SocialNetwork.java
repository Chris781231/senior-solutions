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
        if (members.size() == 0) { return Optional.empty(); }

        return members.stream()
                .collect(Collectors.averagingDouble(m -> m.getSkills().size()))
                .describeConstable();
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }
}
