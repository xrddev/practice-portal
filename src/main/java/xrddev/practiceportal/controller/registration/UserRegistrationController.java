package xrddev.practiceportal.controller.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.config.SessionKeys;
import xrddev.practiceportal.model.enums.UserRole;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/public/register")
@SessionAttributes({
        SessionKeys.PASSWORD,
        SessionKeys.EMAIL,
        SessionKeys.ROLES,
})
public class UserRegistrationController {

    private final BCryptPasswordEncoder passwordEncoder;

    public UserRegistrationController() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        List<String> roles = Arrays.stream(UserRole.values()).map(Enum::name).toList();
        model.addAttribute(SessionKeys.ROLES, roles);
        return "register/user_register";
    }

    @PostMapping
    public String handleUserRegistration(
            @RequestParam @NotNull @Size(min = 6, max = 64) String password,
            @RequestParam @NotNull @Email String email,
            @RequestParam @NotNull UserRole role,
            Model model) {

        model.addAttribute(SessionKeys.PASSWORD, passwordEncoder.encode(password));
        model.addAttribute(SessionKeys.EMAIL, email);

        return switch (role) {
            case STUDENT -> "redirect:/public/register/student";
            case PROFESSOR -> "redirect:/public/register/professor";
            case COMPANY -> "redirect:/public/register/company";
        };
    }
}