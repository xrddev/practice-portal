package xrddev.practiceportal.controller.registration.professor;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.config.ModelAttributeKeys;
import xrddev.practiceportal.controller.registration.common.RegistrationSessionHelper;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.service.api.ProfessorService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/public/register/professor")
public class ProfessorRegistrationController extends RegistrationSessionHelper {

    private final ProfessorService professorService;

    public ProfessorRegistrationController( ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String showProfessorRegistrationForm() {
        return "register/professor_registration";
    }

    @PostMapping
    public String handleProfessorRegistration(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam("department") Department department,
            @RequestParam List<String> interests,
            HttpSession session) {

        professorService.registerProfessor(
                super.getEmail(session),
                super.getPassword(session),
                firstName,
                lastName,
                department,
                interests);

        super.clearSession(session);
        return "redirect:/public/register/success";
    }

}