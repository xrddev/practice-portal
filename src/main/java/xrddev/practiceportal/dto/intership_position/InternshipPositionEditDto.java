package xrddev.practiceportal.dto.intership_position;

import jakarta.validation.constraints.*;
import lombok.Data;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.internship.InternshipPosition;

import java.time.LocalDate;
import java.util.List;

@Data
public class InternshipPositionEditDto {

    @NotBlank(message = "Title is required.")
    @Size(max = 150, message = "Title can be up to 150 characters.")
    private String title;

    @NotNull(message = "Description is required.")
    @Size(max = 500, message = "Description can be up to 500 characters.")
    private String description;

    @NotNull(message = "Start date is required.")
    private LocalDate startDate;

    @NotNull(message = "End date is required.")
    private LocalDate endDate;

    @NotEmpty(message = "At least one skill is required.")
    private List<Skills> skills;

    @NotEmpty(message = "At least one interest is required.")
    private List<Interests> interests;

    @AssertTrue(message = "End date must be after or equal to start date.")
    public boolean isValidDateRange() {
        if (startDate == null || endDate == null) return true;
        return !endDate.isBefore(startDate);
    }

    public InternshipPositionEditDto(InternshipPosition internshipPosition){
        this.title = internshipPosition.getTitle();
        this.description = internshipPosition.getDescription();
        this.startDate = internshipPosition.getStartDate();
        this.endDate = internshipPosition.getEndDate();
        this.skills = internshipPosition.getSkills();
        this.interests = internshipPosition.getInterests();
    }
}