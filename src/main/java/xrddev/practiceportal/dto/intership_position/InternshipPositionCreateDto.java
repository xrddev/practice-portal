package xrddev.practiceportal.dto.intership_position;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;

import java.lang.annotation.*;
import java.time.LocalDate;
import java.util.List;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

/**
 * Custom annotation to check that endDate ≥ startDate.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
@Documented
@interface DateRange {
    String message() default "End date must be on or after start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String startField();
    String endField();
}

class DateRangeValidator implements ConstraintValidator<DateRange, InternshipPositionCreateDto> {
    public void initialize(DateRange ann) {
        String startField = ann.startField();
        String endField = ann.endField();
    }
    public boolean isValid(InternshipPositionCreateDto dto, ConstraintValidatorContext ctx) {
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            return true; // let @NotNull handle the error
        }
        return !dto.getEndDate().isBefore(dto.getStartDate());
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@DateRange(startField = "startDate", endField = "endDate",
        message = "End date must be the same as or after the start date")
public class InternshipPositionCreateDto {

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Maximum 150 characters allowed")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Maximum 500 characters allowed")
    private String description;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @NotEmpty(message = "Please select at least one skill")
    private List<Skills> skills;

    @NotEmpty(message = "Please select at least one interest")
    private List<Interests> interests;
}
