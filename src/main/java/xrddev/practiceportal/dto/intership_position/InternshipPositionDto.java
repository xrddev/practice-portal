package xrddev.practiceportal.dto.intership_position;

import xrddev.practiceportal.model.InternshipPosition;

public record InternshipPositionDto(
    Long id,
    String title,
    String description,
    String studentName
) {
    public InternshipPositionDto(InternshipPosition ip) {
        this(
            ip.getId(),
            ip.getTitle(),
            ip.getDescription(),
            ip.getStudent().getFirstName() + " " + ip.getStudent().getLastName()
        );
    }
}