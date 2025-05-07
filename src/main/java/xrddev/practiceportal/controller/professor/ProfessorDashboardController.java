package xrddev.practiceportal.controller.professor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDto;
import xrddev.practiceportal.model.Professor;
import xrddev.practiceportal.service.ProfessorService;
import xrddev.practiceportal.dto.professor.ProfessorDashboardDto;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ProfessorDashboardController {

  private final ProfessorService professorService;

  @GetMapping("/professor/dashboard")
  public String showDashboard(@AuthenticationPrincipal User principal, Model model) {
    Professor professor =
            professorService.findByEmail(principal.getUsername())
                    .orElseThrow(() -> new RuntimeException("Professor not found"));


    List<InternshipPositionDto> supervisedPositions = professor
                    .getSupervisedPositions()
                    .stream()
                    .map(InternshipPositionDto::new)
                    .toList();

    model.addAttribute("positions", supervisedPositions);
    model.addAttribute("professor", new ProfessorDashboardDto(professor));
    return "professor/dashboard";
  }

}