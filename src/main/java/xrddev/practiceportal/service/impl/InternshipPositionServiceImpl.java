package xrddev.practiceportal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDashboardDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionEditDto;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.model.user.Company;
import xrddev.practiceportal.repository.api.InternshipPositionRepository;
import xrddev.practiceportal.service.api.CompanyService;
import xrddev.practiceportal.service.api.InternshipPositionService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InternshipPositionServiceImpl implements InternshipPositionService {

    private final InternshipPositionRepository internshipPositionRepository;
    private final CompanyService companyService;


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
        internshipPositionRepository.save(internshipPosition);
    }


    @Override
    public void updatePosition(InternshipPositionEditDto dto, Long id) {
        InternshipPosition position = internshipPositionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Position not found"));

        position.setTitle(dto.getTitle());
        position.setDescription(dto.getDescription());
        position.setStartDate(dto.getStartDate());
        position.setEndDate(dto.getEndDate());
        position.setSkills(dto.getSkills());
        position.setInterests(dto.getInterests());
        internshipPositionRepository.save(position);
    }


    @Override
    public void deleteByIdAndCompanyEmail(Long id, String companyEmail) {
        InternshipPosition position = internshipPositionRepository
            .findByIdAndCompanyEmail(id, companyEmail)
            .orElseThrow(() -> new EntityNotFoundException("Position not found or not yours"));
        internshipPositionRepository.delete(position);
    }



    @Override
    public long count(){
        return internshipPositionRepository.count();
    }


    @Override
    public InternshipPositionEditDto getByIdAndCompanyEmailMappedToEditDto(Long id, String email) {
        return internshipPositionRepository
                .findByIdAndCompanyEmail(id, email)
                .map(InternshipPositionEditDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Not found or not allowed"));
    }

    @Override
    public void deleteById(Long id){
        internshipPositionRepository.deleteById(id);
    }

    @Override
    public InternshipPositionEditDto getByIdMappedToEditDto(Long id) {
        return internshipPositionRepository.findById(id)
                .map(InternshipPositionEditDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Position not found"));
    }

    @Override
    public List<InternshipPositionDashboardDto> getAllByCompanyEmailMappedToDashboardDto(String email){
        return internshipPositionRepository.findAllByCompanyEmail(email)
                .stream()
                .map(InternshipPositionDashboardDto::new)
                .toList();
    }
}