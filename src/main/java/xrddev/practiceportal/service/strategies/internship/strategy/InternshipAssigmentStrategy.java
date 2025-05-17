package xrddev.practiceportal.service.strategies.internship.strategy;

import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;

import java.util.List;

public interface InternshipAssigmentStrategy {

    InternshipMatchingOptions getStrategyType();

    List<InternshipAssignment> match();
}
