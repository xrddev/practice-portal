package xrddev.practiceportal.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xrddev.practiceportal.model.User;
import xrddev.practiceportal.repository.UserRepository;

@Controller
@RequestMapping("/public/register")
public class UserRegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showRegistrationForm() {
        return "register/user_register";
    }

    @PostMapping
    public String registerUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            RedirectAttributes redirectAttributes) {

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);
        redirectAttributes.addAttribute("userId", user.getId());

        return switch (role.toUpperCase()) {
            case "STUDENT" -> "redirect:/public/register/student";
            case "PROFESSOR" -> "redirect:/public/register/professor";
            case "COMPANY" -> "redirect:/public/register/company";
            default -> {
                System.out.println("[ERROR] Unknown role: " + role);
                yield "redirect:/error";
            }
        };
    }
}