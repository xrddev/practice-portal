package xrddev.practiceportal.model.internship_evaluations;

import jakarta.persistence.*;
import lombok.*;
import xrddev.practiceportal.model.enums.Rating;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentInternshipEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rating rating;

    @Column(length = 1000)
    private String comment;

}
