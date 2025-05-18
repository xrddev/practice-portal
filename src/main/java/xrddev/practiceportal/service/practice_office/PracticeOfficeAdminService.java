// src/main/java/xrddev/practiceportal/service/api/PracticeOfficeAdminService.java
package xrddev.practiceportal.service.practice_office;

import xrddev.practiceportal.dto.practice_office.PracticeOfficeAdminDto;
import xrddev.practiceportal.model.practice_office.PracticeOfficeAdmin;

import java.util.Optional;

public interface PracticeOfficeAdminService {
    void registerAdmin(PracticeOfficeAdminDto dto);
    Optional<PracticeOfficeAdmin> findByEmail(String email);

    PracticeOfficeAdminDto getByEmailMappedToDto(String email);

}