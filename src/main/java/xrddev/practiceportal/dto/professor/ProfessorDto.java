package xrddev.practiceportal.dto.professor;

import xrddev.practiceportal.model.user.Professor;

public record ProfessorDto(
        Long   id,
        String email,
        String firstName,
        String lastName
) {
    public ProfessorDto(Professor p) {
        this(
                p.getId(),
                p.getEmail(),
                p.getFirstName(),
                p.getLastName()
        );
    }
}