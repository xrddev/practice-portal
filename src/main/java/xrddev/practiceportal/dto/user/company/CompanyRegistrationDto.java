package xrddev.practiceportal.dto.user.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xrddev.practiceportal.dto.user.AbstractUserRegistrationDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyRegistrationDto extends AbstractUserRegistrationDto {

    @NotBlank(message = "Company name cannot be null!")
    @Size(max = 150, message = "Company name can be up to 150 characters.")
    private String companyName;

    @Pattern(
            regexp = "^[^,]+\\s+\\d+,\\s+[^,]+,\\s*\\d{5}$",
            message = "Address must be in the format: Street Number, City, Postal Code"
    )
    @NotBlank(message = "Address cannot be null!")
    @Size(max = 200, message = "Address can be up to 200 characters.")
    private String address;

    @NotBlank(message = "Phone cannot be null!")
    @Pattern(regexp = "^\\+?[0-9 ]{7,15}$",
             message = "Phone number must be valid (7-15 digits, optional '+').")
    private String phone;

    @Size(max = 100, message = "Website can be up to 100 characters.")
    @Pattern(
        regexp = "^(https?://)?([\\w\\-]+\\.)+[a-zA-Z]{2,}(/\\S*)?$|^$",
        message = "Website must be a valid URL format.")
    private String website;

    @NotBlank(message = "Internship coordinator cannot be null!")
    @Size(max = 100, message = "Internship coordinator's name can be up to 100 characters.")
    private String internshipCoordinator;

    @NotBlank(message = "Internship coordinator email cannot be null!")
    @Email(message = "Internship coordinator email must be valid.")
    @Size(max = 100, message = "Internship coordinator email can be up to 100 characters.")
    private String internshipCoordinatorEmail;
}