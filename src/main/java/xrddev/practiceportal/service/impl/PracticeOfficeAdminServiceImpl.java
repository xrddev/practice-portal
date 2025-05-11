package xrddev.practiceportal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.practice_office.PracticeOfficeAdminDto;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.model.user.PracticeOfficeAdmin;
import xrddev.practiceportal.repository.api.PracticeOfficeAdminRepository;
import xrddev.practiceportal.service.api.PracticeOfficeAdminService;

import java.util.Optional;

@Service
public class PracticeOfficeAdminServiceImpl implements PracticeOfficeAdminService {

    private final PracticeOfficeAdminRepository practiceOfficeAdminRepository;

    public PracticeOfficeAdminServiceImpl(PracticeOfficeAdminRepository practiceOfficeAdminRepository) {
        this.practiceOfficeAdminRepository = practiceOfficeAdminRepository;
    }

    @Override
    @Transactional
    public void registerAdmin(PracticeOfficeAdminDto dto) {
        PracticeOfficeAdmin admin = new PracticeOfficeAdmin();
        admin.setRole(UserRole.PRACTICE_OFFICE);
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        practiceOfficeAdminRepository.save(admin);
    }

    @Override
    public Optional<PracticeOfficeAdmin> findByEmail(String email) {
        return practiceOfficeAdminRepository.findByEmail(email);
    }

    @Override
    public PracticeOfficeAdminDto getByEmailMappedToDto(String email) {
        return practiceOfficeAdminRepository.findByEmail(email)
                .map(PracticeOfficeAdminDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
    }
}