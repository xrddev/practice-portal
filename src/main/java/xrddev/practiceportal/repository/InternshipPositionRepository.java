package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.internship.InternshipPosition;

import java.util.List;
import java.util.Optional;

public interface InternshipPositionRepository extends JpaRepository<InternshipPosition, Long> {

    List<InternshipPosition> findAllByCompanyEmail(String email);

    Optional<InternshipPosition> findByIdAndCompanyEmail(Long id, String companyEmail);

    List<InternshipPosition> findAllByCompanyId(Long companyId);
}