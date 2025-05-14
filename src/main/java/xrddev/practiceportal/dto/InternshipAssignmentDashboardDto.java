package xrddev.practiceportal.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xrddev.practiceportal.model.enums.AssignmentStrategy;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternshipAssignmentDashboardDto {
    private Long id;
    private String studentName;
    private String positionTitle;
    private String professorName;
    private LocalDate assignedAt;
    private AssignmentStrategy strategy;
}