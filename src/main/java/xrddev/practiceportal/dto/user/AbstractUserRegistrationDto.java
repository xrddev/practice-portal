package xrddev.practiceportal.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class AbstractUserRegistrationDto {

    private String email;

    private String password;
}
