package xrddev.practiceportal.service.impl;

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

    private final PracticeOfficeAdminRepository repository;

    public PracticeOfficeAdminServiceImpl(PracticeOfficeAdminRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void registerAdmin(PracticeOfficeAdminDto dto) {
        PracticeOfficeAdmin admin = new PracticeOfficeAdmin();
        admin.setRole(UserRole.PRACTICE_OFFICE);
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        repository.save(admin);
    }

    @Override
    public Optional<PracticeOfficeAdmin> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}