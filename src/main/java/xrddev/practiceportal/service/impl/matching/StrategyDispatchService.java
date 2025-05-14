package xrddev.practiceportal.service.impl.matching;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.model.enums.AssignmentStrategy;
import xrddev.practiceportal.service.api.PositionMatchingStrategy;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StrategyDispatchService implements xrddev.practiceportal.service.api.StrategyDispatchService {

    private final List<PositionMatchingStrategy> strategies;

    @Override
    @Transactional
    public void dispatchStrategy(AssignmentStrategy strategy) {
        strategies.stream()
                .filter(s -> s.getStrategyType() == strategy)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Strategy not found: " + strategy))
                .match(); // <--- τώρα καλεί void method
    }
}
