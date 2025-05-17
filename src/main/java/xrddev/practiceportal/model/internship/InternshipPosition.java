package xrddev.practiceportal.model.internship;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.user.Company;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "company", "internshipAssignment" })
@Table(name = "internship_position")
public class InternshipPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    @NotNull(message = "The title of the position cannot be null!")
    @Size(max = 150, message = "The title can be up to 150 characters.")
    private String title;

    @Column(name = "description", nullable = false, length = 500)
    @NotNull(message = "The description of the position cannot be null!")
    @Size(max = 500, message = "The description can be up to 500 characters.")
    private String description;

    @Column(name = "start_date", nullable = false)
    @NotNull(message = "The start date of the position cannot be null!")
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @NotNull(message = "The end date of the position cannot be null!")
    private LocalDate endDate;

    @ElementCollection(targetClass = Skills.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "position_skills", joinColumns = @JoinColumn(name = "position_id"))
    @Column(name = "skill")
    private List<Skills> skills;

    @ElementCollection(targetClass = Interests.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "position_interests", joinColumns = @JoinColumn(name = "position_id"))
    @Column(name = "interest")
    private List<Interests> interests;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToOne(mappedBy = "position")
    private InternshipAssignment internshipAssignment;

    @Column(name = "is_assigned", nullable = false)
    private boolean isAssigned = false;

    public String getLocation() {
        return (company != null) ? company.getAddress().split(",")[1] : null;
    }

}