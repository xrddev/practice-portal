package xrddev.practiceportal.service;

import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDto;
import xrddev.practiceportal.model.Company;
import xrddev.practiceportal.model.InternshipPosition;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.repository.InternshipPositionRepository;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternshipPositionService {

    private final InternshipPositionRepository positionRepository;
    private final CompanyService companyService;

    public InternshipPositionService(InternshipPositionRepository positionRepo,
                                     CompanyService companyService) {
        this.positionRepository = positionRepo;
        this.companyService = companyService;
    }

    @Transactional
    public void createPosition(InternshipPositionCreateDto dto) {
        InternshipPosition internshipPosition = new InternshipPosition();

        internshipPosition.setTitle(dto.getTitle());
        internshipPosition.setDescription(dto.getDescription());
        internshipPosition.setStartDate(Date.from(dto.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        internshipPosition.setEndDate(Date.from(dto.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        internshipPosition.setSkills(dto.getSkills());
        internshipPosition.setInterests(dto.getInterests());
        internshipPosition.setAvailable(true);


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();

        Company company = companyService.findByEmail(email)
            .orElseThrow(() ->
               new IllegalStateException("Company not found: " + email)
            );

        internshipPosition.setCompany(company);
        positionRepository.save(internshipPosition);
    }

    public List<InternshipPosition> getByCompanyEmail(String email) {
        return positionRepository.findAllByCompanyEmail(email);
    }

}