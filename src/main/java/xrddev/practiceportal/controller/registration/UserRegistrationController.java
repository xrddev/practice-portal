package xrddev.practiceportal.controller.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.config.ModelAttributes;
import xrddev.practiceportal.config.SessionAttribute;
import jakarta.servlet.http.HttpSession;
import xrddev.practiceportal.model.enums.UserRole;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/public/register")
public class UserRegistrationController {

    private final BCryptPasswordEncoder passwordEncoder;

    public UserRegistrationController() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        List<String> roles = Arrays.stream(UserRole.values()).map(Enum::name).toList();
        model.addAttribute(ModelAttributes.ROLES, roles);
        return "register/user_register";
    }

    @PostMapping
    public String handleUserRegistration(
            @RequestParam @NotNull @Size(min = 6, max = 64) String password,
            @RequestParam @NotNull @Email String email,
            @RequestParam @NotNull UserRole role,
            HttpSession session) {

        session.setAttribute(SessionAttribute.PASSWORD, passwordEncoder.encode(password));
        session.setAttribute(SessionAttribute.EMAIL, email);

        return switch (role) {
            case STUDENT -> "redirect:/public/register/student";
            case PROFESSOR -> "redirect:/public/register/professor";
            case COMPANY -> "redirect:/public/register/company";
            default -> throw new IllegalStateException("Unexpected value: " + role);
        };
    }
}