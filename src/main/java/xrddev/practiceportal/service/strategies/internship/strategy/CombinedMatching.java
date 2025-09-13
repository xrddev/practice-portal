package xrddev.practiceportal.service.strategies.internship.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.internship_position.InternshipPosition;
import xrddev.practiceportal.model.student.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CombinedMatching implements InternshipAssigmentStrategy {

    private final InterestsAndSkillsMatching interestsAndSkillsMatching;
    private final LocationMatching locationMatching;

    @Override
    public InternshipMatchingOptions getStrategyType() {
        return InternshipMatchingOptions.COMBINED;
    }

    @Override
    public List<InternshipAssignment> match(List<Student> students, List<InternshipPosition> positions) {
        List<InternshipAssignment> interestsAndSkillsMatched = interestsAndSkillsMatching.match(students, positions);
        List<InternshipAssignment> locationMatched = locationMatching.match(students, positions);


        List<InternshipAssignment> combined = new ArrayList<>();

        for(InternshipAssignment assignment1 : interestsAndSkillsMatched){
            for(InternshipAssignment assignment2 : locationMatched){
                if(Objects.equals(assignment1.getStudent().getId(), assignment2.getStudent().getId())
                    && Objects.equals(assignment1.getPosition().getId(), assignment2.getPosition().getId())){
                    assignment1.setStudentMatchStrategy(InternshipMatchingOptions.COMBINED);
                    combined.add(assignment1);
                }
            }
        }
        return combined;
    }
}
