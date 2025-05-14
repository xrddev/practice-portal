package xrddev.practiceportal.service.impl.matching.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.model.enums.AssignmentStrategy;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.model.user.Student;
import xrddev.practiceportal.repository.api.InternshipAssignmentRepository;
import xrddev.practiceportal.service.api.InternshipPositionService;
import xrddev.practiceportal.service.api.PositionMatchingStrategy;
import xrddev.practiceportal.service.api.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CombinedMatchingStrategy implements PositionMatchingStrategy {

    private final StudentService studentService;
    private final InternshipPositionService positionService;
    private final InternshipAssignmentRepository assignmentRepository;

    private static final double MIN_JACCARD_THRESHOLD = 0.3;

    @Override
    public AssignmentStrategy getStrategyType() {
        return AssignmentStrategy.COMBINED;
    }

    @Override
    public void match() {
    }
}
