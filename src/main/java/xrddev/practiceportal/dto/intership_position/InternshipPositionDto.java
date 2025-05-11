package xrddev.practiceportal.dto.intership_position;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.dto.student.StudentDto;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.internship.InternshipPosition;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
public class InternshipPositionDto {

    private Long id;

    @NotNull(message = "Title is required.")
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

    private StudentDto student;


    public InternshipPositionDto(InternshipPosition position) {
        this.id = position.getId();
        this.title = position.getTitle();
        this.description = position.getDescription();
        this.startDate = position.getStartDate();
        this.endDate = position.getEndDate();
        this.skills = position.getSkills();
        this.interests = position.getInterests();
    }
}