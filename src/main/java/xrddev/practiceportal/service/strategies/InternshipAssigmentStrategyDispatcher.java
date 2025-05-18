package xrddev.practiceportal.service.strategies;

import org.springframework.beans.factory.annotation.Qualifier;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.internship_position.InternshipPosition;
import xrddev.practiceportal.model.student.Student;

import java.util.List;

public interface InternshipAssigmentStrategyDispatcher {

    @Qualifier("mapDrivenInternshipAssigmentDispatcher")
    List<InternshipAssignment> dispatch(InternshipMatchingOptions strategy, List<Student> students, List<InternshipPosition> internshipPositions);
}
