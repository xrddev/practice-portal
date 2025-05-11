package xrddev.practiceportal.controller.professor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.dto.professor.ProfessorDashboardDto;
import xrddev.practiceportal.service.api.ProfessorService;

import java.security.Principal;

@Controller
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorDashboardController {

  private final ProfessorService professorService;

  @GetMapping("/dashboard")
  public String viewDashboard(Model model, Principal principal) {
    var professorDto = professorService
            .findByEmail(principal.getName())
            .map(ProfessorDashboardDto::new)
            .orElseThrow(() -> new RuntimeException("Professor not found"));
    model.addAttribute("PROFESSOR_DASHBOARD_DTO", professorDto);
    return "professor/dashboard";
  }
}
