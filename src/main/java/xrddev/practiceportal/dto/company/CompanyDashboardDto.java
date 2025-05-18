package xrddev.practiceportal.dto.company;

import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.company.Company;

@NoArgsConstructor
@Data
public class CompanyDashboardDto {
    private long id;
    private String companyName;
    private String email;
    private String address;
    private String phone;
    private String website;
    private String internshipCoordinator;
    private String internshipCoordinatorEmail;

    public CompanyDashboardDto(Company company) {
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.email = company.getEmail();
        this.address = company.getAddress();
        this.phone = company.getPhone();
        this.website = company.getWebsite();
        this.internshipCoordinator = company.getInternshipCoordinator();
        this.internshipCoordinatorEmail = company.getInternshipCoordinatorEmail();
    }
}