package xrddev.practiceportal.controller.api_manual_post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionManualCreateDto;
import xrddev.practiceportal.service.internship.InternshipPositionService;

import java.util.List;

@RestController
@RequestMapping("/public/api/internships")
@RequiredArgsConstructor
public class InternshipPositionApiController {

    private final InternshipPositionService internshipPositionService;

    @PostMapping
    public void addPosition(@RequestBody InternshipPositionManualCreateDto frontEndDto) {
        internshipPositionService.createPosition(new InternshipPositionCreateDto(frontEndDto), frontEndDto.getCompanyEmail());
    }

    @PostMapping("/batch")
    public void addBatch(@RequestBody List<InternshipPositionManualCreateDto> frontEndDtoList) {
        frontEndDtoList.forEach(element
                -> internshipPositionService.createPosition(new InternshipPositionCreateDto(element), element.getCompanyEmail()));
    }
}
