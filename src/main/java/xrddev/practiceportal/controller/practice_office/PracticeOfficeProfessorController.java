package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.config.ModelAttributeKeys;
import xrddev.practiceportal.service.api.ProfessorService;

@Controller
@RequestMapping("/practice-office/professors")
@RequiredArgsConstructor
public class PracticeOfficeProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public String showAllProfessors(Model model) {
        //model.addAttribute("professors", professorService.getAllMappedToDto());
        return "practice_office/professors";
    }

    @PostMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorService.deleteById(id);
        return "redirect:/practice-office/professors";
    }
}
