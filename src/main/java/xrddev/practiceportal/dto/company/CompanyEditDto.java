package xrddev.practiceportal.dto.company;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.company.Company;

@NoArgsConstructor
@Data
public class CompanyEditDto {

    @NotNull(message = "Company name cannot be null!")
    @Size(max = 150, message = "Company name can be up to 150 characters.")
    private String companyName;

    @Pattern(
            regexp = "^[^,]+\\s+\\d+,\\s+[^,]+,\\s*\\d{5}$",
            message = "Address must be in the format: Street Number, City, Postal Code"
    )
    @NotNull(message = "Address cannot be null!")
    @Size(max = 200, message = "Address can be up to 200 characters.")
    private String address;

    @NotNull(message = "Phone cannot be null!")
    @Pattern(regexp = "^\\+?[0-9 ]{7,15}$", message = "Phone number must be valid (7-15 digits, optional +).")
    private String phone;

    @Size(max = 100, message = "Website can be up to 100 characters.")
    @Pattern(
            regexp = "^(https?://)?([\\w\\-]+\\.)+[a-zA-Z]{2,}(/\\S*)?$|^$",
            message = "Website must be a valid URL format.")
    private String website;

    @NotNull(message = "Internship coordinator cannot be null!")
    @Size(max = 100, message = "Internship coordinator's name can be up to 100 characters.")
    private String internshipCoordinator;

    @NotNull(message = "Internship coordinator email cannot be null!")
    @Email(message = "Internship coordinator email must be valid.")
    @Size(max = 100, message = "Internship coordinator email can be up to 100 characters.")
    private String internshipCoordinatorEmail;

    public CompanyEditDto(Company company) {
        this.companyName = company.getCompanyName();
        this.address = company.getAddress();
        this.phone = company.getPhone();
        this.website = company.getWebsite();
        this.internshipCoordinator = company.getInternshipCoordinator();
        this.internshipCoordinatorEmail = company.getInternshipCoordinatorEmail();
    }
}
