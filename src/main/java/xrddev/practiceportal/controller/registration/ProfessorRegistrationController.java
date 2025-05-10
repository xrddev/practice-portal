package xrddev.practiceportal.controller.registration;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.config.ModelAttributes;
import xrddev.practiceportal.config.SessionAttribute;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.service.api.ProfessorService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/public/register/professor")
public class ProfessorRegistrationController {

    private final ProfessorService professorService;

    public ProfessorRegistrationController( ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String showProfessorRegistrationForm(Model model) {
        List<String> interests = Arrays.stream(Interests.values()).map(Enum::name).toList();
        model.addAttribute(ModelAttributes.INTERESTS, interests);
        return "register/professor_registration";
    }

    @PostMapping
    public String handleProfessorRegistration(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam List<String> interests,
            HttpSession session) {

        professorService.registerProfessor(
                (String) session.getAttribute(SessionAttribute.EMAIL),
                (String) session.getAttribute(SessionAttribute.PASSWORD),
                firstName,
                lastName,
                interests);

        session.removeAttribute(SessionAttribute.EMAIL);
        session.removeAttribute(SessionAttribute.PASSWORD);
        return "redirect:/public/register/success";
    }
}