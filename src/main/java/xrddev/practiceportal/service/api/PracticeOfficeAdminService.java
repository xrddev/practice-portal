// src/main/java/xrddev/practiceportal/service/api/PracticeOfficeAdminService.java
package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.user.practice_office.PracticeOfficeAdminDto;
import xrddev.practiceportal.model.user.PracticeOfficeAdmin;

import java.util.Optional;

public interface PracticeOfficeAdminService {
    void registerAdmin(PracticeOfficeAdminDto dto);
    Optional<PracticeOfficeAdmin> findByEmail(String email);

    PracticeOfficeAdminDto getByEmailMappedToDto(String email);

}