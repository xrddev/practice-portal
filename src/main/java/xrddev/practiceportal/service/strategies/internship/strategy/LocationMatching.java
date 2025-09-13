package xrddev.practiceportal.service.strategies.internship.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.internship_position.InternshipPosition;
import xrddev.practiceportal.model.student.Student;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationMatching implements InternshipAssigmentStrategy {

    @Override
    public InternshipMatchingOptions getStrategyType() {
        return InternshipMatchingOptions.LOCATION;
    }

    @Override
    public List<InternshipAssignment> match(List<Student> students, List<InternshipPosition> internshipPositions) {
        Map<String, Queue<InternshipPosition>> locationToPositions = internshipPositions.stream()
                .collect(Collectors.groupingBy(
                        position -> position.getLocation().trim().toUpperCase(),
                                Collectors.toCollection(LinkedList::new)));

        List<InternshipAssignment> internshipAssignments = new ArrayList<>();

        for (Student student : students) {
            String preferredLocation = student.getPreferredLocation().trim().toUpperCase();
            Queue<InternshipPosition> available = locationToPositions.get(preferredLocation);
            if (available == null || available.isEmpty()) continue;

            InternshipAssignment assignment = new InternshipAssignment();
            assignment.setPosition(available.poll());
            assignment.setStudent(student);
            assignment.setProfessor(null);
            assignment.setStudentMatchStrategy(InternshipMatchingOptions.LOCATION);
            assignment.setAssignedAt(LocalDate.now());

            internshipAssignments.add(assignment);
        }
        return internshipAssignments;
    }
}
