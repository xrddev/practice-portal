package xrddev.practiceportal.controller.professor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.dto.internship_evaluations.ProfessorInternshipEvaluationDashboardDto;
import xrddev.practiceportal.service.internship_assigment.InternshipAssignmentService;
import xrddev.practiceportal.service.professor.ProfessorService;

import java.security.Principal;

@Controller
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorDashboardController {

  private final ProfessorService professorService;
  private final InternshipAssignmentService internshipAssignmentService;

  @GetMapping("/dashboard")
  public String viewDashboard(Model model, Principal principal) {
    model.addAttribute("professor", professorService.getByEmailMappedToDashboardDto(principal.getName()));
    model.addAttribute("matchedAssignments", internshipAssignmentService.getAllByProfessorEmailMappedToDashboardDto(principal.getName()));
    return "professor/dashboard";
  }

  @GetMapping("/evaluate/{assignmentId}")
  public String showEvaluationForm(@PathVariable Long assignmentId,
                                   @RequestParam Long studentId,
                                   Principal principal,
                                   Model model) {
    model.addAttribute("assignment",
            internshipAssignmentService.getByAssigmentIDAndStudentIDAndProfessorEmailMappedToDashboardDto(assignmentId, studentId, principal.getName()));
    model.addAttribute("evaluation", new ProfessorInternshipEvaluationDashboardDto());
    return "professor/evaluation";
  }


  @PostMapping("/evaluation")
  public String submitEvaluation(
          @ModelAttribute("evaluation") ProfessorInternshipEvaluationDashboardDto evaluationDto,
          @RequestParam("assignmentId") Long assignmentId,
          @RequestParam("studentId") Long studentId,
          Principal principal) {

    internshipAssignmentService.saveProfessorEvaluation(assignmentId, studentId, principal.getName(), evaluationDto);
    return "redirect:/professor/dashboard";
  }

}
