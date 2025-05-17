package xrddev.practiceportal.service.strategies.internship;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.service.strategies.InternshipAssigmentStrategyDispatcher;
import xrddev.practiceportal.service.strategies.internship.strategy.InternshipAssigmentStrategy;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("mapDrivenInternshipAssigmentDispatcher")
public class MapDrivenInternshipAssigmentDispatcher implements InternshipAssigmentStrategyDispatcher {

    private final Map<InternshipMatchingOptions, InternshipAssigmentStrategy> assignmentStrategyMap;

    public MapDrivenInternshipAssigmentDispatcher(List<InternshipAssigmentStrategy> strategyList) {
        this.assignmentStrategyMap = strategyList.stream().collect(
                Collectors.toMap(
                        InternshipAssigmentStrategy::getStrategyType,
                        strategy -> strategy,
                        (a, b) -> {throw new IllegalStateException("Duplicate strategy: " + a.getStrategyType());},
                        () -> new EnumMap<>(InternshipMatchingOptions.class)
                ));
    }

    @Override
    @Transactional
    public List<InternshipAssignment> dispatch(InternshipMatchingOptions strategy) {
        return Optional.ofNullable(assignmentStrategyMap.get(strategy))
                .orElseThrow(() -> new IllegalArgumentException("No matching InternshipMatchingOptions for: " + strategy))
                .match();
    }
}
