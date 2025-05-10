package xrddev.practiceportal.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.internship.InternshipPosition;

import java.util.List;
import java.util.Optional;

public interface InternshipPositionRepository extends JpaRepository<InternshipPosition, Long> {
    InternshipPosition findById(long id);
    List<InternshipPosition> findAllByCompanyEmail(String email);
    Optional<InternshipPosition> findByIdAndCompanyEmail(Long id, String companyEmail);
}