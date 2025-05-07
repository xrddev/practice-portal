package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}