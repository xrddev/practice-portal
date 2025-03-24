package xrddev.practiceportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternshipCommittee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "committee_id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    @NotNull(message = "Committee name cannot be null!")
    @Size(max = 100, message = "Committee name can be up to 100 characters.")
    private String name;

    @OneToMany(mappedBy = "committee", cascade = CascadeType.ALL)
    private List<Professor> members; // Καθηγητές που ανήκουν στην ομάδα
}