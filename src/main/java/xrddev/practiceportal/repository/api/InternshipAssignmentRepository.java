package xrddev.practiceportal.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.internship.InternshipAssignment;

public interface InternshipAssignmentRepository extends JpaRepository<InternshipAssignment, Long> {
}