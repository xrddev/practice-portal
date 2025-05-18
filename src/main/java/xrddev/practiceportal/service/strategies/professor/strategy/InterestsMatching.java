package xrddev.practiceportal.service.strategies.professor.strategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.professor.Professor;
import xrddev.practiceportal.service.professor.ProfessorService;
import xrddev.practiceportal.service.strategies.SimilarityMetrics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestsMatching implements ProfessorMatchingStrategy {

    private static final double MIN_JACCARD_THRESHOLD = 0.3;

    @Override
    public ProfessorMatchingOptions getStrategyType() {
        return ProfessorMatchingOptions.INTERESTS;
    }

    @Override
    public List<InternshipAssignment> filterProfessorCompatibleAssignments(List<Professor> professors, List<InternshipAssignment> internshipAssignments) {
        List<InternshipAssignment> compatibleAssignments = new ArrayList<>();

        for (InternshipAssignment assignment : internshipAssignments) {
            Iterator<Professor> iterator = professors.iterator();
            while (iterator.hasNext()) {
                Professor professor = iterator.next();
                if (isCompatible(professor, assignment)) {
                    assignment.setProfessor(professor);
                    assignment.setProfessorMatchStrategy(ProfessorMatchingOptions.INTERESTS);
                    compatibleAssignments.add(assignment);
                    iterator.remove();
                    break;
                }
            }
        }
        return compatibleAssignments;
    }

    private boolean isCompatible(Professor professor, InternshipAssignment assignment) {
        return SimilarityMetrics.jaccardSimilarity(professor.getInterests(), assignment.getStudent().getInterests()) >= MIN_JACCARD_THRESHOLD;
    }
}






















