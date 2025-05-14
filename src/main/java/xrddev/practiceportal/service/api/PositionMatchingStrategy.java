package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.model.enums.AssignmentStrategy;

import java.util.List;

public interface PositionMatchingStrategy {

    AssignmentStrategy getStrategyType();

    void match();
}
