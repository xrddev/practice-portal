package xrddev.practiceportal.dto.intership_position;

import lombok.Data;
import xrddev.practiceportal.model.internship.InternshipPosition;

import java.util.Date;

@Data
public class InternshipPositionResponseDto {
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean available;

    public InternshipPositionResponseDto(InternshipPosition pos) {
        this.id = pos.getId();
        this.title = pos.getTitle();
        this.description = pos.getDescription();
        this.startDate = pos.getStartDate();
        this.endDate = pos.getEndDate();
        this.available = pos.isAvailable();
    }
}