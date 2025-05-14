package xrddev.practiceportal.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "internshipAssignments")
@EqualsAndHashCode(callSuper = true)
public class Student extends User{

    @Column(name = "student_number", nullable = false, unique = true)
    @NotNull(message = "Student number cannot be null!")
    @Size(min = 5, max = 7, message = "Student number must be between 5 and 7 characters.")
    private String studentNumber;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotNull(message = "First name cannot be null!")
    @Size(max = 50, message = "First name can have up to 50 characters.")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotNull(message = "Last name cannot be null!")
    @Size(max = 50, message = "Last name can have up to 50 characters.")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false, length = 50)
    @NotNull(message = "Department cannot be null!")
    private Department department;

    @Column(name = "year_of_study", nullable = true)
    @Min(value = 1, message = "Year of study must be at least 1.")
    @Max(value = 10, message = "Year of study cannot exceed 10.")
    private int yearOfStudy;

    @ElementCollection(targetClass = Skills.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "student_skills", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "skill")
    private List<Skills> skills;

    @ElementCollection(targetClass = Interests.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "student_interests", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "interest")
    private List<Interests> interests;

    @Column(name = "average_grade", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Average grade must be at least 0.0.")
    @DecimalMax(value = "10.0", inclusive = true, message = "Average grade must not exceed 10.0.")
    private Double averageGrade;

    @Column(name = "preferred_location", nullable = true, length = 50)
    @Size(max = 50, message = "Preferred location can have up to 50 characters.")
    private String preferredLocation;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<InternshipAssignment> internshipAssignments;

}