package xrddev.practiceportal.dto.user.practice_office;

import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.user.PracticeOfficeAdmin;

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