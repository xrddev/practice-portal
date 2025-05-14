package xrddev.practiceportal.dto.intership_position;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.Rating;
import xrddev.practiceportal.model.enums.OverallGrade;

@NoArgsConstructor
@Data
public class ProfessorInternshipEvaluationDto {

    @NotNull
    private Long positionId;

    @NotNull(message = "Motivation rating is required.")
    private Rating motivation;

    @NotNull(message = "Effectiveness rating is required.")
    private Rating effectiveness;

    @NotNull(message = "Efficiency rating is required.")
    private Rating efficiency;

    @NotNull(message = "Overall grade is required.")
    private OverallGrade overallGrade;

    @Size(max = 500, message = "Comments can be up to 500 characters.")
    private String comments;
}
