package xrddev.practiceportal.controller.company;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionEditDto;
import xrddev.practiceportal.service.api.InternshipPositionService;


import java.security.Principal;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyPositionController {

    private final InternshipPositionService internshipPositionService;


    /*
     * Displays the form for creating a new internship position.
     *
     * @param model the Model object to add attributes
     * @return the view name for the creation form
     */
    @GetMapping("/new-position")
    public String showCreateForm(Model model) {
        model.addAttribute("position", new InternshipPositionCreateDto());
        return "company/new_position";
    }



    /**
     * Handles the submission of a new internship position form.
     *
     * @param positionDto   the DTO containing the position data
     * @param bindingResult contains the validation results
     * @param principal     the currently authenticated user
     * @return redirect to the dashboard on success or back to form on validation errors
     */
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



    /**
     * Deletes an internship position by its ID.
     *
     * @param id        the ID of the position to delete
     * @param principal the currently authenticated user
     * @return redirect to dashboard after deletion
     */
    @PostMapping("/positions/delete/{id}")
    public String deletePosition(@PathVariable Long id, Principal principal) {
        internshipPositionService.deleteByIdAndCompanyEmail(id, principal.getName());
        return "redirect:/company/dashboard";
    }



    /**
     * Displays the form for editing an existing internship position.
     *
     * @param id        the ID of the position to edit
     * @param model     the Model object to add attributes
     * @param principal the currently authenticated user
     * @return the view name for the edit form
     */
    @GetMapping("/positions/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("position", internshipPositionService.getByIdAndCompanyEmailMappedToEditDto(id, principal.getName()));
        return "company/edit_position";
    }



    /**
     * Handles the submission of an edited internship position form.
     *
     * @param dto           the DTO containing the updated position data
     * @param bindingResult contains the validation results
     * @param principal     the currently authenticated user
     * @return redirect to the dashboard on success or back to form on validation errors
     */
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