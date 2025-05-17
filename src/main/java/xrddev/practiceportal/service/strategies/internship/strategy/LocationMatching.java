package xrddev.practiceportal.service.strategies.internship.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.service.internship.InternshipPositionService;
import xrddev.practiceportal.service.student.StudentService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationMatching implements InternshipAssigmentStrategy {

    private final StudentService studentService;
    private final InternshipPositionService internshipPositionService;

    @Override
    public InternshipMatchingOptions getStrategyType() {
        return InternshipMatchingOptions.LOCATION;
    }

    @Override
    public List<InternshipAssignment> match() {
        Map<String, Deque<InternshipPosition>> locationToPositionMap =
                internshipPositionService.getAll()
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
                    matchedAssignment.setStudentMatchStrategy(InternshipMatchingOptions.LOCATION);
                    matchedAssignment.setAssignedAt(localDate);
                    matchedAssignment.setProfessor(null);
                    return matchedAssignment;})
                .filter(Objects::nonNull)
                .toList();
    }
}
