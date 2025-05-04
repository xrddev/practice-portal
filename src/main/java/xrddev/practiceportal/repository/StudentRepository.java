package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentNumber(String studentNumber); // Εύρεση φοιτητή με βάση τον αριθμό μητρώου

    Optional<Student> findByEmail(String email); // Εύρεση φοιτητή με βάση το email (κληρονομείται από την User)

    Optional<Student> findByUsername(String username); // Εύρεση φοιτητή με βάση το username (κληρονομείται από την User)
}