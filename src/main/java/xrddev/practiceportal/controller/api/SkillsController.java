package xrddev.practiceportal.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.model.enums.Skills;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {

    private static final String API_TOKEN = "my-secure-api-token";

    @GetMapping
    public ResponseEntity<List<String>> getAllSkills(
            @RequestHeader(value = "X-API-TOKEN", required = false) String token) {
        // Έλεγχος αν το token είναι έγκυρο
        if (!API_TOKEN.equals(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null); // Επιστρέφει σφάλμα 403 αν το token είναι μη έγκυρο ή λείπει
        }

        // Επιστροφή όλων των Skills αν το token είναι σωστό
        List<String> skills = Arrays.stream(Skills.values())
                .map(Enum::name)
                .toList();
        return ResponseEntity.ok(skills);
    }
}