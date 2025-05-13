package xrddev.practiceportal.dto.user.company;

import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.user.Company;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class CompanyDashboardDto {
    private String companyName;
    private String email;
    private String address;
    private String phone;
    private String website;
    private String internshipCoordinator;
    private String internshipCoordinatorEmail;

    public CompanyDashboardDto(Company company) {
        this.companyName = company.getCompanyName();
        this.email = company.getEmail();
        this.address = company.getAddress();
        this.phone = company.getPhone();
        this.website = company.getWebsite();
        this.internshipCoordinator = company.getInternshipCoordinator();
        this.internshipCoordinatorEmail = company.getInternshipCoordinatorEmail();
    }
}