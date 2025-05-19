package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;

import java.util.List;
import java.util.Optional;

public interface InternshipAssignmentRepository extends JpaRepository<InternshipAssignment, Long> {
    List<InternshipAssignment> findAllByPositionId(Long positionId);

    List<InternshipAssignment> findAllByStudentId(Long studentId);

    List<InternshipAssignment> findAllByProfessorId(Long professorId);

    Optional<InternshipAssignment> findByIdAndProfessorId(Long id, Long professorId);

    Optional<InternshipAssignment> findByStudentId(Long student_id);

    Optional<InternshipAssignment> findByStudentEmail(String email);

    Optional<InternshipAssignment> findByPositionCompanyEmail(String email);

    List<InternshipAssignment> findAllByPositionCompanyEmail(String email);

    Optional<InternshipAssignment> findByIdAndStudentIdAndPositionCompanyEmail(Long id, Long studentId, String companyEmail);

}