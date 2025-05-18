package xrddev.practiceportal.service.strategies.professor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.professor.Professor;
import xrddev.practiceportal.service.strategies.ProfessorStrategyDispatcher;
import xrddev.practiceportal.service.strategies.professor.strategy.ProfessorMatchingStrategy;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("mapDrivenProfessorDispatcher")
public class MapDrivenProfessorStrategyDispatcher implements ProfessorStrategyDispatcher {

    private final Map<ProfessorMatchingOptions, ProfessorMatchingStrategy> strategyMap;

    public MapDrivenProfessorStrategyDispatcher(List<ProfessorMatchingStrategy> strategies) {
        this.strategyMap = strategies.stream().collect(
                Collectors.toMap(
                        ProfessorMatchingStrategy::getStrategyType,
                        strategy -> strategy,
                        (a, b) -> { throw new IllegalStateException("Duplicate strategy: " + a.getStrategyType()); },
                        () -> new EnumMap<>(ProfessorMatchingOptions.class)
                )
        );
    }

    @Override
    public List<InternshipAssignment> dispatch(ProfessorMatchingOptions strategy, List<Professor> professors, List<InternshipAssignment> internshipAssignments) {
         return Optional.ofNullable(strategyMap.get(strategy))
                .orElseThrow(() -> new IllegalArgumentException("Unknown professor strategy: " + strategy))
                .filterProfessorCompatibleAssignments(professors,internshipAssignments);
    }
}
