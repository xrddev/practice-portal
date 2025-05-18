package xrddev.practiceportal.service.strategies.professor.strategy;

import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.professor.Professor;

import java.util.List;

public interface ProfessorMatchingStrategy {
    ProfessorMatchingOptions getStrategyType();
    List<InternshipAssignment> filterProfessorCompatibleAssignments(List<Professor> professors,List<InternshipAssignment> assignments);
}
