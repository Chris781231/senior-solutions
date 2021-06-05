package lambdaoptional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;

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
        if (members == null || members.size() == 0) { return Optional.empty(); }

        return Optional.of(members.stream()
                .mapToDouble(m -> m.getSkills().size())
                .average().getAsDouble());
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }
}
