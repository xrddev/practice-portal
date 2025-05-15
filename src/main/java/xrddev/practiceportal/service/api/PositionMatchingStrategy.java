package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.model.enums.AssignmentStrategy;
import xrddev.practiceportal.model.internship.InternshipAssignment;

import java.util.List;

public interface PositionMatchingStrategy {

    AssignmentStrategy getStrategyType();

    public List<InternshipAssignment> match();
}
