package xrddev.practiceportal.model.professor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.practice_office.User;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "internshipAssignments")
@EqualsAndHashCode(callSuper = true)
public class Professor extends User {

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false)
    private Department department;

    @ElementCollection(targetClass = Interests.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "professor_interests", joinColumns = @JoinColumn(name = "professor_id"))
    @Column(name = "interest")
    private List<Interests> interests;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<InternshipAssignment> internshipAssignments;
}