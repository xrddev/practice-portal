package xrddev.practiceportal.service.api;

import xrddev.practiceportal.model.enums.AssignmentStrategy;
import xrddev.practiceportal.model.internship.InternshipAssignment;

import java.util.List;

public interface StrategyDispatchService {
    public List<InternshipAssignment> dispatchStrategy(AssignmentStrategy strategy);
}
