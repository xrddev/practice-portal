package xrddev.practiceportal.model.internship;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.OverallGrade;
import xrddev.practiceportal.model.enums.Rating;

@Entity
@Table(name = "professor_evaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long id;

    @OneToOne(mappedBy = "professorInternshipEvaluation")
    private InternshipPosition internshipPosition;

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