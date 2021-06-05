package lambdacomparator;

import java.util.Comparator;
import java.util.List;

public class Clouds {

    public static final String EMPTY_LIST = "Empty list";

    public CloudStorage alphabeticallyFirst(List<CloudStorage> cloudStorages) {
        return cloudStorages.stream()
                .min(Comparator.comparing(CloudStorage::getProvider, String::compareToIgnoreCase))
                .orElseThrow(() -> new IllegalArgumentException(EMPTY_LIST));
    }

    public CloudStorage bestPriceForShortestPeriod(List<CloudStorage> cloudStorages) {
        return cloudStorages.stream()
                .min(Comparator.comparing(CloudStorage::getPeriod,
                        Comparator.nullsFirst(Comparator.comparing(PayPeriod::getLength)))
                .thenComparingDouble(CloudStorage::getPrice))
                .orElseThrow(() -> new IllegalArgumentException(EMPTY_LIST));
    }

    public List<CloudStorage> worstOffers(List<CloudStorage> cloudStorages) {
        return cloudStorages.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
    }
}
