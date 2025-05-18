package xrddev.practiceportal.service.strategies;

import org.springframework.beans.factory.annotation.Qualifier;
import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.professor.Professor;

import java.util.List;

public interface ProfessorStrategyDispatcher {
    @Qualifier("mapDrivenProfessorDispatcher")
    List<InternshipAssignment> dispatch(ProfessorMatchingOptions strategy, List<Professor> professors, List<InternshipAssignment> internshipAssignments);
}
