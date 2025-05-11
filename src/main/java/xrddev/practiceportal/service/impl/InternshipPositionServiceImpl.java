package xrddev.practiceportal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDashboardDto;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.model.user.Company;
import xrddev.practiceportal.repository.api.InternshipPositionRepository;
import xrddev.practiceportal.service.api.CompanyService;
import xrddev.practiceportal.service.api.InternshipPositionService;

import java.util.List;

@Service
public class InternshipPositionServiceImpl implements InternshipPositionService {

    private final InternshipPositionRepository positionRepository;
    private final CompanyService companyService;

    public InternshipPositionServiceImpl(InternshipPositionRepository positionRepo,
                                         CompanyService companyService) {
        this.positionRepository = positionRepo;
        this.companyService = companyService;
    }

    @Override
    @Transactional
    public void createPosition(InternshipPositionCreateDto dto) {
        InternshipPosition internshipPosition = new InternshipPosition();
        internshipPosition.setTitle(dto.getTitle());
        internshipPosition.setDescription(dto.getDescription());
        internshipPosition.setStartDate(dto.getStartDate());
        internshipPosition.setEndDate(dto.getEndDate());
        internshipPosition.setSkills(dto.getSkills());
        internshipPosition.setInterests(dto.getInterests());
        internshipPosition.setAvailable(true);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();

        Company company = companyService.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Company not found: " + email));

        internshipPosition.setCompany(company);
        positionRepository.save(internshipPosition);
    }

    @Override
    public List<InternshipPosition> getByCompanyEmail(String email) {
        return positionRepository.findAllByCompanyEmail(email);
    }

    @Override
    public void deleteByIdAndCompanyEmail(Long id, String companyEmail) {
        InternshipPosition position = positionRepository
            .findByIdAndCompanyEmail(id, companyEmail)
            .orElseThrow(() -> new EntityNotFoundException("Position not found or not yours"));
        positionRepository.delete(position);
    }

    @Override
    public InternshipPosition getByIdAndCompanyEmail(Long id, String companyEmail) {
        return positionRepository.findByIdAndCompanyEmail(id, companyEmail)
                .orElseThrow(() -> new EntityNotFoundException("Position not found or not yours"));
    }

    @Override
    @Transactional
    public void updatePosition(Long id, InternshipPositionDashboardDto dto, String companyEmail) {
        InternshipPosition position = positionRepository.findByIdAndCompanyEmail(id, companyEmail)
                .orElseThrow(() -> new EntityNotFoundException("Position not found or not yours"));

        position.setTitle(dto.getTitle());
        position.setDescription(dto.getDescription());
        position.setStartDate(dto.getStartDate());
        position.setEndDate(dto.getEndDate());
        position.setSkills(dto.getSkills());
        position.setInterests(dto.getInterests());

        positionRepository.save(position);
    }

    @Override
    public long count(){
        return positionRepository.count();
    }
}