package xrddev.practiceportal.model.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.practice_office.User;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "internshipAssignments")
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

    @Column(name = "student_number", nullable = false, unique = true)
    private String studentNumber;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false, length = 50)
    private Department department;

    @Column(name = "year_of_study", nullable = true)
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
    private Double averageGrade;

    @Column(name = "preferred_location", nullable = true, length = 50)
    @Size(max = 50, message = "Preferred location can have up to 50 characters.")
    private String preferredLocation;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InternshipAssignment> internshipAssignments;
}