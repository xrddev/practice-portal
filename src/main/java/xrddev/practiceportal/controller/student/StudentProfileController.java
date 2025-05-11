package xrddev.practiceportal.controller.student;

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
import xrddev.practiceportal.dto.student.StudentDto;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.service.api.StudentService;

import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentService studentService;

    @GetMapping("/edit-profile")
    public String editProfile(Model model, Principal principal) {
        var student = studentService
                .findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        model.addAttribute("STUDENT_DTO", new StudentDto(student));
        model.addAttribute("INTERESTS", Arrays.asList(Interests.values()));
        model.addAttribute("SKILLS", Arrays.asList(Skills.values()));
        model.addAttribute("DEPARTMENTS", Arrays.asList(Department.values()));
        return "student/edit_profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(
            @Valid @ModelAttribute("STUDENT_DTO") StudentDto dto,
            BindingResult bindingResult,
            Principal principal,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("STUDENT_DTO", dto);
            model.addAttribute("INTERESTS", Arrays.asList(Interests.values()));
            model.addAttribute("SKILLS", Arrays.asList(Skills.values()));
            model.addAttribute("DEPARTMENTS", Arrays.asList(Department.values()));
            return "student/edit_profile";
        }

        dto.setEmail(principal.getName());
        studentService.updateStudent(dto, principal.getName());
        redirectAttributes.addFlashAttribute("profileUpdated", true);
        return "redirect:/student/dashboard";
    }
}