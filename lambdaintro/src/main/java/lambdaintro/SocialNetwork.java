package lambdaintro;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SocialNetwork {

    List<Member> members;

    public SocialNetwork(List<Member> members) {
        this.members = members;
    }

    public List<Member> findMembersBy(Predicate<Member> condition) {
        return members.stream()
                .filter(condition)
                .toList();
    }

    public void applyToSelectedMembers(Predicate<Member> condition, Consumer<Member> consumer) {
        members.stream()
                .filter(condition)
                .forEach(consumer);
    }

    public List<String> transformMembers(Function<Member, String> function) {
        return members.stream()
                .map(function)
                .toList();
    }
}
