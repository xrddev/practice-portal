package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.Professor;
import xrddev.practiceportal.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
}