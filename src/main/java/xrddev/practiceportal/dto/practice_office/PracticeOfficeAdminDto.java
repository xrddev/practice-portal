package xrddev.practiceportal.dto.practice_office;

import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.practice_office.PracticeOfficeAdmin;

@NoArgsConstructor
@Data
public class PracticeOfficeAdminDto {

    private Long id;
    private String email;
    private String password;

    public PracticeOfficeAdminDto(PracticeOfficeAdmin admin) {
        this.id       = admin.getId();
        this.email    = admin.getEmail();
        this.password = admin.getPassword();
    }
}