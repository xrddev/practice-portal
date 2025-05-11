package xrddev.practiceportal.controller.company;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xrddev.practiceportal.config.ModelAttributeKeys;
import xrddev.practiceportal.dto.company.CompanyDashboardDto;
import xrddev.practiceportal.service.api.CompanyService;

import java.security.Principal;

@Controller
@RequestMapping("/company")
public class CompanyProfileController {

    private final CompanyService companyService;

    public CompanyProfileController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/edit-profile")
    public String editProfile(Model model, Principal principal) {
        String companyEmail = principal.getName();
        model.addAttribute(ModelAttributeKeys.COMPANY_DASHBOARD_DTO, companyService.getByEmailMappedToDto(companyEmail));
        return "company/edit_profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(
            @Valid @ModelAttribute(ModelAttributeKeys.COMPANY_DASHBOARD_DTO) CompanyDashboardDto dto,
            BindingResult bindingResult,
            Principal principal,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelAttributeKeys.COMPANY_DASHBOARD_DTO, dto);
            return "company/edit_profile";
        }

        // Ensure the email is taken from the authenticated user and not from the submitted form for security reasons
        dto.setEmail(principal.getName());

        companyService.updateCompany(dto, principal.getName());
        redirectAttributes.addFlashAttribute("profileUpdated", true);
        return "redirect:/company/dashboard";
    }
}