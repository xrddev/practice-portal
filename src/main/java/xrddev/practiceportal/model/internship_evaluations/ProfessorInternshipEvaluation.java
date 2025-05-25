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
@Table(name = "professor_internship_evaluation")
public class ProfessorInternshipEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "professorEvaluation")
    private InternshipAssignment assignment;

    @Enumerated(EnumType.STRING)
    private Rating motivation;

    @Enumerated(EnumType.STRING)
    private Rating effectiveness;

    @Enumerated(EnumType.STRING)
    private Rating efficiency;

    private String overallGrade;

    private String comments;
}
