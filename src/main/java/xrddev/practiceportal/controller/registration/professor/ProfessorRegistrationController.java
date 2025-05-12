package xrddev.practiceportal.controller.registration.professor;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.controller.registration.common.RegistrationSessionHelper;
import xrddev.practiceportal.dto.user.professor.ProfessorRegistrationDto;
import xrddev.practiceportal.service.api.ProfessorService;

@Controller
@RequestMapping("/public/register/professor")
public class ProfessorRegistrationController extends RegistrationSessionHelper {

    private final ProfessorService professorService;

    public ProfessorRegistrationController( ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String showProfessorRegistrationForm(Model model) {
        model.addAttribute("professor", new ProfessorRegistrationDto());
        return "register/professor_registration";
    }

    @PostMapping
    public String handleProfessorRegistration(
            @ModelAttribute("professor") @Valid ProfessorRegistrationDto professorRegistrationDto,
            BindingResult bindingResult,
            HttpSession session) {

        if (bindingResult.hasErrors())
            return "register/professor_registration";

        professorRegistrationDto.setEmail(super.getEmail(session));
        professorRegistrationDto.setPassword(super.getPassword(session));
        professorService.registerProfessor(professorRegistrationDto);
        super.clearSession(session);
        return "redirect:/public/register/success";
    }
}