package xrddev.practiceportal.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.model.enums.Interests;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "internshipAssignments")
@EqualsAndHashCode(callSuper = true)
public class Professor extends User {

    @Column(name = "first_name", nullable = false, length = 50)
    @NotNull(message = "First name cannot be null!")
    @Size(max = 50, message = "First name can have up to 50 characters.")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotNull(message = "Last name cannot be null!")
    @Size(max = 50, message = "Last name can have up to 50 characters.")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false)
    @NotNull(message = "Department cannot be null!")
    private Department department;


    @ElementCollection(targetClass = Interests.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "professor_interests", joinColumns = @JoinColumn(name = "professor_id"))
    @Column(name = "interest")
    private List<Interests> interests;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<InternshipAssignment> internshipAssignments;
}