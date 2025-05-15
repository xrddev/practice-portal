package xrddev.practiceportal.service.impl.matching;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.model.enums.AssignmentStrategy;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.service.api.PositionMatchingStrategy;
import xrddev.practiceportal.service.api.StrategyDispatchService;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StrategyDispatchServiceImpl implements StrategyDispatchService {

    private final Map<AssignmentStrategy, PositionMatchingStrategy> strategyMap;

    public StrategyDispatchServiceImpl(List<PositionMatchingStrategy> strategyList) {
        this.strategyMap = strategyList.stream().collect(
                Collectors.toMap(PositionMatchingStrategy::getStrategyType, strategy -> strategy,
                        (a, b) -> { throw new IllegalStateException("Duplicate strategy: " + a.getStrategyType()); },
                        () -> new EnumMap<>(AssignmentStrategy.class)));
    }

    @Override
    @Transactional
    public List<InternshipAssignment> dispatchStrategy(AssignmentStrategy strategy) {
        return Optional.ofNullable(strategyMap.get(strategy))
                .orElseThrow(() -> new IllegalArgumentException("No matching strategy for: " + strategy))
                .match();
    }
}
