package xrddev.practiceportal.controller.company;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionEditDto;
import xrddev.practiceportal.service.internship.InternshipPositionService;


import java.security.Principal;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyPositionController {

    private final InternshipPositionService internshipPositionService;


    @GetMapping("/new-position")
    public String showCreateForm(Model model) {
        model.addAttribute("position", new InternshipPositionCreateDto());
        return "company/new_position";
    }


    @PostMapping("/new-position")
    public String handleCreateForm(
            @Valid @ModelAttribute("position") InternshipPositionCreateDto positionDto,
            BindingResult bindingResult,
            Principal principal) {

        if (bindingResult.hasErrors()) {
            return "company/new_position";
        }
        internshipPositionService.createPosition(positionDto, principal.getName());
        return "redirect:/company/dashboard";
    }



    @PostMapping("/positions/delete/{id}")
    public String deletePosition(@PathVariable Long id, Principal principal) {
        internshipPositionService.deleteByIdAndCompanyEmail(id, principal.getName());
        return "redirect:/company/dashboard";
    }



    @GetMapping("/positions/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("position", internshipPositionService.getByIdAndCompanyEmailMappedToEditDto(id, principal.getName()));
        return "company/edit_position";
    }


    @PostMapping("/positions/edit")
    public String handleEditForm(
            @Valid @ModelAttribute("position") InternshipPositionEditDto dto,
            BindingResult bindingResult,
            Principal principal) {

        if (bindingResult.hasErrors()) {
            return "company/edit_position";
        }
        internshipPositionService.updatePosition(dto.getId(), principal.getName(), dto);
        return "redirect:/company/dashboard";
    }


}