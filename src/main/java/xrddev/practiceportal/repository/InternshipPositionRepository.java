package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.InternshipPosition;

import java.util.List;

public interface InternshipPositionRepository extends JpaRepository<InternshipPosition, Long> {
    InternshipPosition findById(long id);
    List<InternshipPosition> findAllByCompanyEmail(String email);
}