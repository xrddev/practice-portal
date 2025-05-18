package xrddev.practiceportal.dto.internship_assigment;

import lombok.Data;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;

import java.time.LocalDate;

import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.internship_position.InternshipPosition;
import xrddev.practiceportal.model.professor.Professor;
import xrddev.practiceportal.model.student.Student;

@Data
public class InternshipAssignmentDashboardDto {

    private Long id;

    private Student student;

    private InternshipPosition position;

    private Professor professor;

    private LocalDate assignedAt;

    private InternshipMatchingOptions positionStrategy;

    private ProfessorMatchingOptions professorStrategy;

    public InternshipAssignmentDashboardDto(InternshipAssignment internshipAssignment) {
        this.id = internshipAssignment.getId();
        this.student = internshipAssignment.getStudent();
        this.position = internshipAssignment.getPosition();
        this.professor = internshipAssignment.getProfessor();
        this.assignedAt = internshipAssignment.getAssignedAt();
        this.positionStrategy = internshipAssignment.getStudentMatchStrategy();
        this.professorStrategy = internshipAssignment.getProfessorMatchStrategy();
    }
}
