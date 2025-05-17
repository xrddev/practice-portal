package xrddev.practiceportal.service.strategies;

import org.springframework.beans.factory.annotation.Qualifier;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;

import java.util.List;

public interface InternshipAssigmentStrategyDispatcher {

    @Qualifier("mapDrivenInternshipAssigmentDispatcher")
    List<InternshipAssignment> dispatch(InternshipMatchingOptions strategy);
}
