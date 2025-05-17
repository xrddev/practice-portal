package xrddev.practiceportal.service.strategies.professor.strategy;

import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.model.user.Professor;

import java.util.List;

public interface ProfessorMatchingStrategy {
    ProfessorMatchingOptions getStrategyType();
    List<InternshipAssignment> filterProfessorCompatibleAssignments(List<InternshipAssignment> assignments);
}
