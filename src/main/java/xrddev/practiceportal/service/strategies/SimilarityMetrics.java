package xrddev.practiceportal.service.strategies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class SimilarityMetrics {

    private SimilarityMetrics() {
        throw new IllegalStateException("Utility class – no instances allowed");
    }

    public static  <T> double jaccardSimilarity(Collection<T> a, Collection<T> b) {
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) return 0.0;

        Set<T> intersection = new HashSet<>(a);
        intersection.retainAll(b);

        Set<T> union = new HashSet<>(a);
        union.addAll(b);

        return (double) intersection.size() / union.size();
    }
}
