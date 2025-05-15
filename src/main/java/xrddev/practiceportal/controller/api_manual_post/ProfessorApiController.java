package xrddev.practiceportal.controller.api_manual_post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.dto.user.professor.ProfessorRegistrationDto;
import xrddev.practiceportal.service.api.ProfessorService;

import java.util.List;

@Validated                                         // ενεργοποιεί την container-element validation
@RestController
@RequestMapping(
        path     = "/public/api/professors",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class ProfessorApiController {

    private final ProfessorService professorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfessor(
            @RequestBody @Valid ProfessorRegistrationDto dto
    ) {
        professorService.registerProfessor(dto);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfessorsBatch(
            @RequestBody @Valid List<ProfessorRegistrationDto> dtos  // @Valid εδώ “πιάνει” κάθε στοιχείο
    ) {
        dtos.forEach(professorService::registerProfessor);
    }
}