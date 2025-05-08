package xrddev.practiceportal.dto.intership_position;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternshipPositionCreateDto {

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Maximum 150 characters allowed")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Maximum 500 characters allowed")
    private String description;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @NotEmpty(message = "Please select at least one skill")
    private List<Skills> skills;

    @NotEmpty(message = "Please select at least one interest")
    private List<Interests> interests;
}
