package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xrddev.practiceportal.service.api.InternshipPositionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/practice-office/internships")
public class PracticeOfficeInternshipController {

    private final InternshipPositionService internshipPositionService;

    @GetMapping
    public String manageInternships(Model model) {
        return "practice_office/internships";
    }

    @PostMapping("/delete/{id}")
    public String deletePosition(@PathVariable Long id) {
        internshipPositionService.deleteById(id);
        return "redirect:/practice-office/internships";
    }
}
