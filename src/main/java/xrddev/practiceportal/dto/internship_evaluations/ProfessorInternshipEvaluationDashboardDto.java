package xrddev.practiceportal.dto.internship_evaluations;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.Rating;
import xrddev.practiceportal.model.enums.OverallGrade;
import xrddev.practiceportal.model.internship_evaluations.ProfessorInternshipEvaluation;

@NoArgsConstructor
@Data
public class ProfessorInternshipEvaluationDashboardDto {

    @NotNull
    private Long positionId;

    @NotNull(message = "Motivation rating is required.")
    private Rating motivation;

    @NotNull(message = "Effectiveness rating is required.")
    private Rating effectiveness;

    @NotNull(message = "Efficiency rating is required.")
    private Rating efficiency;

    @Pattern(regexp = "^(10(\\.0+)?|[0-9](\\.\\d+)?)$", message = "Grade must be between 0 and 10")
    private String overallGrade;

    @Size(max = 500, message = "Comments can be up to 500 characters.")
    private String comments;

    public ProfessorInternshipEvaluationDashboardDto(ProfessorInternshipEvaluation evaluation) {
        if(evaluation == null) return;
        this.positionId = evaluation.getId();
        this.motivation = evaluation.getMotivation();
        this.effectiveness = evaluation.getEffectiveness();
        this.efficiency = evaluation.getEfficiency();
        this.overallGrade = evaluation.getOverallGrade();
        this.comments = evaluation.getComments();
    }

}
