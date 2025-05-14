package xrddev.practiceportal.service.api;

import xrddev.practiceportal.model.enums.AssignmentStrategy;

public interface StrategyDispatchService {
    void dispatchStrategy(AssignmentStrategy strategy);
}
