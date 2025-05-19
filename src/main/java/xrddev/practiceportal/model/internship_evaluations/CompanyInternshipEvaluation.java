package xrddev.practiceportal.model.internship_evaluations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import xrddev.practiceportal.model.enums.OverallGrade;
import xrddev.practiceportal.model.enums.Rating;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;

@Entity
@Getter
@Setter
@Table(name = "company_internship_evaluation")
public class CompanyInternshipEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rating motivation;

    @Enumerated(EnumType.STRING)
    private Rating effectiveness;

    @Enumerated(EnumType.STRING)
    private Rating efficiency;

    private String overallGrade;

    private String comments;
}
