package xrddev.practiceportal.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.model.enums.Department;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private static final String API_TOKEN = "my-secure-api-token"; // Σταθερό token

    @GetMapping
    public ResponseEntity<?> getAllDepartments(
            @RequestHeader(value = "X-API-TOKEN", required = false) String token) {
        // Έλεγχος αν το token είναι έγκυρο
        if (!API_TOKEN.equals(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null);
        }

        // Επιστροφή όλων των Departments αν το token είναι σωστό
        List<Department> departments = Arrays.asList(Department.values());
        return ResponseEntity.ok(departments);
    }
}