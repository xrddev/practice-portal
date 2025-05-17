package xrddev.practiceportal.service.strategies;

import org.springframework.beans.factory.annotation.Qualifier;
import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.model.user.Professor;

import java.util.List;

public interface ProfessorStrategyDispatcher {
    @Qualifier("mapDrivenProfessorDispatcher")
    List<InternshipAssignment> dispatch(ProfessorMatchingOptions strategy, List<InternshipAssignment> internshipAssignments);
}
