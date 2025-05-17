package xrddev.practiceportal.service.strategies.internship.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;

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
    public List<InternshipAssignment> match() {
        List<InternshipAssignment> locationMatchedPositions = locationMatching.match();
        Map<Long, Long> interestsAndSkillsStudentIDtoPositionIDMap = interestsAndSkillsMatching.match().stream()
                .collect(Collectors.toMap(
                        internshipAssignment -> internshipAssignment.getStudent().getId(),
                        internshipAssignment -> internshipAssignment.getPosition().getId()));


        //Filter out positions that don't match the student's interests
        return locationMatchedPositions.stream().filter(locationPosition -> {
            Long mapPositionID = interestsAndSkillsStudentIDtoPositionIDMap.get(locationPosition.getStudent().getId());
            return mapPositionID != null && mapPositionID.equals(locationPosition.getPosition().getId());
        }).toList();
    }
}
