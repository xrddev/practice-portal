package xrddev.practiceportal.service.strategies.professor.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.model.user.Professor;
import xrddev.practiceportal.service.professor.ProfessorService;
import xrddev.practiceportal.service.strategies.SimilarityMetrics;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestsMatching implements ProfessorMatchingStrategy {

    private final ProfessorService professorService;

    private static final double MIN_JACCARD_THRESHOLD = 0.3;

    @Override
    public ProfessorMatchingOptions getStrategyType() {
        return ProfessorMatchingOptions.INTERESTS;
    }

    @Override
    public List<InternshipAssignment> filterProfessorCompatibleAssignments(List<InternshipAssignment> internshipAssignments) {
        return professorService.getAll()
                .stream()
                .map(professor -> {
                    InternshipAssignment assignment = findBestMatchForProfessor(professor, internshipAssignments);
                    if (assignment != null) {
                        assignment.setProfessor(professor);
                        assignment.setProfessorMatchStrategy(ProfessorMatchingOptions.INTERESTS);
                        return assignment;
                    }
                    return null;})
                .filter(java.util.Objects::nonNull)
                .toList();
    }



    private InternshipAssignment findBestMatchForProfessor(Professor professor, List<InternshipAssignment> internshipAssignments) {
        return internshipAssignments
                .stream()
                .filter(assignment -> isCompatible(professor, assignment))
                .findFirst()
                .orElse(null);
    }

    private boolean isCompatible(Professor professor, InternshipAssignment assignment) {
        return SimilarityMetrics.jaccardSimilarity(professor.getInterests(), assignment.getStudent().getInterests()) >= MIN_JACCARD_THRESHOLD;
    }
}






















