package xrddev.practiceportal.service.strategies.internship.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.model.user.Student;
import xrddev.practiceportal.service.internship.InternshipPositionService;
import xrddev.practiceportal.service.strategies.SimilarityMetrics;
import xrddev.practiceportal.service.student.StudentService;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InterestsAndSkillsMatching implements InternshipAssigmentStrategy {

    private final StudentService studentService;
    private final InternshipPositionService positionService;

    private static final double MIN_JACCARD_THRESHOLD = 0.3;

    @Override
    public InternshipMatchingOptions getStrategyType() {
        return InternshipMatchingOptions.INTERESTS_AND_SKILLS;
    }

    @Override
    public List<InternshipAssignment> match() {
        List<InternshipAssignment> internshipAssignments = new ArrayList<>();

        List<InternshipPosition> positions = positionService.getAll();
        studentService.findAll().forEach(student -> {
            InternshipPosition matchedPosition = findBestMatchForStudent(student, positions);
            if(matchedPosition != null) {
                InternshipAssignment assignment = new InternshipAssignment();
                assignment.setPosition(matchedPosition);
                assignment.setStudent(student);
                assignment.setProfessor(null);
                assignment.setStudentMatchStrategy(InternshipMatchingOptions.INTERESTS_AND_SKILLS);
                assignment.setAssignedAt(LocalDate.now());
                internshipAssignments.add(assignment);
            }
        });
        return internshipAssignments;
    }

    private InternshipPosition findBestMatchForStudent(Student student, List<InternshipPosition> positions) {
        return positions.stream()
                .filter(position -> isCompatible(student, position))
                .findFirst()
                .orElse(null);
    }

    private boolean isCompatible(Student student, InternshipPosition position) {
        double interestSimilarity = SimilarityMetrics.jaccardSimilarity(student.getInterests(), position.getInterests());
        double skillSimilarity = SimilarityMetrics.jaccardSimilarity(student.getSkills(), position.getSkills());
        return interestSimilarity >= MIN_JACCARD_THRESHOLD || skillSimilarity >= MIN_JACCARD_THRESHOLD;
    }
}
