package xrddev.practiceportal.dto.internship_assigment;

import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDashboardDto;
import xrddev.practiceportal.dto.professor.ProfessorDashboardDto;
import xrddev.practiceportal.dto.student.StudentDashboardDto;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;

import java.time.LocalDate;

import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;

@Data
@NoArgsConstructor
public class InternshipAssignmentDashboardDto {

    private Long id;

    private StudentDashboardDto student;

    private InternshipPositionDashboardDto position;

    private ProfessorDashboardDto professor;

    private LocalDate assignedAt;

    private InternshipMatchingOptions positionStrategy;

    private ProfessorMatchingOptions professorStrategy;

    public InternshipAssignmentDashboardDto(InternshipAssignment internshipAssignment) {
        this.id = internshipAssignment.getId();
        this.student = new StudentDashboardDto(internshipAssignment.getStudent());
        this.position = new InternshipPositionDashboardDto(internshipAssignment.getPosition());
        this.professor = new ProfessorDashboardDto(internshipAssignment.getProfessor());
        this.assignedAt = internshipAssignment.getAssignedAt();
        this.positionStrategy = internshipAssignment.getStudentMatchStrategy();
        this.professorStrategy = internshipAssignment.getProfessorMatchStrategy();
    }
}
