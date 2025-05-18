package xrddev.practiceportal.model.company;

import jakarta.persistence.*;
import lombok.*;
import xrddev.practiceportal.model.internship_position.InternshipPosition;
import xrddev.practiceportal.model.practice_office.User;

import java.util.List;

@Entity
@Data
@ToString(exclude = "internshipPositions")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends User {

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "website", length = 100)
    private String website;

    @Column(name = "internship_coordinator", nullable = false, length = 100)
    private String internshipCoordinator;

    @Column(name = "internship_coordinator_email", nullable = false, length = 100)
    private String internshipCoordinatorEmail;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InternshipPosition> internshipPositions;
}