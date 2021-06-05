package lambdastreams.baseoperations;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Numbers {

    List<Integer> numList;

    public Numbers(List<Integer> numList) {
        this.numList = numList;
    }

    public Optional<Integer> min() {
        return numList.stream()
                .min(Comparator.naturalOrder());
    }

    public int sum() {
        return numList.stream()
                .reduce(0, Integer::sum, Integer::sum);
    }

    public boolean isAllPositive() {
        return numList.stream()
                .allMatch(i -> i > 0);
    }

    public List<Integer> getDistinctElements() {
        return numList.stream()
                .distinct()
                .toList();
    }
}
