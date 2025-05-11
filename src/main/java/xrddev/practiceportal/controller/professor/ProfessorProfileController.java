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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xrddev.practiceportal.dto.professor.ProfessorDashboardDto;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.service.api.ProfessorService;

import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorProfileController {

    private final ProfessorService professorService;

    @GetMapping("/edit-profile")
    public String editProfile(Model model, Principal principal) {
        var professor = professorService
                .findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        model.addAttribute("PROFESSOR_DASHBOARD_DTO", new ProfessorDashboardDto(professor));
        model.addAttribute("INTERESTS", Arrays.asList(Interests.values()));
        return "professor/edit_profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(
            @Valid @ModelAttribute("PROFESSOR_DASHBOARD_DTO") ProfessorDashboardDto dto,
            BindingResult bindingResult,
            Principal principal,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("PROFESSOR_DASHBOARD_DTO", dto);
            model.addAttribute("INTERESTS", Arrays.asList(Interests.values()));
            return "professor/edit_profile";
        }

        dto.setEmail(principal.getName());
        professorService.updateProfessor(dto, principal.getName());
        redirectAttributes.addFlashAttribute("profileUpdated", true);
        return "redirect:/professor/dashboard";
    }
}
