package xrddev.practiceportal.service.strategies.internship.strategy;

import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.internship_position.InternshipPosition;
import xrddev.practiceportal.model.student.Student;

import java.util.List;

public interface InternshipAssigmentStrategy {

    InternshipMatchingOptions getStrategyType();

    List<InternshipAssignment> match(List<Student> students, List<InternshipPosition> internshipPositions);
}
