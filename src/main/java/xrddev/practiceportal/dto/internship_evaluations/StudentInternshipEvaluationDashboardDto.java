package xrddev.practiceportal.dto.internship_evaluations;

import lombok.Getter;
import lombok.Setter;
import xrddev.practiceportal.model.enums.Rating;

@Getter
@Setter
public class StudentInternshipEvaluationDashboardDto {
    private Rating rating;
    private String comment;
}
