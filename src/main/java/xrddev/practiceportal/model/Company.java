package xrddev.practiceportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends User {

    @Column(name = "company_name", nullable = false, length = 150)
    @NotNull(message = "Company name cannot be null!")
    @Size(max = 150, message = "Company name can be up to 150 characters.")
    private String companyName;

    @Column(name = "address", nullable = false, length = 200)
    @NotNull(message = "Address cannot be null!")
    @Size(max = 200, message = "Address can be up to 200 characters.")
    private String address;

    @Column(name = "phone", nullable = false, length = 15)
    @NotNull(message = "Phone cannot be null!")
    @Pattern(regexp = "^\\+?[0-9 ]{7,15}$", message = "Phone number must be valid (7-15 digits, optional +).")
    private String phone;

    @Column(name = "website", nullable = true, length = 100)
    @Size(max = 100, message = "Website can be up to 100 characters.")
    @Pattern(regexp = "^(https?:\\/\\/)?([\\w\\-]+\\.)+[\\w\\-]+(\\/\\S*)?$", 
            message = "Website must be a valid URL format.")
    private String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InternshipPosition> internshipPositions;


}