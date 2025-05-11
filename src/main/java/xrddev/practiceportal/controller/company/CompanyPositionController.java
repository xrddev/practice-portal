package xrddev.practiceportal.controller.company;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xrddev.practiceportal.config.ModelAttributeKeys;
import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDto;
import xrddev.practiceportal.service.api.InternshipPositionService;

import java.security.Principal;

@Controller
@RequestMapping("/company")
public class CompanyPositionController {

    private final InternshipPositionService internshipPositionService;

    public CompanyPositionController(InternshipPositionService internshipPositionService) {
        this.internshipPositionService = internshipPositionService;
    }

    @GetMapping("/new-position")
    public String showCreateForm(Model model) {
        model.addAttribute(ModelAttributeKeys.INTERNSHIP_POSITION_CREATE_DTO, new InternshipPositionCreateDto());
        return "company/new_position";
    }


    @PostMapping("/new-position")
    public String handleCreateForm(
            @Valid @ModelAttribute(ModelAttributeKeys.INTERNSHIP_POSITION_CREATE_DTO) InternshipPositionCreateDto dto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors())
            return "company/new_position";

        internshipPositionService.createPosition(dto);
        redirectAttributes.addFlashAttribute("positionCreated", true);
        return "redirect:/company/dashboard";
    }


    @PostMapping("/positions/delete/{id}")
    public String deletePosition(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        String companyEmail = principal.getName();
        internshipPositionService.deleteByIdAndCompanyEmail(id, companyEmail);
        redirectAttributes.addFlashAttribute("positionDeleted", true);
        return "redirect:/company/dashboard";
    }


    @GetMapping("/positions/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, Principal principal) {
        String companyEmail = principal.getName();

        model.addAttribute(ModelAttributeKeys.INTERNSHIP_POSITION_DASHBOARD_DTO,
                internshipPositionService.getByIdAndCompanyEmailMappedToDto(id, companyEmail));

        return "company/edit_position";
    }


    @PostMapping("/positions/edit/{id}")
    public String handleEditForm(
            @PathVariable Long id,
            @Valid @ModelAttribute(ModelAttributeKeys.INTERNSHIP_POSITION_DASHBOARD_DTO) InternshipPositionDto internshipPositionDto,
            BindingResult bindingResult,
            Principal principal,
            Model model,
            RedirectAttributes redirectAttributes) {

        String companyEmail = principal.getName();

        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelAttributeKeys.INTERNSHIP_POSITION_DASHBOARD_DTO, internshipPositionDto);
            return "company/edit_position";
        }

        internshipPositionService.updatePosition(id, internshipPositionDto, companyEmail);
        redirectAttributes.addFlashAttribute("positionUpdated", true);
        return "redirect:/company/dashboard";
    }
}
