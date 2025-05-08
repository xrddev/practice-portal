package xrddev.practiceportal.dto.company;

import xrddev.practiceportal.dto.intership_position.InternshipPositionDto;
import xrddev.practiceportal.model.Company;

import java.util.List;
import java.util.stream.Collectors;

public record CompanyDashboardDto(
    Long id,
    String name,
    String email,
    String address,
    List<InternshipPositionDto> positions
) {
    public CompanyDashboardDto(Company company) {
        this(
            company.getId(),
            company.getCompanyName(),
            company.getEmail(),
            company.getAddress(),
            company.getInternshipPositions()
                   .stream()
                   .map(InternshipPositionDto::new)
                   .collect(Collectors.toList())
        );
    }
}