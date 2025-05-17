package xrddev.practiceportal.dto;

import lombok.Data;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDashboardDto;
import xrddev.practiceportal.dto.user.professor.ProfessorDashboardDto;
import xrddev.practiceportal.dto.user.student.StudentDashboardDto;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;

import java.time.LocalDate;

@Data
public class InternshipAssignmentDashboardDto {

    private Long id;
    private InternshipMatchingOptions strategy;
    private LocalDate assignedAt;

    private InternshipPositionDashboardDto position;
    private StudentDashboardDto student;
    private ProfessorDashboardDto professor;

    public InternshipAssignmentDashboardDto(InternshipAssignment assignment) {
        this.id = assignment.getId();
        this.strategy = assignment.getStudentMatchStrategy();
        this.assignedAt = assignment.getAssignedAt();
        this.position = new InternshipPositionDashboardDto(assignment.getPosition());
        this.student = new StudentDashboardDto(assignment.getStudent());
        this.professor = new ProfessorDashboardDto(assignment.getProfessor());
    }
}
