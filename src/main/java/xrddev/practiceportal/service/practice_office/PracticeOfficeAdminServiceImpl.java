package xrddev.practiceportal.service.practice_office;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.practice_office.PracticeOfficeAdminDto;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.model.practice_office.PracticeOfficeAdmin;
import xrddev.practiceportal.repository.PracticeOfficeAdminRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PracticeOfficeAdminServiceImpl implements PracticeOfficeAdminService {

    private final PracticeOfficeAdminRepository practiceOfficeAdminRepository;


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