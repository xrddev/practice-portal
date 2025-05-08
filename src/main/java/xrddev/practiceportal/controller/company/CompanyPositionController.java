package xrddev.practiceportal.controller.company;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import xrddev.practiceportal.config.ModelAttributes;
import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.service.InternshipPositionService;

import java.util.Arrays;

@Controller
public class CompanyPositionController {

    private final InternshipPositionService internshipPositionService;

    public CompanyPositionController(InternshipPositionService internshipPositionService) {
        this.internshipPositionService = internshipPositionService;
    }


    @GetMapping("/company/new-position")
    public String showCreateForm(Model model) {
        model.addAttribute(ModelAttributes.INTERNSHIP_POSITION_CREATE_DTO, new InternshipPositionCreateDto());
        model.addAttribute(ModelAttributes.SKILLS, Arrays.stream(Skills.values()).toList());
        model.addAttribute(ModelAttributes.INTERESTS, Arrays.stream(Interests.values()).toList());
        return "company/new_position_form";
    }


    @PostMapping("/company/new-position")
    public String handleCreateForm(
            @Valid @ModelAttribute(ModelAttributes.INTERNSHIP_POSITION_CREATE_DTO)
            InternshipPositionCreateDto dto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelAttributes.SKILLS,
                    Arrays.stream(Skills.values()).toList());
            model.addAttribute(ModelAttributes.INTERESTS,
                    Arrays.stream(Interests.values()).toList());
            return "company/new_position_form";
        }

        internshipPositionService.createPosition(dto);
        return "redirect:/company/dashboard";
    }
}