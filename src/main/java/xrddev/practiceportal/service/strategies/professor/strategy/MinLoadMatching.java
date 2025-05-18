package xrddev.practiceportal.service.strategies.professor.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.professor.Professor;
import xrddev.practiceportal.service.professor.ProfessorService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MinLoadMatching implements ProfessorMatchingStrategy {

    private final ProfessorService professorService;

    @Override
    public ProfessorMatchingOptions getStrategyType() {
        return ProfessorMatchingOptions.MIN_LOAD;
    }

    @Override
    public List<InternshipAssignment> filterProfessorCompatibleAssignments(List<Professor> professors,List<InternshipAssignment> internshipAssignments) {
        List<InternshipAssignment> professorCompatibleInternshipAssignments = new ArrayList<>();
        Map<Professor, Integer> loadMap = new HashMap<>();
        PriorityQueue<Professor> professorQueue = new PriorityQueue<>(Comparator.comparingInt(loadMap::get));

        professors.forEach(professor -> {
            loadMap.put(professor, 0);
            professorQueue.add(professor);
        });

        internshipAssignments.forEach(internshipAssignment -> {
            if (!professorQueue.isEmpty()) {
                Professor leastLoaded = professorQueue.poll();

                internshipAssignment.setProfessor(leastLoaded);
                internshipAssignment.setProfessorMatchStrategy(ProfessorMatchingOptions.MIN_LOAD);
                professorCompatibleInternshipAssignments.add(internshipAssignment);

                int updatedLoad = loadMap.get(leastLoaded) + 1;
                loadMap.put(leastLoaded, updatedLoad);
                professorQueue.add(leastLoaded);
            }
        });
        return professorCompatibleInternshipAssignments;
    }
}
