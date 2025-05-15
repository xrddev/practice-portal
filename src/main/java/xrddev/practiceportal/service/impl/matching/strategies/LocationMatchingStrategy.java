package xrddev.practiceportal.service.impl.matching.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.AssignmentStrategy;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.repository.api.InternshipAssignmentRepository;
import xrddev.practiceportal.service.api.InternshipPositionService;
import xrddev.practiceportal.service.api.PositionMatchingStrategy;
import xrddev.practiceportal.service.api.StudentService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationMatchingStrategy implements PositionMatchingStrategy {

    private final StudentService studentService;
    private final InternshipPositionService internshipPositionService;

    @Override
    public AssignmentStrategy getStrategyType() {
        return AssignmentStrategy.LOCATION;
    }

    @Override
    public List<InternshipAssignment> match() {
        Map<String, Deque<InternshipPosition>> locationToPositionMap =
                internshipPositionService.findAllAvailable()
                        .stream()
                        .collect(Collectors.groupingBy(position -> position.getLocation().trim().toUpperCase()
                                ,Collectors.toCollection(ArrayDeque::new)));

        LocalDate localDate = LocalDate.now();

        return studentService.findAll()
                .stream()
                .map(student -> {
                    String preferredLocation = student.getPreferredLocation().trim().toUpperCase();
                    Deque<InternshipPosition> availablePositionsAtTheSpecificLocation = locationToPositionMap.get(preferredLocation);

                    //No Positions at the specific location at all or no positions left
                    if (availablePositionsAtTheSpecificLocation == null || availablePositionsAtTheSpecificLocation.isEmpty()) return null;

                    InternshipPosition internshipPosition = availablePositionsAtTheSpecificLocation.pollFirst();
                    InternshipAssignment matchedAssignment = new InternshipAssignment();
                    matchedAssignment.setStudent(student);
                    matchedAssignment.setPosition(internshipPosition);
                    matchedAssignment.setStrategy(AssignmentStrategy.LOCATION);
                    matchedAssignment.setAssignedAt(localDate);
                    matchedAssignment.setProfessor(null);

                    System.out.println("Matched position: " + internshipPosition.getTitle() + " to student: " + student.getEmail() + "");
                    return matchedAssignment;})
                .filter(Objects::nonNull)
                .toList();
    }
}
