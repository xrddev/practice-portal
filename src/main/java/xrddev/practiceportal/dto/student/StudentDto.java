package xrddev.practiceportal.dto.student;

import xrddev.practiceportal.model.user.Student;

public record StudentDto(
        Long id,
        String fullName,
        String email,
        String studentNumber,
        String department,
        int yearOfStudy
        // αν θες, πρόσθεσε κι άλλα πεδία εδώ, π.χ. Double averageGrade, String preferredLocation κ.λπ.
) {
    public StudentDto(Student student) {
        this(
                student.getId(),
                student.getFirstName() + " " + student.getLastName(),
                student.getEmail(),
                student.getStudentNumber(),
                student.getDepartment().name(),
                student.getYearOfStudy()
        );
    }
}