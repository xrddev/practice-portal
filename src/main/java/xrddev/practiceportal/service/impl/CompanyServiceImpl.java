package xrddev.practiceportal.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.user.company.CompanyDashboardDto;
import xrddev.practiceportal.dto.user.company.CompanyRegistrationDto;
import xrddev.practiceportal.model.user.Company;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.repository.api.CompanyRepository;
import xrddev.practiceportal.service.api.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public void registerCompany(CompanyRegistrationDto companyRegistrationDto) {
        Company company = new Company();

        company.setEmail(companyRegistrationDto.getEmail());
        company.setPassword(companyRegistrationDto.getPassword());
        company.setRole(UserRole.COMPANY);

        company.setCompanyName(companyRegistrationDto.getCompanyName());
        company.setAddress(companyRegistrationDto.getAddress());
        company.setPhone(companyRegistrationDto.getPhone());
        company.setWebsite(companyRegistrationDto.getWebsite());
        company.setInternshipCoordinator(companyRegistrationDto.getInternshipCoordinator());
        company.setInternshipCoordinatorEmail(companyRegistrationDto.getInternshipCoordinatorEmail());

        companyRepository.save(company);
    }


    @Override
    public Optional<Company> findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    @Override
    public CompanyDashboardDto getByEmailMappedToDto(String email) {
        return companyRepository.findByEmail(email)
                .map(CompanyDashboardDto::new)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }



    @Override
    @Transactional
    public void updateCompany(CompanyDashboardDto dto, String email) {
        Company company = companyRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setCompanyName(dto.getCompanyName());
        company.setAddress(dto.getAddress());
        company.setPhone(dto.getPhone());
        company.setWebsite(dto.getWebsite());
        company.setInternshipCoordinator(dto.getInternshipCoordinator());
        company.setInternshipCoordinatorEmail(dto.getInternshipCoordinatorEmail());
        companyRepository.save(company);
    }

    @Override
    public long count(){
        return companyRepository.count();
    }

    @Override
    public List<CompanyDashboardDto> getAllMappedToDto() {
        return companyRepository.findAll()
                .stream()
                .map(CompanyDashboardDto::new)
                .toList();
    }

    @Override
    public void deleteByEmail(String email) {
        var company = companyRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Company not found with email: " + email));
        companyRepository.delete(company);
    }

}