package xrddev.practiceportal.dto.company;

import lombok.Data;
import xrddev.practiceportal.model.user.Company;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDto;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CompanyDashboardDto {
    private String companyName;
    private String email;
    private String address;
    private List<InternshipPositionDto> positions;

    // Μετατροπή Company → CompanyDashboardDto
    public CompanyDashboardDto(Company company) {
        this.companyName = company.getCompanyName();
        this.email = company.getEmail();
        this.address = company.getAddress();
        this.positions = company.getInternshipPositions()
                .stream()
                .map(InternshipPositionDto::new)
                .collect(Collectors.toList());
    }
}