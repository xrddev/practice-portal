package xrddev.practiceportal.controller.company;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xrddev.practiceportal.config.ModelAttributes;
import xrddev.practiceportal.dto.company.CompanyDashboardDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDashboardDto;
import xrddev.practiceportal.dto.student.StudentDto;
import xrddev.practiceportal.service.api.CompanyService;
import xrddev.practiceportal.service.api.InternshipPositionService;

@Controller
@RequestMapping("/company")
public class CompanyDashboardController {

    private final CompanyService companyService;
    private final InternshipPositionService internshipPositionService;

    public CompanyDashboardController(CompanyService companyService, InternshipPositionService internshipPositionService) {
        this.companyService = companyService;
        this.internshipPositionService = internshipPositionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String companyEmail = principal.getName();

        var company = companyService.findByEmail(companyEmail).orElseThrow(() -> new RuntimeException("Company not found"));

        List<InternshipPositionDashboardDto> internshipPositionDashboardDto = internshipPositionService
                .getByCompanyEmail(companyEmail)
                .stream()
                .map(position -> {
                    InternshipPositionDashboardDto dto = new InternshipPositionDashboardDto(position);
                    if (!position.isAvailable()) {
                        dto.setStudent(new StudentDto(position.getStudent()));
                    }
                    return dto;
                })
                .toList();

        model.addAttribute(ModelAttributes.COMPANY_DASHBOARD_DTO, new CompanyDashboardDto(company));
        model.addAttribute(ModelAttributes.INTERNSHIP_POSITION_DASHBOARD_DTO, internshipPositionDashboardDto);
        return "company/dashboard";
    }

    @GetMapping("/edit-profile")
    public String editProfile(Model model, Principal principal) {
        String companyEmail = principal.getName();
        var company = companyService
                .findByEmail(companyEmail)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        model.addAttribute(ModelAttributes.COMPANY_DASHBOARD_DTO, new CompanyDashboardDto(company));
        return "company/edit_profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(
            @Valid @ModelAttribute(ModelAttributes.COMPANY_DASHBOARD_DTO) CompanyDashboardDto dto,
            BindingResult bindingResult,
            Principal principal,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelAttributes.COMPANY_DASHBOARD_DTO, dto);
            return "company/edit_profile";
        }

        dto.setEmail(principal.getName()); //No email override
        companyService.updateCompany(dto, principal.getName());
        redirectAttributes.addFlashAttribute("profileUpdated", true);
        return "redirect:/company/dashboard";
    }
}