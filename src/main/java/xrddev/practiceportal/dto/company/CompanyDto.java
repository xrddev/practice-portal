package xrddev.practiceportal.dto.company;

import lombok.Data;
import xrddev.practiceportal.model.user.Company;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDto;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CompanyDto {
    private Long id;
    private String companyName;
    private String address;
    private String email;
    private String phone;
    private String website;
    private String internshipCoordinator;
    private String internshipCoordinatorEmail;
    private List<InternshipPositionDto> internshipPositions;

    // Μετατροπή Company → CompanyDto
    public CompanyDto(Company company) {
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.email = company.getEmail();
        this.address = company.getAddress();
        this.phone = company.getPhone();
        this.website = company.getWebsite();
        this.internshipCoordinator = company.getInternshipCoordinator();
        this.internshipCoordinatorEmail = company.getInternshipCoordinatorEmail();
        this.internshipPositions = company.getInternshipPositions()
                .stream()
                .map(InternshipPositionDto::new)
                .collect(Collectors.toList());
    }
}