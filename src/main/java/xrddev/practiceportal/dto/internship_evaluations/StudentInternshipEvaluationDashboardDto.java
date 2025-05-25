package xrddev.practiceportal.dto.internship_evaluations;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xrddev.practiceportal.model.enums.Rating;
import xrddev.practiceportal.model.internship_evaluations.StudentInternshipEvaluation;

@Data
@NoArgsConstructor
public class StudentInternshipEvaluationDashboardDto {
    private Rating rating;
    private String comment;

    public StudentInternshipEvaluationDashboardDto(StudentInternshipEvaluation studentInternshipEvaluation) {
        if(studentInternshipEvaluation == null) return;
        this.rating = studentInternshipEvaluation.getRating();
        this.comment = studentInternshipEvaluation.getComment();
    }
}
