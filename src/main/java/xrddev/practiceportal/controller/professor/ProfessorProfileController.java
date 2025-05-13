package xrddev.practiceportal.controller.professor;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.dto.user.professor.ProfessorEditDto;
import xrddev.practiceportal.service.api.ProfessorService;

import java.security.Principal;

@Controller
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorProfileController {

    private final ProfessorService professorService;

    @GetMapping("/edit-profile")
    public String editProfile(Model model, Principal principal) {
        model.addAttribute("professor", professorService.getByEmailMappedToEditDto(principal.getName()));
        return "professor/edit_profile";
    }


    @PostMapping("/edit-profile")
    public String updateProfile(
            @Valid @ModelAttribute("professor") ProfessorEditDto professorEditDto,
            BindingResult bindingResult,
            Principal principal) {

        if (bindingResult.hasErrors())
            return "professor/edit_profile";

        professorService.updateProfessor(professorEditDto, principal.getName());
        return "redirect:/professor/dashboard";
    }
}
