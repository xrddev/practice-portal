package xrddev.practiceportal.dto.professor;

import xrddev.practiceportal.model.user.Professor;

public record ProfessorDashboardDto(
        String fullName,
        String email
) {
    public ProfessorDashboardDto(Professor p) {
        this(
                p.getFirstName() + " " + p.getLastName(),
                p.getEmail()
        );
    }
}