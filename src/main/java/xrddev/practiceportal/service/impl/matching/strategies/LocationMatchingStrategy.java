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
    private final InternshipAssignmentRepository internshipAssignmentRepository;

    @Override
    public AssignmentStrategy getStrategyType() {
        return AssignmentStrategy.LOCATION;
    }

    @Override
    public void match() {
        Map<String, List<InternshipPosition>> locationToPositionMap = internshipPositionService.findAllAvailable().stream()
                .collect(Collectors.groupingBy(internshipPosition -> internshipPosition.getLocation().trim()));

        studentService.findAll().forEach(student -> {
            String studentPreferredLocation = student.getPreferredLocation().trim();
            if(locationToPositionMap.containsKey(studentPreferredLocation) && !locationToPositionMap.get(studentPreferredLocation).isEmpty()) {
                InternshipPosition internshipPosition = locationToPositionMap.get(studentPreferredLocation).getFirst();
                locationToPositionMap.get(studentPreferredLocation).removeFirst();

                InternshipAssignment assignment = new InternshipAssignment();
                assignment.setStudent(student);
                assignment.setPosition(internshipPosition);
                assignment.setStrategy(AssignmentStrategy.LOCATION);
                assignment.setAssignedAt(LocalDate.now());
                assignment.setProfessor(null);
                internshipAssignmentRepository.save(assignment);
            }
        });
    }
}
